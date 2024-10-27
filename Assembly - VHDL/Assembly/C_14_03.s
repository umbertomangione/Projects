;int elabora(char *vet, int d)
;{ int i,pari;
;pari=0;
;for(i=0;i<d;i++)
;if(vet[i]%2==0)
;pari++;
;return pari;
;}
;main() {
;char VAL[32];
;int i,ris,numero;
;for(i=0;i<3;i++) {
;printf("Inserisci una stringa con almeno 4 caratteri n");
;scanf("%s",VAL);
;if(strlen(VAL)<4)
;{ printf("Inserisci un numero maggiore di %d \n",strlen(VAL));
;scanf("%d",&ris);
;else
;ris=elabora(VAL,strlen(VAL));
;printf(" Ris[%d]= %d n",i,ris);
;}

.data
VAL: .space 32

mes1: .asciiz "Inserisci una stringa con almeno 4 caratteri \n"
mes2: .asciiz "Inserisci un numero maggiore di %d \n"
mes3: .asciiz " Ris[%d]= %d\n"

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
	
	;printf("Inserisci una stringa con almeno 4 caratteri n");
	daddi $t0, $0, mes1
	sd $t0, ps5($0)
	daddi r14, $0, ps5
	syscall 5

	;scanf("%s",VAL);
	daddi $t0, $0, VAL
	sd $t0, inds3($0)
	daddi r14, $0, ps3
	syscall 3
	move $s1, r1

	;if(strlen(VAL)<4)
	slti $t0, $s1, 4
	beq $t0, $0, else
	
	;{ printf("Inserisci un numero maggiore di %d \n",strlen(VAL));
	sd $s1, val1($0)
	daddi $t0, $0, mes2
	sd $t0, ps5($0)
	daddi r14, $0, ps5
	syscall 5
	
	;scanf("%d",&ris);
	jal input_unsigned
	move $a1, r1

	;printf(" Ris[%d]= %d n",i,ris);
printf:	sd $s0, val1($0)
	sd $a1, val2($0)
	daddi $t0, $0, mes3
	sd $t0, ps5($0)
	daddi r14, $0, ps5
	syscall 5

	;i++
	daddi $s0, $s0, 1
	j for
 
;ris=elabora(VAL,strlen(VAL));
else:	daddi $a0, $0, VAL
	jal elabora
	move $a1, r1
	j printf

fine_for: syscall 0

elabora:	daddi $sp, $sp, -8
	sd $s0, 0($sp)

	;pari=0;
	daddi $s2, $0, 0

	;for(i=0;i<d;i++)
	daddi $s0, $0, 0 ;i=0
for2:	slt $t0, $s0, $s1
	beq $t0, $0, return
		
	;if(vet[i]%2==0)
	dadd $t0, $a0, $s0
	lbu $t1, 0($t0) ;$t1 = vet[i]
	andi $t0, $t1, 1
	daddi $s0, $s0, 1
	bne $t0, $0, for2

	;pari++;
	daddi $s2, $s2, 1
	j for2
	
return:	move r1, $s2
	ld $s0, 0($sp)
	daddi $sp, $sp, 8
	jr $ra
 
#include input_unsigned.s
