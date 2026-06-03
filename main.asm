org 0x0
v0: word $def, 0x180
v1: word $int2, 0x180
v2: word $int3, 0x180
v3: word $def, 0x180
v4: word $def, 0x180
v5: word $def, 0x180
v6: word $def, 0x180
v7: word $def, 0x180
def: iret

org 0x20
X: word 0x18

start: di
ld #0x9
out 5
ld #0xA
out 7
jump $prog

prog: ei
declp: 
ld X
dec
st X
jump declp

int2:
ld X
hlt                                           ; отладка ВУ-2
in 4
and X
hlt                                           ; отладка ВУ-2
call $check
hlt                                           ; отладка ВУ-2
st X
iret

int3: 
ld X
hlt                                           ; отладка ВУ-3
asl
asl
add X
neg
sub #4
hlt                                           ; отладка ВУ-3
out 6
hlt                                           ; отладка ВУ-3
ld X
iret

org 0x100
min: word 0xFFE6
max: word 0x18
check: cmp min
bpl A
ld min
A:cmp max
bmi B
ld max
B: ret