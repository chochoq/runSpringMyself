t = int(input())
for i in range(t):
    h, w, n = map(int, input().split())
    floor = 0
    num = 0
    if n % h == 0 : 
        # 나머지가 0일때는 꼭대기층
        floor = h *100
        # 방 호수가 100자리임으로 *100을 해줌
        num = n // h
    else :
        floor = (n%h)*100
        num = 1+ n//h
    print(floor + num)

    # //는 나눗셈후 소수점이하를 버려준는 연산자이다
    # ** 거듭제곱을 구하는 연산자이다
    