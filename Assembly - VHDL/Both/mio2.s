;int elabora(char *a0, int a1)
;{   int i,conta;
;	conta=0;
;	for(i=0;i<a1;i++)
;if(a0[i]<57)  
 ;    		  conta++;
 ;    return conta;            
;}
;main() {char STR[16];
;        int i,val, num;     
   
;for(i=0;i<3;i++) {
		
;	 printf("Inserisci una stringa con almeno 4 caratteri\n");
 ;     scanf("%s",STR);
;	 if(strlen(STR)>=4)
 ;      val= elabora(STR,strlen(STR));
 ;      else {
;printf("Inserisci un numero "); 
 ;    		scanf("%d",&val);
  ; }
   ;    printf(" Val = %d \n",val); 
   ;  }  
;}

.data
STR: .space 16
mess1: .asciiz "Inserisci una stringa con almeno 4 caratteri\n"
mess2: .asciiz "Inserisci un numero "
mess3: .asciiz " Val = %d \n"

p1s3: .word 0
ind3: .space 8
num3: .word 16

p1s5: .space 8
val: .space 8

stack: .space 32

.code 
	daddi $sp, $0, stack
	daddi $sp, $sp, 32
;for(i=0;i<3;i++) {
	daddi $s0, $0, 0
forM: 	slti $t0, $s0, 3
	beq $t0, $0, fine
	
	;printf("Inserisci una stringa con almeno 4 caratteri\n")
	daddi $t0, $0, mess1
	sd $t0,  p1s5($0)
	daddi r14, $0, p1s5
	syscall 5

	;     scanf("%s",STR);
	daddi $t0, $0, STR
	sd $t0, ind3($0)
	daddi r14, $0, p1s3
	syscall 3
	move $a1, r1

	 ;if(strlen(STR)>=4)
	slti $t0, $a1, 4
	bne $t0, $0, else	

	;      val= elabora(STR,strlen(STR));	
	daddi $a0, $0, STR
	jal elabora	
	sd r1, val($0)
	j print

else:	
	;printf("Inserisci un numero ");
	daddi $t0, $0, mess2
	sd $t0, p1s5($0)
	daddi r14, $0, p1s5
	syscall 5

	;scanf("%d",&val);
	jal input_unsigned
	sd r1, val($0)
	
print:	
	;    printf(" Val = %d \n",val); 
	daddi $t0, $0, mess3
	sd $t0, p1s5($0)
	daddi r14, $0, p1s5
	syscall 5

incr:	daddi $s0, $s0, 1
	j forM

fine:  syscall 0

;int elabora(char *a0, int a1)
elabora:	daddi $sp, $sp, -16
	sd $s0, 0($sp)
	sd $s1, 8($sp)
	
	;conta=0;
	daddi $s0, $0, 0

	;for(i=0;i<a1;i++)
	daddi $s1, $0, 0 ;i=0

forf:	slt $t0, $s1, $a1
	beq $t0, $0, return

	;if(a0[i]<57) 
	;&a0[i]=a0+i= $a0+$s1
	dadd $t1, $a0, $s1
	lbu $t2, 0($t1)
	slti $t0, $t2, 57
	beq $t0, $0, incrf
	
	;conta++;	
	daddi $s0, $s0, 1

incrf:	daddi $s1, $s1, 1
	j forf

return:	move r1, $s0
	
	ld $s0, 0($sp)
	ld $s1, 8($sp)
	daddi $sp, $sp, 16

	jr $ra
	
#include input_unsigned.s
