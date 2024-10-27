#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<string.h>
#include<sys/sem.h>
#include<sys/shm.h>
#include"semaforo_mio.h"

#define BUF 5
#define SHM_KEY (key_t)1234
#define SEM_KEY_WRITE (key_t)5678
#define SEM_KEY_READ (key_t)9012

typedef struct {
	int random[BUF];
	int fine;
} struttura;

int main(void){
	void *ShmP = (void *)0;
	int WriteID, ReadID, ShmID,i,running = 1;
	int media;
	struttura *s;
	
	WriteID = semget(SEM_KEY_WRITE, 1, 0666 | IPC_CREAT);
	if (WriteID == -1){
		fprintf(stderr, "Creazione Semaforo WRITE fallita\n");
		exit(EXIT_FAILURE);
	}
	ReadID = semget(SEM_KEY_READ, 1, 0666 | IPC_CREAT);
	if (ReadID == -1){
		fprintf(stderr, "Creazione Semaforo READ fallita\n");
		exit(EXIT_FAILURE);
	}
	SEM_SET(WriteID, 1);
	SEM_SET(ReadID, 0);
	
	ShmID = shmget(SHM_KEY, sizeof(struttura), 0666 | IPC_CREAT);
	if (ShmID == -1){
		fprintf(stderr, "Allocazione memoria condivisa fallita\n");
		exit(EXIT_FAILURE);
	}
	ShmP = shmat(ShmID, (void *)0, 0);
	if (ShmP == (void *)-1){
		fprintf(stderr, "Aggancio della memoria condivisa fallita\n");
		exit(EXIT_FAILURE);
	}
	s = (struttura *)ShmP;
	
	while(running){
		media=0;
		
		if(SEM_P(ReadID) == -1) exit(EXIT_FAILURE);

		for (i=0; i<BUF; i++){
			printf("Vettore[%d] = %d\n",i,s->random[i]);
			media += s->random[i];
		}
		
		media /= BUF;
		printf("Media = %d\n",media);
		
		if(s->fine == -1)
			running = 0;
		
		if(SEM_V(WriteID) == -1) exit(EXIT_FAILURE);
	}
	
	if(shmdt(ShmP) == -1){
		fprintf(stderr,"shmdt failed\n");
		exit(EXIT_FAILURE);
	}
	if(shmctl(ShmID, IPC_RMID,0) == -1){
		fprintf(stderr,"shmctl(IPC_RMID) failed\n");
		exit(EXIT_FAILURE);
	}
	
	if(SEM_DEL(WriteID) == -1) exit(EXIT_FAILURE);
	if(SEM_DEL(ReadID) == -1) exit(EXIT_FAILURE);
	
	exit(EXIT_SUCCESS);
}