#하노이의 탑

n = int(input())

def hanoi(n, start, trans, end):
    if n == 1:
        # 원반이 하나일 경우 바로 목표로 넣어줄수있음
        print(start, end)
    else:
        hanoi(n - 1, start, end, trans)
        # 2개 이상일땐 마지막 원반을 제외한 모든 원반들이
        # 중간 지점으로 옮겨져야 n==1이 될수있다
        print(start, end)
        hanoi(n - 1, trans, start, end)
sum = 1

for i in range(n - 1):
    sum = sum * 2 + 1
    # 여기 이해못해욤
print(sum)

hanoi(n, 1, 2, 3)
