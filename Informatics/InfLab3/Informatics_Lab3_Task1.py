# Author = Bosenko Boris Igorevich
# Group = P3115
# Date = 12.11.2025

import re

def haiku(text):
    sl = '[аеёиоуыэюя]'
    if not(re.fullmatch(r'[^/]*/[^/]*/[^/]*', text)):
        return "Не хайку. Должно быть 3 строки."
    elif re.match(sl+"{5}/"+sl+"{7}/"+sl+"{5}", "".join(re.findall('[аеёиоуыэюя/]', str.lower(text)))):
        return "Хайку!"
    else:
        return "Не хайку."

if __name__ == "__main__":
    try:
        tests = [
            "Ветер за окном. / Дождь слегка бьёт по окнам. / Сегодня пишу псж.",
            "   Один день назадю / Был сильный дождь. / Много луж на улице.",
            "Еще один день. / Очень скучно.",
            "Один, четыре. / Два, три, пять, шесть, семь, восемь. / Девять, десять, ноль.     ",
            "Четыре. / Двенадцать. / Восемь. / Пятнадцать. / Тринадцать."
        ]
        for test in tests:
            print(haiku(test), end="   ")
        print()
        results = [
            "Хайку!",
            "Не хайку.",
            "Не хайку. Должно быть 3 строки.",
            "Хайку!",
            "Не хайку. Должно быть 3 строки."
        ]
        print(*results, sep="   ")
    except Exception as error:
        print(f"Ошибка: {error}")