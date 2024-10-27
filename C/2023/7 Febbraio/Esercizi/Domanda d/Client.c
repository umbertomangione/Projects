#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<netinet/in.h>
#include<arpa/inet.h>
#include<unistd.h>
#include<sys/socket.h>

#define N 10
#define SIZE 5

typedef struct {
	unsigned int vettore[N];
	float media;
} info;

void riempi(info *p, int dim){
	int i;
   for (i=0; i<dim; i++){
		printf("Inserisci elemento di indice %d ",i);
	 	scanf("%u",&p->vettore[i]);
   } 
   while(getchar()!='\n');  
}

int main(void) {
   int sockfd, len, result, running=1;
   struct sockaddr_in address;
   info message;
   char buffer[SIZE];
     
   sockfd=socket(AF_INET, SOCK_STREAM, 0);
   if (sockfd==-1) {
		fprintf(stderr,"opps: creazione socket fallita ");
	 	exit(EXIT_FAILURE);
   } 
   address.sin_family=AF_INET;
   inet_aton("127.0.0.1", &address.sin_addr); 
   address.sin_port=htons(9734);
   len=sizeof(address);
   result=connect(sockfd, (struct sockaddr *)&address, len);
   if (result==-1) {
		fprintf(stderr,"opps: connessione rifiutata ");
		exit(EXIT_FAILURE);
   }
   printf("Connesso al Server \n");
   while(running) {
      printf("Vuoi Continuare ? ");
	 	fgets(buffer, SIZE, stdin);
	 	if (!strncmp(buffer, "n", 1) || !strncmp(buffer, "N", 1)) {
			running = 0;
	 	} else {
			riempi(&message,N);
			write(sockfd, &message, sizeof(info));
			read(sockfd, &message, sizeof(info));
			printf("La risposta che ho ricevuto e' stata: %f ", message.media);
	 	}
   }
	
   close(sockfd);
   exit(EXIT_SUCCESS);
}
