;int
;elabora(char a0, int a1)
;{ int t=a0-48;
;if(t < a1)
;return t;
;else return a1;
;}
;main() {char STRINGA[16];
;int A[16], i, n;
;printf("Inserire una stringa \n")
;scanf("%s",STRINGA);
;for(i=0;i<strlen(STRINGA);
;printf("Inserisci un numero (0 termina l’esecuzione n");
;scanf("%d",&n)
;if(n!=0)
;{A[i]=elabora(STRINGA[i],n);
;printf("A[%d]=%d", i,A[i]);
;}
;}
;}

.data
STRINGA: .space 16
A: .space 16

mes1: .asciiz "Inserire una stringa \n"
mes2: .asciiz "Inserisci un numero (0 termina l’esecuzione) \n"
mes3: .asciiz "A[%d]=%d"

ps3: .word 0
is3: .space 8
ds3: .word 16 

ps5: .space 8
val1: .space 8
val2: .space 8

stack: .space 32

.code
daddi $sp, $0, stack
daddi $sp, $sp, 32

;printf("Inserire una stringa \n")
daddi $t0, $0, mes1
sd $t0, ps5($0)
daddi r14, $0, ps5
syscall 5

;scanf("%s",STRINGA);
daddi $t0, $0, STRINGA
sd $t0, is3($0)
daddi r14, $0, ps3
syscall 3 
move $a0, r1

;for(i=0;i<strlen(STRINGA);
daddi $s1, $0, 0
for:  	slt $t0, $s1, $a0
	beq $t0, $0, fine_for

	;printf("Inserisci un numero (0 termina l’esecuzione n");
	daddi $t0, $0, mes2
	sd $t0, ps5($0)
	daddi r14, $0, ps5
	syscall 5

	;scanf("%d",&n)
	jal input_unsigned
	move $a1, r1

	;if(n!=0)
	beq r1, $0, incr
	
	;{A[i]=elabora(STRINGA[i],n);
	jal elabora

	;printf("A[%d]=%d", i,A[i]);
	sd $s1, val1($0)
	sd r1, val2($0)
	daddi $t1, $0, mes3
	sd $t1, ps5($0)
	daddi r14, $0, ps5
	syscall 5

incr:  	daddi $s1, $s1, 1
	j for

fine_for:	syscall 0

;int t=a0-48;
elabora: 	dadd $t3, $a0, $s1
	lbu $t4, 0($t3)
	daddi $t0, $t4, -48
	
	;if(t < a1)
	slt $t1, $t0, $a1
	beq $t1, $0, return
	
	;else return t;
	move r1, $t0
	jr $ra	

;return a1;
return:	move r1, $a1
	jr $ra	
#include input_unsigned.s
