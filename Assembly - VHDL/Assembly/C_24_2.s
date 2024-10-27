;int calcola(char *st, int d, int val)
;{ int j,cnt;
;cnt=0;
;for(j=0;j<d;j++)
;if(st[j]-48<val)
;cnt++;
;else cnt+=2;
;return cnt;
;}
;main() {
;char ST[16];
;int i,num,ris;
;i=0;
;do{
;printf(printf("Inserisci una stringa di soli numeri\n");
;scanf("%s",ST);
;printf("Inserisci un numero a una cifra");
;scanf("%d",&num);
;ris= calcola(ST,strlen(ST),num);
;printf(" Valore= %d \n",ris);
;i++;
;} while(i<3);
;}

.data
ST: .space 16

mes1: .asciiz "Inserisci una stringa di soli numeri\n"
mes2: .asciiz "Inserisci un numero a una cifra"
mes3: .asciiz "Valore= %d \n"

ps5: .space 8
val: .space 8

ps3: .word 0
inds3: .space 8
bs3: .word 16

.code
;i=0;
daddi $s0, $0, 0

;do{
;printf(printf("Inserisci una stringa di soli numeri\n");
do:	daddi $t0, $0, mes1
	sd $t0, ps5($0)
	daddi r14, $0, ps5
	syscall 5

	;scanf("%s",ST);
	daddi $t0, $0, ST
	sd $t0, inds3($0)
	daddi r14, $0, ps3
	syscall 3
	move $s1, r1

	;printf("Inserisci un numero a una cifra");
	daddi $t0, $0, mes2
	sd $t0, ps5($0)
	daddi r14, $0, ps5
	syscall 5

	;scanf("%d",&num);
	jal input_unsigned
	move $a1, r1

	;ris= calcola(ST,strlen(ST),num);
	daddi $a0, $0, ST
	jal calcola
	move $a2, r1

	;printf(" Valore= %d \n",ris);
	sd $a2, val($0)
	daddi $t0, $0, mes3
	sd $t0, ps5($0)
	daddi r14, $0, ps5
	syscall 5

	;i++;
	daddi $s0, $s0, 1
	
	;} while(i<3);
	slti $t0, $s0, 3
	bne $t0, $0, do

fine_for: syscall 0

	;cnt=0;
calcola:	daddi $s2, $0, 0
	
	;j=0;
	daddi $s3, $0, 0

	;for(j=0;j<d;j++)
for:	slt $t0, $s3, $s1
	beq $t0, $0, return

	;if(st[j]-48<val)
	dadd $t0, $a0, $s3
	lbu $t1, 0($t0)
	daddi $t1, $t1, -48
	slt $t0, $t1, $a1
	beq $t0, $0, else
	
	;cnt++;
	daddi $s2, $s2, 1
	j for	

else:	daddi $s2, $s2, 2	
	j for	 

return:	move r1, $s2
	jr $ra
		
#include input_unsigned.s
