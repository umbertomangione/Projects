#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<sys/socket.h>
#include<arpa/inet.h> 
#include<unistd.h>
#include<pthread.h>

#define N 10
#define SIZE 5

typedef struct {
	unsigned int vettore[N];
	float media;
} info;

float media(info *p, int dim){
	int i;
	float m=0;
	for (i=0; i<dim; i++) m+=p->vettore[i];
	return(m/dim);
}
/* Funzione per la gestione di ciascun client*/

void *connection_handler(void *socket_desc){
	int socket = *(int*)socket_desc;
	int risultato;
	int ripeti=1;
	info message;
	
	while(ripeti) {
		risultato=read(socket, &message, sizeof(info));
		if (risultato==0) { //vuol dire che il client ha chiuso la connessione
                                    //in tal caso il server legge 0 byte 
			ripeti=0;
		} else {
			message.media=media(&message,N);
			write(socket, &message, sizeof(info));
		}
	}
	close(socket);
	free(socket_desc);
	pthread_exit(NULL);
}

int main(void){
	int server_sockfd, client_sockfd;
	int server_len, client_len;
	struct sockaddr_in server_address, client_address;
	int *new_sock;
	int res;
	pthread_t tid;
	
	server_sockfd = socket(AF_INET , SOCK_STREAM , 0);
	if (server_sockfd == -1) {
		fprintf(stderr,"Non posso creare il socket");
		exit(EXIT_FAILURE);
	}
	printf("Socket creato\n");
	
	server_address.sin_family=AF_INET;
   	inet_aton("127.0.0.1", &server_address.sin_addr); 
   	server_address.sin_port=htons(9734);
   	server_len=sizeof(server_address);
	
   	if (bind(server_sockfd,(struct sockaddr *)&server_address, server_len) ==-1){
		fprintf(stderr,"Errore nella funzione di Bind");
		exit(EXIT_FAILURE);
	}
	printf("Bind eseguita\n");
	listen(server_sockfd, 5);
	
	printf("Attendo connesioni ...\n");
	while(1) {
	    client_len=sizeof(client_address);
	    client_sockfd=accept(server_sockfd,(struct sockaddr *) &client_address, &client_len);
  	    if (client_sockfd < 0){
			fprintf(stderr,"Connessione non accettata ");
			exit(EXIT_FAILURE);
	    }
        printf("Connessione accettata\n");
		
	    new_sock = (int *)malloc(sizeof(int));
	    *new_sock = client_sockfd;
	    
	    res = pthread_create(&tid, NULL, connection_handler, (void *)new_sock);
	    if (res != 0) {
			fprintf(stderr, "Creazione del Thread fallita");
			exit(EXIT_FAILURE);
	    }
	    pthread_detach(tid);
	}
}
