#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<signal.h>
#include<sys/types.h>

void sigHandler_Parent(int sig){
	printf("4. PADRE: Ho ricevuto SIGUSR2\n");
	return;
}

void sigHandler_Child(int sig){
	if(sig==SIGUSR1){
		printf("2. FIGLIO: Ho ricevuto SIGUSR1 (%d) da mio PADRE\n", sig);
		printf("3. FIGLIO: Mando SIGUSR2 a mio PADRE\n");
		kill(getppid(),SIGUSR2);
		return;
	}
	if(sig==SIGTERM){
		printf("6. FIGLIO: Mio PADRE mi ha terminato !!!\n");
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
	sigaction(SIGTERM, &action, NULL);
	
	if((pid=fork()) == -1) exit(EXIT_FAILURE);
	if(pid == 0)
		for(;;)
			pause();
	
	action.sa_handler = sigHandler_Parent;
	sigaction(SIGUSR2, &action, NULL);
	action.sa_handler = SIG_DFL;
	sigaction(SIGUSR1, &action, NULL);
	sigaction(SIGTERM, &action, NULL);
	
	printf("1. PADRE: Invio SIGUSR1 al processo FIGLIO\n");
	kill(pid, SIGUSR1);
	pause();
	printf("5. PADRE: Invio SIGTERM al processo FIGLIO\n");
	kill(pid, SIGTERM);
	
	wait(0);
	exit(EXIT_SUCCESS);
}
