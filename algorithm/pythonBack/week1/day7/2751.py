# 수 정렬하기 2
#오름차순

n = int(input())
num = []

for i in range(n):
    x = int(input())
    num.append(x)

for i in sorted(num):
    print(i)

# for i in range(n):
#     num.append(list(map(int, sys.stdin.readline().split())))
#     print(num)

# print(int(num.sort())