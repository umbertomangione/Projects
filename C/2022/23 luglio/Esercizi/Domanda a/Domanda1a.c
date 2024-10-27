#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<fcntl.h>

int main(int argc, char *argv[]){
	char *s;
	int fd,i=0;
	ssize_t result;
	
	if(argc<2){
		fprintf(stderr,"Inserire il nome del file da aprire\n");
		exit(EXIT_FAILURE);
	}
	s=(char *)malloc(1*sizeof(char));
	fd=open(argv[1], O_RDONLY);
	if(fd==-1){
		fprintf(stderr, "Errore Apertura File\n");
		exit(EXIT_FAILURE);
	}
	
	do{
		result=read(fd,s,1);
		if(result>0) i++;
	} while(result>0);
	
	printf("Numero di caratteri %d\n",i);
	close(fd);
	exit(EXIT_SUCCESS);
}
