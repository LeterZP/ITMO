import sys

print("Введите набор цифр в формате 'rririii' (где r-проверочные биты; i-информационные биты):", end=" ")
while True:
    try:
        s = input().replace(" ", "")
        if len(s) != 7:
            print("Число не соответствует заданному форамату")
            print("Пожалуйста, повторите ввод:", end=" ")
            continue
        correct = True
        for i in s:
            if i not in "01":
                print("Число не соответствует заданному форамату")
                print("Пожалуйста, повторите ввод:", end=" ")
                correct = False
                break
        if correct: break
    except EOFError:
        print("Завершение работы.")
        sys.exit()
S = str()
S += str((int(s[0]) + int(s[2]) + int(s[4]) + int(s[6]))%2)
S += str((int(s[1]) + int(s[2]) + int(s[5]) + int(s[6]))%2)
S += str((int(s[3]) + int(s[4]) + int(s[5]) + int(s[6]))%2)
err_bit = int()
if S.count("1") == 0: err_bit = 7
elif S.count("1") == 1: err_bit = 2**int(S.index("1")) - 1
elif S.count("1") == 2: err_bit = int(S[::-1], 2)-1
else: err_bit = 6
if err_bit == 7: print("Передаваемое число - " + s[2] + s[4] + s[5] + s[6] + "! Ошибок нет!")
else:
    s = s[:err_bit] + str((int(s[err_bit]) + 1)%2) + s[err_bit+1:]
    print("Передаваемое число - " + s[2] + s[4] + s[5] + s[6] + "! Ошибка была в бите " + str(err_bit + 1) + "!")