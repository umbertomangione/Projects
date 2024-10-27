#include<stdio.h>
#include<stdlib.h>
#include<sys/fcntl.h>
#include<sys/types.h>
#include<unistd.h>

int main(int argc, char *argv[]){
	char ch, randomletter;
	int i, numeroscrivere, numeroleggere,fd;
	ssize_t size;
	
	if(argc<4){
		fprintf(stderr,"Parametri Errati\n");
		exit(EXIT_FAILURE);
	}
	
	numeroscrivere = atoi(argv[2]);
	numeroleggere = atoi(argv[3]);
	
	if(fork()){
		fd = open(argv[1], O_RDONLY);
		printf("Processo Padre: premi un tasto per leggere i caratteri \n");
		getchar();
		
		printf("Frase:\n");
		
		for(i=0; i<numeroleggere; i++){
			read(fd, &ch, 1);
			printf("%c",ch);
			if(i == (numeroleggere -1))
				printf("\n");
		}
		
		close(fd);
		exit(EXIT_SUCCESS);
	}
	
	fd = open(argv[1], O_CREAT|O_TRUNC|O_WRONLY, 0660);
	printf("Processo Figlio: Scritto sul file\n");
	for(i=0; i<numeroscrivere; i++){
		randomletter = 'A' + (random() % 26);
		write(fd, &randomletter, 1);
		if(i == (numeroscrivere -1))
			write(fd, "\n", 1);
	}
	
	close(fd);
	exit(EXIT_SUCCESS);
}
