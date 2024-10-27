#include<stdio.h>
#include<stdlib.h>
#include<sys/types.h>
#include<pthread.h>

float f,*vettore;
int n;

void *thread_function(void *arg){
	int contatore=0;
	int i=0,inf = *(int*)arg;
	
	for(i=inf; i<n; i++)
		if(vettore[i] == f)
			contatore++;
			
	printf("Numero di ricorrenze nella seconda parte del vettore = %d\n",contatore);
	pthread_exit(NULL);
}

int main(int argc, char *argv[]){
	pthread_t tid;
	int i, res, sup;
	int contatore=0;
	
	if(argc != 2){
		fprintf(stderr,"Parametri errati\n");
		exit(EXIT_FAILURE);
	}
	if((n=atoi(argv[1])) < 0){
		fprintf(stderr,"Il numero dii elementi e' errato\n");	
		exit(EXIT_FAILURE);
	}
	
	vettore = (float *)malloc(n*sizeof(float));
	for(i=0;i<n;i++){
		vettore[i] = (float) (rand()%100)/10;
		printf("vettore[%d] = %f\n", i,vettore[i]);	
	}
	
	printf("Inserire il float da ricercare nel vettore: ");
	scanf("%f",&f);
		
	sup=n/2;
	res = pthread_create(&tid, NULL, thread_function, (void *)&sup);
	if(res != 0)
		exit(EXIT_FAILURE);
	
	for(i=0;i<sup;i++)
		if(vettore[i] == f)
			contatore++;

	printf("Numero di ricorrenze nella prima parte del vettore = %d\n",contatore);
	
	res = pthread_join(tid,NULL);
	if(res != 0)
		exit(EXIT_FAILURE);
	
	printf("Thread joined\n");
	exit(EXIT_SUCCESS);
}
