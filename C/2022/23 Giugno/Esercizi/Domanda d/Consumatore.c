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
	char str[10];
} stringa;

int main(void){
	void *ShmP = (void *)0;
	int WriteID, ReadID, ShmID,n=2;
	stringa *s;
	
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
	
	ShmID = shmget(SHM_KEY, sizeof(stringa), 0666 | IPC_CREAT);
	if (ShmID == -1){
		fprintf(stderr, "Allocazione memoria condivisa fallita\n");
		exit(EXIT_FAILURE);
	}
	ShmP = shmat(ShmID, (void *)0, 0);
	if (ShmP == (void *)-1){
		fprintf(stderr, "Aggancio della memoria condivisa fallita\n");
		exit(EXIT_FAILURE);
	}
	s = (stringa *)ShmP;
	
	while(n>0){
	
		if(SEM_P(ReadID) == -1) exit(EXIT_FAILURE);
		strcpy( s->str, "Consumatore" );
		printf("Stringa del consumatore = %s\n",s->str);
		if(SEM_V(WriteID) == -1) exit(EXIT_FAILURE);
		
		n--;
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
