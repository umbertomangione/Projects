;int calcola(char *a0)
;{ int j,cnt;
;j=0;
;cnt=0;
;do{
;if(a0[j]<58)
;cnt++;
;j++;
;} while (a0[j]!=48)
;return cnt;
;}
;main() {
;char ST[16];
;int i,num,ris;
;for(i=0;i<4;i++)
;printf("Inserisci una stringa terminata con il carattere 0 \n");
;scanf("%s",ST);
;printf("Inserisci un numero");
;scanf("%d",&num);
;ris= calcola(ST)+num;
;printf(" Valore= %d \n",ris);
;}
;}

.data
ST: .space 16

mes1: .asciiz "Inserisci una stringa terminata con il carattere 0 \n"
mes2: .asciiz "Inserisci un numero"
mes3: .asciiz " Valore= %d \n"

ps5: .space 8
val1: .space 8

ps3: .word 0
inds3: .space 8
bs3: .word 16

.code
;for(i=0;i<4;i++)
daddi $s0, $0, 0
for:	slti $t0, $s0, 4
	beq $t0, $0, fine_for

	;printf("Inserisci una stringa terminata con il carattere 0 \n");
	daddi $t0, $0, mes1
	sd $t0, ps5($0)
	daddi r14, $0, ps5
	syscall 5

	;scanf("%s",ST);
	daddi $t0, $0, ST
	sd $t0, inds3($0)
	daddi r14, $0, ps3
	syscall 3
	move $s1, r1

	;printf("Inserisci un numero");
	daddi $t0, $0, mes2
	sd $t0, ps5($0)
	daddi r14, $0, ps5
	syscall 5

	;scanf("%d",&num);
	jal input_unsigned
	move $a1, r1

 	;ris= calcola(ST)+num;
	daddi $a0, $0, ST
	jal calcola
	move $a2, r1
	dadd $a2, $a2, $a1

	;printf(" Valore= %d \n",ris);
	sd $a2, val1($0)	
	daddi $t0, $0, mes3
	sd $t0, ps5($0)
	daddi r14, $0, ps5
	syscall 5 

	daddi $s0, $s0, 1
	j for

fine_for:	syscall 0

;int calcola(char *a0)
;{ int j,cnt;
;j=0;
calcola:	daddi $s2, $0, 0
	
	;cnt=0;
	daddi $s3, $0, 0
	
;do{
;if(a0[j]<58)
do:	dadd $t0, $a0, $s2
	lbu $t1, 0($t0)
	slti $t0, $t1, 58
	beq $t0, $0, j++

	;cnt++;
	daddi $s3, $s3, 1

j++:	daddi $s2, $s2, 1
	
	;} while (a0[j]!=48)
	dadd $t0, $a0, $s2
	lbu $t1, 0($t0)
	daddi $s4, $0, 48
	bne $t1, $s4, do

	;return cnt;
	move r1, $s3
	jr $ra
#include input_unsigned.s
