#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/types.h>
#include<signal.h>
#include<sys/wait.h>

#define N 20E8

void sigHandler(int sig){
	pid_t pid;
	int statVal, value;
	
	while((pid = wait(&statVal))>0)
		if(WIFEXITED(statVal)){
			value=WEXITSTATUS(statVal);
			printf("\nFiglio (PID=%d) Terminato Normalmente ", pid);
			printf("\nValore di ritorno=%d\n ", value); 
		} else
			printf("\nTerminazione Anomala\n");
		
	return;
}

int main(int argc, char *argv[]){
	int nChild,i;
	struct sigaction action;
	
	sigemptyset(&action.sa_mask);
	action.sa_flags = 0;
	action.sa_handler = sigHandler;
	sigaction(SIGCHLD, &action, NULL);
	
	if(argc<2){
		fprintf(stderr,"Numero parametri insufficiente\n");
		exit(EXIT_FAILURE); 
	}
	nChild = atoi(argv[1]);
	
	for(i=0; i<nChild; i++)
   	if (fork() == 0){
         printf ("%d° Processo con PID= %d, Figlio di PID= %d \n", i,getpid(), getppid());
         exit(EXIT_SUCCESS);
      }
	
	for(i=0; i<N; i++);
	
	exit(EXIT_SUCCESS);
}
