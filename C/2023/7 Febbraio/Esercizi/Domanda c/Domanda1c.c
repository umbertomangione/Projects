#include<stdio.h>
#include<stdlib.h>
#include<sys/types.h>
#include<pthread.h>

unsigned int *vettore;
int n,num;

void *thread_function(void *arg){
	int i=0,j=0,inf = *(int*)arg;
	
	for(i=0; i<inf; i++)
		if(vettore[i]==num)
			j++;
			
	printf("Ricorrenze ritrovate dal thread = %d\n",j);
	pthread_exit(NULL);
}

int main(int argc, char *argv[]){
	pthread_t tid;
	int i, res, sup,j=0;
	
	if(argc != 2){
		fprintf(stderr,"Parametri errati\n");
		exit(EXIT_FAILURE);
	}
	if((n=atoi(argv[1])) < 0){
		fprintf(stderr,"Il numero dii elementi e' errato\n");	
		exit(EXIT_FAILURE);
	}
	
	vettore = (int *)malloc(n*sizeof(float));
	
	for(i=0;i<n;i++){
		vettore[i] = rand()%100;
		printf("Vettore[%d]= %d\n",i,vettore[i]);	
	}
	
	printf("Inserire numero da ricercare: ");
	scanf("%d",&num);
		
	sup=n/2;
	res = pthread_create(&tid, NULL, thread_function, (void *)&sup);
	if(res != 0)
		exit(EXIT_FAILURE);
	
	for(i=sup;i<n;i++)
		if(vettore[i]==num)
			j++;
	
	res = pthread_join(tid,NULL);
	if(res != 0)
		exit(EXIT_FAILURE);
	
	printf("Ricorrenze trovare dal Thread Main = %d\n",j);
		
	printf("Thread joined\n");
	exit(EXIT_SUCCESS);
}
