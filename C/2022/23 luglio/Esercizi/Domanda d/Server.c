#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<sys/socket.h>
#include<arpa/inet.h>
#include<unistd.h>
#include<pthread.h>

void *connection_handler(void *);

int main(void){
	int sockfd, client_descriptor;
	int res, server_len, client_len;
	struct sockaddr_in server_address, client_address;
	int *new_sock;
	pthread_t tid;
	
	sockfd = socket(AF_INET , SOCK_STREAM , 0);
	
	server_address.sin_family=AF_INET;
	inet_aton("127.0.0.1", &server_address.sin_addr);
	server_address.sin_port=htons(9734);
	server_len=sizeof(server_address);
	bind(sockfd,(struct sockaddr*)&server_address, server_len);
	listen(sockfd, 5);
	
	while(1) {
		client_len=sizeof(client_address);
		client_descriptor=accept(sockfd,(struct sockaddr*)&client_address,&client_len);
		
		new_sock= (int*)malloc(sizeof(int));
		*new_sock= client_descriptor;
		
		res = pthread_create(&tid, NULL, connection_handler,(void*)new_sock);
		if(res != 0) {
			fprintf(stderr,"Creazione del Thread fallita");
			exit(EXIT_FAILURE);
		}
		pthread_detach(tid);
	}
}

void *connection_handler(void *client_desc){
	int descriptor= *(int*)client_desc;
	int primo,secondo;
	
	read(descriptor, &primo, 1);
	read(descriptor, &secondo, 1);
	primo *= secondo;
	write(descriptor, &primo, 1);
	
	close(descriptor);
	free(client_desc);
	pthread_exit(NULL);
}
