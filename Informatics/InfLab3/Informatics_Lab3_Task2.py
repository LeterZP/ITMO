# Author = Bosenko Boris Igorevich
# Group = P3115
# Date = 12.11.2025

import re

def mail(email):
    if re.fullmatch(r'[\w.]*@[a-zA-Zа-яА-я]+(\.[a-zA-Zа-яА-я]+)+', email):
        return email[email.index("@") + 1:]
    else:
        return "Fail!"

if __name__ == "__main__":
    try:
        tests = [
            "day@dfgdfg..",
            "ind_out.us@out_because.us",
            "2let@it.be.me",
            "no_here.there@and.there",
            "1322list@another8916.21"
        ]
        for test in tests:
            print(mail(test), end="   ")
        print()
        results = [
            "not.here",
            "Fail!",
            "it.be.me",
            "and.there",
            "Fail!"
        ]
        print(*results, sep="   ")
    except Exception as error:
        print(f"Ошибка: {error}")
