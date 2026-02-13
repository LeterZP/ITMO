import json
import yaml

def parse_json(file_name):
    try:
        if file_name.split(".")[-1] != "json":
            print("Ошибка: тип файла должен быть json")
            exit()
        with open(file_name, "r", encoding="utf-8") as file:
            json_file = file.read()
        bin_file = json.dumps(json_file).encode("utf-8")
        with open(".".join(file_name.split(".")[:-1])+"(lib).bin", "wb") as file:
            file.write(bin_file)
    except Exception as e:
        print("Ошибка: ", e)


def unparse_bin(file_name):
    try:
        if file_name.split(".")[-1] != "bin":
            print("Ошибка: тип файла должен быть bin")
            exit()
        with open(file_name, "rb") as file:
            bin_file = file.read()
        yaml_file = yaml.safe_load(yaml.safe_load(bin_file))
        yaml_file = yaml.dump(yaml_file, allow_unicode=True)
        with open(".".join(file_name.split(".")[:-1])+".yaml", "w", encoding="utf-8") as file:
            file.write(yaml_file)
    except Exception as e:
        print("Ошибка :", e)


def json_to_yaml(file_name):
    try:
        parse_json(file_name)
        unparse_bin(".".join(file_name.split(".")[:-1])+"(lib).bin")
    except Exception as e:
        print("Ошибка: ", e)


if __name__ == "__main__":
    json_to_yaml("schedule.json")