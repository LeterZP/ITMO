def json_to_bin(file_name: str) -> None:
    try:
        if file_name.split(".")[-1] != "json":
            print("Ошибка: тип файла должен быть json")
            exit()
        with open(file_name, "r", encoding="utf-8") as file:
            json_file = file.read()
        json_file = parse_value(json_file)
        bin_file = str()
        for i in json_file:
            character = bin(ord(i))[2:]
            bin_file += character + " "
        bin_file = str.encode(bin_file[:-1])
        with open(".".join(file_name.split(".")[:-1])+".bin", "wb") as file:
            file.write(bin_file)
    except Exception as e:
        print("Ошибка: ", e)


def parse_value(value: str) -> str:
    value = clear_spaces(value)
    new_value = str()
    try:
        value = clear_spaces(value)
        if value[0] == "{" and value[-1] == "}":
            new_value += "o"
            value = parse_object(value)
        elif value[0] == "[" and value[-1] == "]":
            new_value += "a"
            value = parse_array(value)
        elif value[0] == '"' and value[-1] == '"':
            new_value += "s"
            value = parse_string(value)
        elif all([i in "+-.0123456789eE" for i in value]):
            new_value += "n"
            value = parse_number(value)
        elif value == "true":
            value = "t"
        elif value == "false":
            value = "f"
        elif value == "null":
            value = "v"
        else:
            print("Ошибка при определении типа элемента: невозможно определить тип: ", value)
            exit()
        new_value += value
        return new_value
    except Exception as e:
        print("Ошибка при определении типа элемента: ", e)


def parse_object(object: str) -> str:
    def error():
        print("Ошибка в определении объекта: последовательность " + object + " не является объектом")
        exit()
    try:
        if len(object) <= 2:
            object = "0"
        else:
            object = object[1:-1]
            new_object = dict()
            while len(object) > 0:
                object = clear_spaces(object)
                key = str()
                value = str()
                if object[0] == '"':
                    for i in range(1, len(object)):
                        if object[i] == '"':
                            if object[i-1] == "\\":
                                continue
                            key = object[:i+1]
                            object = object[i+1:]
                            break
                        elif i + 2 == len(object): error()
                object = clear_spaces(object)
                if len(object) == 0: error()
                if object[0] != ":": error()
                object = clear_spaces(object[1:])
                if object.count(",") == 0:
                    value = object
                if len(object) == 1: error()
                if object[0] not in '{["':
                    value = object[:object.index(",")]
                    object = object[object.index(",")+1:]
                elif object[0] == "{":
                    c = 0
                    for i in range(len(object)):
                        if object[i] == "{":
                            c += 1
                        elif object[i] == "}":
                            c -= 1
                        if c == 0:
                            value = object[:i + 1]
                            object = clear_spaces(object[i + 1:])
                            if object[0] == ",":
                                object = clear_spaces(object[1:])
                            break
                        elif i + 2 == len(object):
                            value = object
                            object = ""
                            break
                elif object[0] == "[":
                    c = 0
                    for i in range(len(object)-1):
                        if object[i] == "[":
                            c += 1
                        elif object[i] == "]":
                            c -= 1
                        if c == 0:
                            value = object[:i + 1]
                            object = clear_spaces(object[i + 1:])
                            if object[0] == ",":
                                object = clear_spaces(object[1:])
                            break
                        elif i + 2 == len(object):
                            value = object
                            object = ""
                            break
                elif object[0] == '"':
                    for i in range(1, len(object)):
                        if object[i] == '"':
                            if object[i-1] == "\\":
                                continue
                            value = object[:i+1]
                            object = clear_spaces(object[i+1:])
                            if object[0] == ",":
                                object = clear_spaces(object[1:])
                            break
                        elif i + 2 == len(object):
                            value = object
                            object = ""
                            break
                else: error()
                new_object.setdefault(key, value)
            object = str(len(new_object.keys()))
            for i in [[list(new_object.keys())[i], list(new_object.values())[i]] for i in range(len(new_object.keys()))]:
                key = parse_value(i[0])
                value = parse_value(i[1])
                object += " " + key + " " + value
        return object
    except Exception as e:
        print("Ошибка при определении объекта: ", e)


def parse_array(array: str) -> str:
    def error():
        print("Ошибка в определении массива: последовательность " + array + " не является массивом")
        exit()
    try:
        if len(array) <= 2:
            array = "0"
        else:
            new_array = list()
            array = array[1:-1]
            while len(array) > 0:
                array = clear_spaces(array)
                if array.count(",") == 0:
                    new_array.append(array)
                    break
                if len(array) == 1: error()
                if array[0] not in '{["':
                    new_array.append(array[:array.index(",")])
                    array = array[array.index(",")+1:]
                elif array[0] == "{":
                    c = 0
                    for i in range(len(array)):
                        if array[i] == "{":
                            c += 1
                        elif array[i] == "}":
                            c -= 1
                        if c == 0:
                            new_array.append(array[:i + 1])
                            array = clear_spaces(array[i + 1:])
                            if array[0] == ",":
                                array = clear_spaces(array[1:])
                            break
                        elif i + 2 == len(array):
                            new_array.append(array)
                            array = ""
                            break
                elif array[0] == "[":
                    c = 0
                    for i in range(len(array)):
                        if array[i] == "[":
                            c += 1
                        elif array[i] == "]":
                            c -= 1
                        if c == 0:
                            new_array.append(array[:i + 1])
                            array = clear_spaces(array[i + 1:])
                            if array[0] == ",":
                                array = clear_spaces(array[1:])
                            break
                        elif i + 2 == len(array):
                            new_array.append(array)
                            array = ""
                            break
                elif array[0] == '"':
                    for i in range(1, len(array)):
                        if array[i] == '"':
                            if array[i-1] == "\\":
                                continue
                            new_array.append(array[:i+1])
                            array = array[i+1:]
                            break
                        elif i + 2 == len(array):
                            new_array.append(array)
                            array = ""
                            break
                else: error()
            array = str(len(new_array))
            for i in new_array:
                array += " " + parse_value(i)
        return array
    except Exception as e:
        print("Ошибка при определении массива: ", e)


def parse_string(string: str) -> str:
    def error():
        print("Ошибка в определении строки: последовательность " + string + " не является строкой")
        exit()
    try:
        for i in range(len(string)-1):
            if string[i] == "\\" and i != len(string) - 1:
                if string[i+1] not in '"\\/bfnrt':
                    continue
                elif string[i+1] == "u" and i + 6 < len(string):
                    for j in range(4):
                        if string[i+2+j].lower() not in "0123456789abcdef": error()
                else: error()
        return string
    except Exception as e:
        print("Ошибка при определении строки: ", e)


def parse_number(number: str) -> str:
    def error():
        print("Ошибка в определении числа: последовательность " + number + " не является числом")
        exit()
    checked_number = str()
    try:
        if number.lower()[-1] == "e": error()
        if "e" in number.lower():
            num1, num2 = number[:number.lower().find("e")], number[number.lower().find("e") + 1:]
        else:
            num1, num2 = number, "not"
        if all([i in "-0123456789." for i in num1]):
            if num1.count(".") == 0 and num1.count("-") == 0:
                if len(num1) > 0:
                    checked_number += num1
                else: error()
            elif num1.count(".") == 1 and num1.count("-") == 1:
                if len(num1) > 2 and num1[0] == "-" and num1[-1] != ".":
                    checked_number += num1
                else: error()
            elif num1.count("-") == 1:
                if len(num1) > 1 and num1[0] == "-":
                    checked_number += num1
                else: error()
            elif num1.count(".") == 1:
                if len(num1) > 1 and num1[0] != "." and num1[-1] != ".":
                    checked_number += num1
                else: error()
            else: error()
        else: error()
        if num2 != "not":
            checked_number += "e"
            if all([i in "+-0123456789" for i in num2]):
                if (num2.count("+") + num2.count("-")) == 1 and len(num2) > 1:
                    if num2[0] == "+" or num2[0] == "-":
                        checked_number += num2
                    else: error()
                elif num2.count("+") + num2.count("-") == 0:
                    checked_number += num2
                else: error()
            else: error()
        return checked_number
    except Exception as e:
        print("Ошибка при определении числа: ", e)


def clear_spaces(line: str) -> str:
    while line[0] in "\r\t\n ":
        line = line[1:]
    while line[-1] in "\r\t\n ":
        line = line[:-1]
    return line


if __name__ == "__main__":
    json_to_bin("schedule.json")