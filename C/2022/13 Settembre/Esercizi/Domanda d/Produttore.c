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

typedef struct {
	int integ;
	float f;
	char str;
} mix;

int main(void){
	void *ShmP = (void *)0;
	int WriteID, ReadID, ShmID,n=2;
	mix *m;
	
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
	
	ShmID = shmget(SHM_KEY, sizeof(mix), 0666 | IPC_CREAT);
	if (ShmID == -1){
		fprintf(stderr, "Shmget failed\n");
		exit(EXIT_FAILURE);
	}
	ShmP = shmat(ShmID, (void *)0, 0);
	if (ShmP == (void *)-1){
		fprintf(stderr, "Shmat failed\n");
		exit(EXIT_FAILURE);
	}
	m = (mix *)ShmP; 
	
	while(n>0){
	
		if(SEM_P(WriteID) == -1) exit(EXIT_FAILURE);
		if(n == 2){
			m->integ=8;
			m->f=12.5;
		} else{
			printf("Premi un tasto per concludere il programma");
			getchar();
			m->str = 'f';
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
