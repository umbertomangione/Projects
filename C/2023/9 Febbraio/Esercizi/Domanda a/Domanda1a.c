#include<stdio.h>
#include<stdlib.h>
#include<sys/fcntl.h>
#include<sys/types.h>
#include<unistd.h>

int main(void){
	char ch;
	int count=-1;
	ssize_t size;
	
	if(fork()){
		int fd = open("myfile", O_RDONLY);
		printf("Processo Padre: premi un tasto per continuare \n");
		getchar();
		
		printf("Frase:\n");
		
		do{
			size = read(fd,&ch, 1);
			printf("%c\n",ch);
			count++;	
		}while(size != 0);
		
		printf("Numero dei caratteri = %d\n",count);
		
		close(fd);
		exit(EXIT_SUCCESS);
	}
	int fd = open("myfile", O_CREAT|O_TRUNC|O_WRONLY, 0660);
	printf("Processo Figlio: Scritto sul file\n");
	write(fd, "Helloooo\n", 9);
	close(fd);
	exit(EXIT_SUCCESS);
}
