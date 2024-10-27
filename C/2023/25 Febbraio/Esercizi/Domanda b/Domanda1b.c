#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<signal.h>
#include<sys/types.h>

void sigHandler_Child(int sig){
	if(sig==SIGUSR1){
		printf("FIGLIO: Ho ricevuto SIGUSR1 (%d) quindi stampo il mio PID = %d\n", sig, getpid());
		return;
	}
	if(sig==SIGUSR2){
		printf("FIGLIO (%d): Ho ricevuto SIGUSR2 (%d) da mio PADRE, quindi termino\n",getpid(), sig);
		exit(EXIT_SUCCESS);
	}
}

int main(void){
	pid_t pid[3];
	int random,i=0;
	struct sigaction action;
	
	sigemptyset(&action.sa_mask);
	action.sa_flags = 0;
	action.sa_handler = sigHandler_Child;
	sigaction(SIGUSR1, &action, NULL);
	sigaction(SIGUSR2, &action, NULL);
	
	for(i=0;i<3;i++){
		if((pid[i]=fork()) == -1) exit(EXIT_FAILURE);
		if(pid[i] == 0)
			for(;;)
				pause();
	}
	
	for(i=0;i<6;i++){
		random = rand() % 2;
		printf("PADRE: Invio SIGUSR1 al processo FIGLIO con PID = %d\n", pid[random]);
		kill(pid[random], SIGUSR1);
		sleep(1);
	}
	
	for(i=0;i<3;i++){
		kill(pid[i], SIGUSR2);
	}
	
	printf("\nPADRE: Ho finito il mio lavoro, vado \n");
	
	wait(0);
	exit(EXIT_SUCCESS);
}
