#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<signal.h>
#include<sys/types.h>

void sigHandler_Child(int sig){
	if(sig==SIGUSR1){
		printf("FIGLIO: Ho ricevuto SIGUSR1 (%d) quindi stampo il mio PID = %d e termino\n", sig, getpid());
		exit(EXIT_SUCCESS);
	}
}

int main(void){
	pid_t pid[5];
	int i=0;
	struct sigaction action;
	
	sigemptyset(&action.sa_mask);
	action.sa_flags = 0;
	action.sa_handler = sigHandler_Child;
	sigaction(SIGUSR1, &action, NULL);
	
	for(i=0;i<5;i++){
		if((pid[i]=fork()) == -1) exit(EXIT_FAILURE);
		if(pid[i] == 0)
			for(;;)
				pause();
	}
	
	for(i=0;i<5;i++){
		printf("PADRE: Invio SIGUSR1 al processo FIGLIO con PID = %d\n", pid[i]);
		kill(pid[i], SIGUSR1);
	}
	
	printf("Mentre aspetto i figli lavoro\n");
	
	for(i=0;i<1;i++){
		sleep(10);
	}
	
	printf("\nPADRE: Ho finito il mio lavoro, vado\n");
	
	wait(0);
	exit(EXIT_SUCCESS);
}
