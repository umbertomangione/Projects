#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h> 
#include <pthread.h>

void *thread_function(void *);

typedef struct {
	pthread_t tid;
	int params;
} threads;

int main(int argc, char *argv[]) {
	int i, res,n;
	
	if(argc<2){
		fprintf(stderr,"Parametri Errati, inserire numero di thread\n");
		exit(EXIT_FAILURE);
	}
	
	n=atoi(argv[1]);
	threads vett[n];
	
	for(i = 0; i < n; i++) {
		vett[i].params=i+2;
		res=pthread_create(&vett[i].tid, NULL, thread_function, (void *)&vett[i].params);
		if (res != 0) exit(EXIT_FAILURE);
	}
	
	printf("Aspetto che i thread finiscano...\n");
	
	for(i = 0; i < n; i++) {
		res = pthread_join(vett[i].tid, NULL);
		if (res == 0) printf("Sonoil main(): il Thread %d e' terminato\n",i);
		else exit(EXIT_FAILURE);
	}
	
	printf("Sono il main(): Tutti i thread sono terminati\n");
	exit(EXIT_SUCCESS);
}

void *thread_function(void *arg) {
	int numero = *(int *)arg;
	int i;
	
	printf("Sono il thread n. = %d\n", (numero-2));
	for(i=1;i<11;i++){
		printf("%d x %d = %d\n",numero,i,(numero*i));
		sleep(1);
	}
	printf("Termino!\n");
	pthread_exit(NULL);
}
