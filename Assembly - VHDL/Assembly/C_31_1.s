;int processa(char *st, int d)
;{ int i;
;for(i=0;i<d;i++)
;if(st[i]>=58)
;break;
;return i;
;}
;main() {
;char STRNG[16];
;int i,val,num;
;for(i=0;i<3;i++) {
;do{ printf("Indica quanti caratteri (numeri) vuoi inserire (>=3)) \n");
;scanf("%d",&num);
;}while(num<3)
;printf("Inserisci la stringa con %d numeri \n",num);
;scanf("%s",STRNG);
;val=processa(STRNG,num);
;printf(" Valore= %d \n",val);
;}

.data
STRNG: .space 16

mes1: .asciiz "Indica quanti caratteri (numeri) vuoi inserire (>=3)) \n"
mes2: .asciiz "Inserisci la stringa con %d numeri \n"
mes3: .asciiz " Valore= %d \n"

ps5: .space 8
val1: .space 8

ps3: .word 0
inds3: .space 8 
bs3: .word 16
stack: .space 32

.code
daddi $sp, $0, stack
daddi $sp, $sp, 32

;i=0
daddi $s0, $0, 0

;for(i=0;i<3;i++) {;for(i=0;i<3;i++) {
for:	slti $t0, $s0, 3
 	beq $t0, $0, fine_for

	;do{ printf("Indica quanti caratteri (numeri) vuoi inserire (>=3)) \n");
	do:	daddi $t0, $0, mes1
		sd $t0, ps5($0)
		daddi r14, $0, ps5
		syscall 5

		;scanf("%d",&num);
		jal input_unsigned
		move $a1, r1

		;}while(num<3)
		slti $t0, $a1, 3
		bne $t0, $0, do

	;printf("Inserisci la stringa con %d numeri \n",num);
	sd $a1, val1($0)
	daddi $t0, $0, mes2
	sd $t0, ps5($0)
	daddi r14, $0, ps5
	syscall 5

	;scanf("%s",STRNG);
	daddi $t0, $0, STRNG
	sd $t0, inds3($0)
	daddi r14, $0, ps3
	syscall 3
	move $s1, r1

	;val=processa(STRNG,num);
	daddi $a0, $0, STRNG
	jal processa
	move $s2, r1	

	;printf(" Valore= %d \n",val);
	sd $s2, val1($0)
	daddi $t0, $0, mes3
	sd $t0, ps5($0) 
	daddi r14, $0, ps5
	syscall 5		

	;i++
	daddi $s0, $s0, 1
	j for

fine_for:	syscall 0

processa:	daddi $sp, $sp, -8
	sd $s0, 0($sp)
	
	;i=0
	daddi $s0, $0, 0

	;for(i=0;i<d;i++)
for2:	slt $t0, $s0, $a1
	beq $t0, $0, return

	;if(st[i]>=58)
	dadd $t0, $a0, $s0
	lbu $t1, 0($t0)
	slti $t0, $t1, 58
	bne $t0, $0, return

	;i++
	daddi $s0, $s0, 1
	j for2

return:	move r1, $s0
	ld $s0, 0($sp)
	daddi $sp, $sp, 8
	jr $ra

#include input_unsigned.s
