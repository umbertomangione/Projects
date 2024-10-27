#include<stdio.h>
#include<stdlib.h>
#include<sys/fcntl.h>
#include<sys/types.h>
#include<unistd.h>

int main(void){
	int fd = open("myfile", O_CREAT|O_TRUNC|O_WRONLY, 0660);
	
	if(fork()){
		printf("Processo Padre: premi un tasto per continuare \n");
		getchar();
		write(fd, "World! \n",8);
		close(fd);
		exit(EXIT_SUCCESS);
	}
	printf("Processo Figlio: Scritto sul file");
	write(fd, "Hello ", 6);
	close(fd);
	exit(EXIT_SUCCESS);
}
