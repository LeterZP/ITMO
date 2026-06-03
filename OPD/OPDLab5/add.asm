org 0x10

start: in 0x19 ;проверка готовности
and #0x40
beq start

in 0x18 ;загрузка числа
st buf

cmp #0xA ;завершение
bne tr
hlt

tr: add #0x80 ;перевод
and mask

out 0xC ;вывод

swab ;сохранение
add buf
swab
st (res)+
jump start

buf: word ? ;память
mask: word 0x00FF
res: word $result
result: word 16 dup (?)
