import sys
n = int(sys.stdin.readline())
so = []
for i in range(n):
    so.append(list(map(int, sys.stdin.readline().split())))
so.sort(key=lambda x: (x[1], x[0]))
# sort 정렬함수, key값을 기준으로 정렬이되며 기본은 오름차순
for i in so:
    print(i[0], i[1])


