sign = 0
while True:
    print("Введите число в системе счисления Фибоначчи:", end=" ")
    num = input()
    if len(num) > 0 and num[0] == "-":
        sign = 1
        num = num[1:]
    flag = 0
    for i in num:
        if i in "01" and not "11" in num:
            continue
        else:
            print("Число не соответствует шаблону.")
            flag = 1
            break
    if flag == 0 and len(num) > 0: break
fib = [1, 1]
for i in range(len(num)-2):
    fib.append(fib[i] + fib[i+1])
if sign == 0:
    print("Число " + num + " в десятичной системе счисления - это ", end="")
    print(sum([int(num[i])*fib[len(fib)-i-1] for i in range(len(num))]))
else:
    print("Число -" + num + " в десятичной системе счисления - это ", end="")
    print(-sum([int(num[i]) * fib[len(fib) - i - 1] for i in range(len(num))]))