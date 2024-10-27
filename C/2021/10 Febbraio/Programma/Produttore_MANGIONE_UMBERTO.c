#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<string.h>
#include<sys/sem.h>
#include<sys/shm.h>
#include"semaforo_mio.h"

#define SHM_KEY (key_t)1234
#define SEM_KEY_WRITE (key_t)5678
#define SEM_KEY_READ (key_t)9012
#define N 100

typedef struct {
	unsigned int n;
	float v[N];
} calcolo;

int main(void){
	void *ShmP = (void *)0;
	int WriteID, ReadID, ShmID,i,n;
	calcolo *c;
	
	WriteID = semget(SEM_KEY_WRITE, 1, 0666 | IPC_CREAT);
	if (WriteID == -1){
		fprintf(stderr, "Semget WRITE failed\n");
		exit(EXIT_FAILURE);
	}
	ReadID = semget(SEM_KEY_READ, 1, 0666 | IPC_CREAT);
	if (ReadID == -1){
		fprintf(stderr, "Semget READ failed\n");
		exit(EXIT_FAILURE);
	}
	
	ShmID = shmget(SHM_KEY, sizeof(calcolo), 0666 | IPC_CREAT);
	if (ShmID == -1){
		fprintf(stderr, "Shmget failed\n");
		exit(EXIT_FAILURE);
	}
	ShmP = shmat(ShmID, (void *)0, 0);
	if (ShmP == (void *)-1){
		fprintf(stderr, "Shmat failed\n");
		exit(EXIT_FAILURE);
	}
	c = (calcolo *)ShmP;
	
	printf("Inserire un numero intero: ");
	scanf("%d",&c->n);
	
	n = c->n; 
	
	while(n>0){
	
		if(SEM_P(WriteID) == -1) exit(EXIT_FAILURE);
		for(i=0; i<N; i++){
			c->v[i] = (float) (rand()%100)/10;
			printf("%d° posizione = %f\n", i+1, c->v[i]);
		} 
		if(SEM_V(ReadID) == -1) exit(EXIT_FAILURE);
		
		n--;
	}
	
	if(shmdt(ShmP) == -1){
		fprintf(stderr,"shmdt failed\n");
		exit(EXIT_FAILURE);
	}
	
	exit(EXIT_SUCCESS);
}