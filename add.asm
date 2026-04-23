org 0x0000
a: word $array
b: word ?
i: word ?
j: word ?
start: ld #5
dec
st i
ld a
inc
st b
A: ld i
st j
B: ld (a)
push
ld (b)
push
call $sort
pop
st (b)+
pop
st (a)+
loop j
jump B
ld a
sub i
st a
ld b
sub i
st b
loop i
jump A
hlt

org 0x0020
array: word 0x0016, 0x41AA, 0x9120, 0x0004, 0xFF12

org 0x0100
sort: ld (sp+2)
cmp (sp+1)
blt z
swam (sp+1)
st (sp+2)
z: ret
x: word ?