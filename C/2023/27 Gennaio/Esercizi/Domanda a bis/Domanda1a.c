#include<stdio.h>
#include<stdlib.h>
#include<sys/fcntl.h>
#include<sys/types.h>
#include<unistd.h>
#include<sys/sem.h>
#include"semaforo_mio.h"

#define SEM_KEY_WRITE (key_t)5678
#define SEM_KEY_READ (key_t)9012

int main(void){
 	int WriteID, ReadID;
	int fd = open("myfile", O_CREAT|O_TRUNC|O_WRONLY, 0660);
	
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
	
	if(fork()){
		if(SEM_P(ReadID) == -1) exit(EXIT_FAILURE);
		
		write(fd, "World! \n",8);
		close(fd);
		
		if(SEM_V(WriteID) == -1) exit(EXIT_FAILURE);
		
		if(SEM_DEL(WriteID) == -1) exit(EXIT_FAILURE);
		if(SEM_DEL(ReadID) == -1) exit(EXIT_FAILURE);
		
		exit(EXIT_SUCCESS);
	}
	if(SEM_P(WriteID) == -1) exit(EXIT_FAILURE);
	
	printf("Processo Figlio: Scritto sul file\n");
	write(fd, "Hello ", 6);
	close(fd);
	
	if(SEM_V(ReadID) == -1) exit(EXIT_FAILURE);
	
	exit(EXIT_SUCCESS);
}
