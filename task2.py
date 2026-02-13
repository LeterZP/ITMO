def bin_to_yaml(file_name):
    try:
        if file_name.split(".")[-1] != "bin":
            print("Ошибка: тип файла должен быть bin")
            exit()
        with open(file_name, "rb") as file:
            bin_file = file.read()
        yaml_file = str()
        bin_file = bin_file.decode().split(" ")
        for i in bin_file:
            yaml_file += chr(int(i, 2))
        yaml_file = unparse_value(yaml_file)
        with open(".".join(file_name.split(".")[:-1])+".yaml", "w", encoding="utf-8") as file:
            file.write(yaml_file)
    except Exception as e:
        print("Ошибка: ", e)


def unparse_value(value: str) -> str:
    try:
        new_value = str()
        while len(value) != 0:
            if value[0] == "o":
                object, value = unparse_object(value)
                new_value += object
            elif value[0] == "a":
                array, value = unparse_array(value)
                new_value += array
            elif value[0] == "s":
                for i in range(2, len(value)-1):
                    if value[i] == '"' and value[i-1] != "\\":
                        new_value += value[2:i-1]
                        if len(value[i:]) > 2:
                            value = value[i+2:]
                        else: value = ""
                        break
                    elif i + 1 == len(value)-1:
                        new_value += value[2:len(value)-1]
                        value = ""
            elif value[0] == "n":
                if value.count(" ") == 0:
                    new_value += value[1:] + "\n"
                    value = ""
                else:
                    new_value += value[1:value.index(" ")]
                    value = value[value.index(" ")+1:]
            elif value[0] == "t":
                if len(value) == 1: value = ""
                else: value = value[2:]
                new_value += "true" + "\n"
            elif value[0] == "f":
                if len(value) == 1: value = ""
                else: value = value[2:]
                new_value += "false" + "\n"
            elif value[0] == "v":
                if len(value) == 1: value = ""
                else: value = value[2:]
                new_value += "null" + "\n"
            else:
                print("Ошибка в определении типа элемента " + value)
                exit()
        return new_value
    except Exception as e:
        print("Ошибка при определении типа элемента: ", e)


def unparse_object(object: str) -> tuple[str, str]:
    def error():
        print("Ошибка в определении объекта " + object)
        exit()
    try:
        result = dict()
        index, object = object[1:object.index(" ")], object[object.index(" ")+1:]
        for _ in range(int(index)):
            key = str()
            if object[0] == "s":
                for i in range(2, len(object)-1):
                    if object[i] == '"' and object[i-1] != "\\":
                        key = object[2:i]
                        object = object[i+2:]
                        break
            else: error()
            if object.count(" ") == 0:
                value = unparse_value(object)
                object = ""
            elif object[0] in "ntfv":
                value = object[1:object.index(" ")]
                object = object[object.index(" ")+1:]
                value = unparse_value(value)
            elif object[0] == "s":
                for i in range(2, len(object)-1):
                    if object[i] == '"' and object[i-1] != "\\":
                        value = object[2:i]
                        if len(object[i:]) > 2:
                            object = object[i+2:]
                        else:
                            object = ""
                        break
            elif object[0] == "a":
                value, object = unparse_array(object)
                value = "\n" + value
            elif object[0] == "o":
                value, object = unparse_object(object)
                value = "\n" + value
                flag = False
                new_value = str()
                for i in range(len(value)-1):
                    if flag:
                        if value[i] == '"' and value[i - 1] != "\\":
                            flag = False
                        continue
                    elif value[i] == '"' and value[i - 1] != "\\":
                        flag = True
                        continue
                    new_value += value[i]
                    if value[i] == "\n":
                        new_value += "  "
                value = new_value
            else: error()
            result.setdefault(key, value)
        new_result = str()
        for i in [[list(result.keys())[i], list(result.values())[i]] for i in range(len(result.keys()))]:
            key = i[0]
            value = i[1]
            new_result += key + ": " + value + "\n"
        result = new_result
        return result, object
    except Exception as e:
        print("Ошибка при определении объекта: ", e)


def unparse_array(array: str) -> tuple[str, str]:
    try:
        result = list()
        index, array = array[1:array.index(" ")], array[array.index(" ")+1:]
        for _ in range(int(index)):
            if array.count(" ") == 0:
                result.append(unparse_value(array))
                array = ""
            elif array[0] in "ntfv":
                value = array[:array.index(" ")]
                array = array[array.index(" ")+1:]
                result.append(unparse_value(value))
            elif array[0] == "s":
                for i in range(2, len(array)-1):
                    if array[i] == '"' and array[i-1] != "\\":
                        result.append(array[2:i])
                        if len(array[i:]) > 2:
                            array = array[i+2:]
                        else:
                            array = ""
                        break
            elif array[0] == "a":
                value, array = unparse_array(array)
                flag = False
                new_value = str()
                for i in range(len(value)):
                    if flag:
                        if value[i] == '"' and value[i - 1] != "\\":
                            flag = False
                        continue
                    elif value[i] == '"' and value[i - 1] != "\\":
                        flag = True
                        continue
                    new_value += value[i]
                    if value[i] == "\n":
                        new_value += "  "
                value = new_value + "\n"
                result.append(value)
            elif array[0] == "o":
                value, array = unparse_object(array)
                flag = False
                new_value = str()
                for i in range(len(value)-1):
                    if flag:
                        if value[i] == '"' and value[i-1] != "\\":
                            flag = False
                        continue
                    elif value[i] == '"' and value[i-1] != "\\":
                        flag = True
                        continue
                    new_value += value[i]
                    if value[i] == "\n":
                        new_value += "  "
                value = new_value + "\n"
                result.append(value)
            else:
                print("Ошибка в определении массива " + str(result))
                exit()
        new_result = str()
        for i in result:
            new_result += "- " + i
        result = new_result
        return result[:-1], array
    except Exception as e:
        print("Ошибка при определении массива: ", e)


if __name__ == "__main__":
    bin_to_yaml("schedule.bin")