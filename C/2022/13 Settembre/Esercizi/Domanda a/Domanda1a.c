#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<fcntl.h>
#include<sys/types.h>

#define BUFSIZE 256

int main(int argc, char *argv[]){
	int fd;
	char *buffer;
	
	if(argc<2){
		fprintf(stderr,"Errore chiamata (Inserire Nome file)\n");
		exit(EXIT_FAILURE);
	}
	
	buffer=(char *)malloc(BUFSIZE*sizeof(char));

	if (fork() == 0){
		fd=open(argv[1], O_CREAT|O_TRUNC|O_WRONLY, 0666);
		write(fd, "Hello\n",6);
		exit(EXIT_SUCCESS);
	}
	
	printf("Premi un tasto per continuare\n");
	getchar();
	fd=open(argv[1], O_RDONLY);
	read(fd,buffer,BUFSIZE);
	printf("Frase scritta dal figlio = %s \n",buffer);	
	close(fd);
	exit(EXIT_SUCCESS);
}
