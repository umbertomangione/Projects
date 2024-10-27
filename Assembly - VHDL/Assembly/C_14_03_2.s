;int elabora(char *vet, int d)
;{ int i,conta;
;conta=0;
;for(i=0;i<d;i++)
;if(vet[i]<58)
;conta++;
;return (conta % 4);
;}
;main() {
;char VAL[32];
;int i,ris,numero;
;for(i=0;i<3;i++) {
;printf("Inserisci un numero\n");
;scanf("%d",&numero);
;if(numero<4)
;ris = numero;
;else {
;printf("Inserisci una stringa con almeno %d caratteri\n",numero);
;scanf("%s",VAL);
;ris=elabora(VAL,strlen(VAL));
;}
;printf(" Ris[%d]= %d \n",i,ris);
;}
;}

.data
VAL: .space 32

mes1: .asciiz "Inserisci un numero\n"
mes2: .asciiz "Inserisci una stringa con almeno %d caratteri\n"
mes3: .asciiz " Ris[%d]= %d \n"

ps5: .space 8
val1: .space 8
val2: .space 8

ps3: .word 0
inds3: .space 8
bs3: .word 32

stack: .space 32

.code
daddi $sp, $0, stack
daddi $sp, $sp, 32

;for(i=0;i<3;i++) {
daddi $s0, $0, 0
for:	slti $t0, $s0, 3
	beq $t0, $0, fine_for

	;printf("Inserisci un numero\n");
	daddi $t0, $0, mes1
	sd $t0, ps5($0)
	daddi r14, $0, ps5
	syscall 5

	;scanf("%d",&numero);
	jal input_unsigned
	move $a1, r1

	;if(numero<4)
	slti $t0, $a1, 4
	beq $t0, $0, else

	;ris = numero;
	move $a2, $a1
	
	j printf
;else {
;printf("Inserisci una stringa con almeno %d caratteri\n",numero);	
else:	sd $a1, val1($0)
	daddi $t0, $0, mes2
	sd $t0, ps5($0)
	daddi r14, $0, ps5
	syscall 5
	
	;scanf("%s",VAL);
	daddi $t0, $0, VAL
	sd $t0, inds3($0)
	daddi r14, $0, ps3
	syscall 3
	move $a0, r1

	;ris=elabora(VAL,strlen(VAL));
	daddi $s3, $0, VAL
	jal elabora
	move $a2, r1

;printf(" Ris[%d]= %d \n",i,ris);
printf:	sd $s0, val1($0)
	sd $a2, val2($0)
	daddi $t0, $0, mes3
	sd $t0, ps5($0)
	daddi r14, $0, ps5
	syscall 5

	;i++
	daddi $s0, $s0, 1
	j for

fine_for:	syscall 0

elabora:	daddi $sp, $sp, -8
	sd $s0, 0($sp)

	;conta=0;
	daddi $s2, $0, 0

;for(i=0;i<d;i++)
for2:	slt $t0, $s0, $a0
	beq $t0, $0, return

	;if(vet[i]<58)
	dadd $t0, $s3, $s0
	lbu $t1, 0($t0)
	slti $t0, $t1, 58
	daddi $s0, $s0, 1
	beq $t0, $0, for2
	
	;conta++;
	daddi $s2, $s2, 1
	j for2

return:	daddi $s3, $0, 4
	div $s2, $s3
	mflo $s4
	mult $s4, $s3
	mflo $s4
	sub $s2, $s2, $s4 
	move r1, $s2
	ld $s0, 0($sp)
	daddi $sp, $sp, 8
	jr $ra
#include input_unsigned.s
