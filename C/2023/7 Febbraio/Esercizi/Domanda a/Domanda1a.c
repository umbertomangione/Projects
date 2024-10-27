#include<stdio.h>
#include<stdlib.h>
#include<sys/fcntl.h>
#include<sys/types.h>
#include<unistd.h>

int main(int argc, char *argv[]){
	int fd,count=-1;
	char ch;
	ssize_t size;
	
	if(argc<2){
		fprintf(stderr,"Parametri Errati, Inserire il nome del file\n");
		exit(EXIT_FAILURE);
	}
		
	if(fork()){
		fd = open(argv[1], O_CREAT|O_TRUNC|O_WRONLY, 0660);
		
		write(fd, "Come ", 5);
		printf("Processo Padre: Scritto sul file!\n");
		sleep(2);
		
		close(fd);
		exit(EXIT_SUCCESS);
	}
	fd = open(argv[1], O_RDONLY);
	
	sleep(1);
	do{
		size = read(fd,&ch, 1);
		printf("%c\n",ch);
		count++;	
	}while(size != 0);
	printf("Processo Figlio: Numero di caratteri = %d\n",count);
	
	close(fd);
	exit(EXIT_SUCCESS);
}
