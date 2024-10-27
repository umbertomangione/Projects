#include<stdio.h>
#include<stdlib.h>
#include<sys/fcntl.h>
#include<sys/types.h>
#include<unistd.h>

int main(int argc, char *argv[]){
	int fd;
	ssize_t size;
	
	if(argc<2){
		fprintf(stderr,"Parametri Errati, Inserire il nome del file\n");
		exit(EXIT_FAILURE);
	}
	
	fd = open(argv[1], O_CREAT|O_TRUNC|O_WRONLY, 0660);
		
	if(fork()){
		
		
		write(fd, "Come ", 5);
		printf("Processo Padre: Scritto sul file!\n");
		sleep(2);
		
		close(fd);
		exit(EXIT_SUCCESS);
	}
	
	sleep(1);
	write(fd, "stai?", 5);
	printf("Processo Figlio: Scritto sul file!\n");
	
	close(fd);
	exit(EXIT_SUCCESS);
}
