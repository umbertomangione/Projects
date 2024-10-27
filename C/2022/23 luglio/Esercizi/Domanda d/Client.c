#include<stdio.h>
#include<stdlib.h>
#include<netinet/in.h>
#include<arpa/inet.h>
#include<unistd.h>
#include<sys/socket.h>

int main(void) {
	int sockfd, len, result;
	struct sockaddr_in address;
	int primo = rand() % 20, secondo = rand() % 20,risultato=0;
	sockfd=socket(AF_INET, SOCK_STREAM, 0);
	
	address.sin_family=AF_INET;
	inet_aton("127.0.0.1", &address.sin_addr);
	address.sin_port=htons(9734);
	len=sizeof(address);
	
	result=connect(sockfd, (struct sockaddr*)&address, len);
	if(result==-1) {
		fprintf(stderr,"opps: connessione rifiutata ");
		exit(EXIT_FAILURE);
	}
	printf("Client PID=%d: 1° int = %d, 2° int = %d \n",getpid(),primo,secondo);
	write(sockfd, &primo,1);
	write(sockfd, &secondo,1);
	read(sockfd, &risultato,1);
	printf("Client PID=%d: prodotto ricevuto dal server = %d\n",getpid(),risultato);
	close(sockfd);
	exit(EXIT_SUCCESS);
}
