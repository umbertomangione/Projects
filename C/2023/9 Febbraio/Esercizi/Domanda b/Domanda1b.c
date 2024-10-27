#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<signal.h>
#include<sys/types.h>

void sigHandler_Child(int sig){
	if(sig==SIGUSR1){
		printf("FIGLIO: Ho ricevuto SIGUSR1 (%d) da mio PADRE\n", sig);
		return;
	}
	if(sig==SIGUSR2){
		printf("FIGLIO: Ho ricevuto SIGUSR2 (%d) da mio PADRE\n", sig);
		return;
	}
	if(sig==SIGTERM){
		printf("FIGLIO: Mio PADRE mi ha terminato con %d !!!\n", sig);
		exit(EXIT_SUCCESS);
	}
}

int main(void){
	pid_t pid;
	struct sigaction action;
	
	sigemptyset(&action.sa_mask);
	action.sa_flags = 0;
	action.sa_handler = sigHandler_Child;
	sigaction(SIGUSR1, &action, NULL);
	sigaction(SIGUSR2, &action, NULL);
	sigaction(SIGTERM, &action, NULL);
	
	if((pid=fork()) == -1) exit(EXIT_FAILURE);
	if(pid == 0)
		for(;;)
			pause();
	
	printf("PADRE: Invio SIGUSR1 al processo FIGLIO\n");
	kill(pid, SIGUSR1);
	sleep(2);
	printf("PADRE: Invio SIGUSR1 al processo FIGLIO\n");
	kill(pid, SIGUSR2);
	sleep(2);
	printf("PADRE: Invio SIGTERM al processo FIGLIO\n");
	kill(pid, SIGTERM);
	
	printf("PADRE: Ho finito il mio lavoro, vado \n");
	
	wait(0);
	exit(EXIT_SUCCESS);
}
