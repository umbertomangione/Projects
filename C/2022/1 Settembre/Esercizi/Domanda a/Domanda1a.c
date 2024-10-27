#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<fcntl.h>
#include<sys/types.h>
#define BUFSIZE 1024

int main(int argc, char *argv[]){
	int sd,dd;
	off_t i,n;
	ssize_t size,result;
	char buffer[BUFSIZE];
	
	if(argc<4){
		fprintf(stderr,"Errore chiamata (Inserire Nome file, Nome file e Carattere)\n");
		exit(EXIT_FAILURE);
	}
	n=(off_t)atoi(argv[3]);
	sd=open(argv[1], O_RDONLY);
	if(sd==-1){
		fprintf(stderr,"Errore Apertura File\n");
		exit(EXIT_FAILURE);
	}
	
	dd=open(argv[2], O_WRONLY | O_CREAT | O_TRUNC, 0660);
	if(dd==-1){
		fprintf(stderr,"Errore Apertura File\n");
		close(sd);
		exit(EXIT_FAILURE);
	}
	
	i=lseek(sd,(off_t)n, SEEK_SET);
	do{
		size=read(sd,buffer,BUFSIZE);
		result=write(dd,buffer,size);
	}while(size>0);
	
	close(sd);
	close(dd);
	exit(EXIT_SUCCESS);
}
