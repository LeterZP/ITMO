org 0x05D6
start: cla
A: in 5
and #0x40
beq A
in 4
swab
st x
cmp stopsym
beq stop
B: in 5
and #0x40
beq B
in 4
or x
st x
and #0xFF
cmp stopsym
beq stop
ld x
st (next)+
jump start
stop: ld x
st (next)+
hlt
next: word $line
stopsym: word 0x0000
x: word 0x0000

org 0x0603
line: word ?