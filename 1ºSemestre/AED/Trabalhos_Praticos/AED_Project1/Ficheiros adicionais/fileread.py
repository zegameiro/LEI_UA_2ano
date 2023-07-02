import math

with open("FileOutput") as file:
    file.readline()
    file.readline()
    file.readline()
    file.readline()
    file.readline()
    dic1 = {}
    dic2 = {}
    dic3 = {}
    dic4 = {}
    cont = 1
    for line in file:
        lst = line.split("|")

    #    n       sol           count   cpu time       n       sol           count   cpu time       n       sol           count   cpu time       n       sol           count   cpu time
    #['  1 ', '   1                2 3.847e-06 ', '   1 ', '   1                2 8.370e-07 ', '   1 ', '   1                1 3.009e-06 ', '   1 ', '   1                1 8.150e-07 ', ' \n']

        cpu1 = (lst[1])[-10:]
        cpu2 = (lst[3])[-10:]
        cpu3 = (lst[5])[-10:]
        cpu4 = (lst[7])[-10:]
        dic1[cont] = cpu1
        dic2[cont] = cpu2
        dic3[cont] = cpu3
        dic4[cont] = cpu4
        cont += 1

    print("\nDICT1\n")
    for item, amount in dic1.items():  
        print(amount, end = ' ')

    print("\nDICT2\n")
    for item, amount in dic2.items():  
        print(amount, end = ' ')

    print("\nDICT3\n")
    for item, amount in dic3.items():  
        print(amount, end = ' ')

    print("\nDICT4\n")
    for item, amount in dic4.items():  
        print(amount, end = ' ')


with open("FileOutup2.txt") as file:
    file.readline()
    file.readline()
    file.readline()
    file.readline()
    file.readline()
    dic5 = {}
    cont = 1
    for line in file:
        lst = line.split("|")

    #    n       sol           count   cpu time       n       sol           count   cpu time       n       sol           count   cpu time       n       sol           count   cpu time
    #['  1 ', '   1                2 3.847e-06 ', '   1 ', '   1                2 8.370e-07 ', '   1 ', '   1                1 3.009e-06 ', '   1 ', '   1                1 8.150e-07 ', ' \n']

        cpu5 = (lst[1])[-10:]
        dic5[cont] = cpu5
        cont += 1

    print("\nDICT5\n")
    for item, amount in dic5.items():  
        print(amount, end = ' ')
