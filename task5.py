from time import time
from task1 import json_to_bin
from task2 import bin_to_yaml
from task3 import json_to_yaml
from task4 import bin_to_xml


try:
    timer = time()
    for i in range(100):
        json_to_bin("schedule.json")
        bin_to_yaml("schedule.bin")
    print("json -> yaml")
    print(time() - timer)

    timer = time()
    for i in range(100):
        json_to_yaml("schedule.json")
    print("json -> yaml (with libraries)")
    print(time() - timer)

    timer = time()
    for i in range(100):
        json_to_bin("schedule.json")
        bin_to_xml("schedule.bin")
    print("json -> xml")
    print(time() - timer)
except Exception as error:
    print(f"Ошибка: {error}")