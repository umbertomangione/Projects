#include<stdio.h>
#include<stdlib.h>
#include<sys/types.h>
#include<pthread.h>

int *vettore;
int n,num[3],sup,v=0;

void *thread_function(void *arg){
	int i,j=0,p,inf = *(int*)arg;
	
	p = v++;
	for(i=0;i<3;i++)
		if(num[i] == ((int)pthread_self()))
			p = i;
	
	for(i= p *(n/3);i<((v)*(n/3));i++){
		printf("Vettore[%d]= %d\n",i,vettore[i]);	
		j += vettore[i];
	}
	
	if((((v)*(n/3)) - n)>-3)
		for(i=((v)*(n/3)); i<n;i++){
			printf("Vettore[%d]= %d\n",i,vettore[i]);
			j += vettore[i];
		}
		
	printf("Somma = %d\n",j);
	num[p] = j;
	
	pthread_exit(NULL);
}

int main(int argc, char *argv[]){
	pthread_t tid[3];
	int i,j=0,res;
	
	if(argc != 2){
		fprintf(stderr,"Parametri errati\n");
		exit(EXIT_FAILURE);
	}
	if((n=atoi(argv[1])) < 0){
		fprintf(stderr,"Il numero di elementi e' errato\n");	
		exit(EXIT_FAILURE);
	}
	
	vettore = (int *)malloc(n*sizeof(int));
	
	for(i=0;i<n;i++){
		vettore[i] = rand()%100;
		printf("Vettore[%d]= %d\n",i,vettore[i]);	
	}
	printf("\n");
	
	sup=n/3;
	
	for(i=0; i<3; i++){
		if(i==1)
			sup++;
					
		res = pthread_create(&tid[i], NULL, thread_function, (void *)&sup);
		if(res != 0)
			exit(EXIT_FAILURE);
	
		num[i] = (int)tid;
		
		res = pthread_join(tid[i],NULL);
		if(res != 0)
			exit(EXIT_FAILURE);
	}
	
	for(i=0; i<3; i++)
		j += num[i];
	
	printf("Somma delle Somme = %d\n",j);
		
	printf("Thread joined\n");
	exit(EXIT_SUCCESS);
}
