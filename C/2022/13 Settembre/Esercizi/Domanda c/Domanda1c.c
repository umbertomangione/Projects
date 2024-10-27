#include<stdio.h>
#include<stdlib.h>
#include<sys/types.h>
#include<pthread.h>

float *vettore;
int n;
float max2=0.0;

void *thread_function(void *arg){
	int i=0,inf = *(int*)arg;
	
	for(i=inf; i<n; i++)
		if(vettore[i]>max2)
			max2 = vettore[i];
	
	pthread_exit(NULL);
}

int main(int argc, char *argv[]){
	pthread_t tid;
	int i, res, sup;
	float max1=0.0;
	
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
	
	for(i=0;i<n;i++){
		vettore[i] = (float) (rand()%100)/10;
		printf("Vettore[%d]= %f\n",i,vettore[i]);	
	}
	
	for(i=0;i<sup;i++)
		if(vettore[i]>max1)
			max1 = vettore[i];
	
	res = pthread_join(tid,NULL);
	if(res != 0)
		exit(EXIT_FAILURE);
	
	printf("Valore massimo della prima parte del vettore = %f,\nValore massimo della seconda parte del vettore = %f\n",max1,max2);
		
	printf("Thread joined\n");
	exit(EXIT_SUCCESS);
}
