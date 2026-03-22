;поиск среднего арифметического положительных чисел массива
ORG 0x0000
result: WORD ? ;результат
toresult: WORD ? ;числа, прибовляемые в результат
timecount: WORD ? ;временная сумма для вычитания
count: WORD ? ;кол-во сложенных чисел
arraystart: WORD 0x0032 ;начальный элемент массива
arraynow: WORD ? ;текущий элемент массива
i: WORD ? ;счетчик цикла
timeres: WORD ? ;временнный результат
START: CLA
ST timeres
LD #6
ST i
ADD arraystart
ST arraynow
A: LD -(arraynow)
BMI B
BEQ B
ADD timeres
ST timeres
LD count
INC
ST count
B: LOOP i
JUMP A
CLA
ST result
E: LD count
ST timecount
LD timeres
CMP timecount
BMI F
LD #1
ST toresult
C: LD timecount
ASL
CMP timeres
BHIS D
ST timecount
LD toresult
ASL
ST toresult
JUMP C
D: LD toresult
ADD result
ST result
LD timeres
SUB timecount
ST timeres
JUMP E
F: HLT
WORD 0
WORD 22
WORD 148
WORD -21
WORD -14
WORD 12