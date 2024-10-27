#include<stdio.h>
#include<stdlib.h>
#include<sys/types.h>
#include<pthread.h>

float *vettore;
int n;

void *thread_function(void *arg){
	int i=0,inf = *(int*)arg;
	
	for(i=inf; i<n; i++){
		vettore[i] = (float) (rand()%100)/10;
		printf("Seconda parte del vettore vettore[%d]= %f\n",i,vettore[i]);
	}
	
	pthread_exit(NULL);
}

int main(int argc, char *argv[]){
	pthread_t tid;
	int i, res, sup;
	
	if(argc != 2){
		fprintf(stderr,"Parametri errati\n");
		exit(EXIT_FAILURE);
	}
	if((n=atoi(argv[1])) < 0){
		fprintf(stderr,"Il numero dii elementi e' errato\n");	
		exit(EXIT_FAILURE);
	}
	
	vettore = (float *)malloc(n*sizeof(float));
		
	sup=n/2;
	res = pthread_create(&tid, NULL, thread_function, (void *)&sup);
	if(res != 0)
		exit(EXIT_FAILURE);
	
	for(i=0;i<sup;i++){
		vettore[i] = (float) (rand()%100)/10;
		printf("Prima parte del vettore vettore[%d]= %f\n",i,vettore[i]);	
	}
	
	res = pthread_join(tid,NULL);
	if(res != 0)
		exit(EXIT_FAILURE);
	
	for(i=0;i<n;i++)
		printf("vettore[%d] = %f\n", i,vettore[i]);
		
	printf("Thread joined\n");
	exit(EXIT_SUCCESS);
}
