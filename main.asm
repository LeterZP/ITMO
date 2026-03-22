ORG 0x0335
arraystart: WORD 0x0346
arraynow: WORD ?
i: WORD ?
result: WORD ?
START: CLA
ST result
LD #5
ST i
ADD arraystart
ST arraynow
A: LD -(arraynow)
BPL B
ADD result
ST result
B: LOOP i
JUMP A
WORD 0xFFF7
WORD 0x0009
WORD 0xFFB4
WORD 0x0000
WORD 0xFFCA
WORD 0xFFFD