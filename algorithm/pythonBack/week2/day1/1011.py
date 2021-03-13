import math

N = int(input())

count = 0                                    #최소 작동 횟수
result = []

for _ in range(N):
    a, b = map(int, input().split())
    distance = b - a                         #주어진 값들간의 거리

    num = math.floor(math.sqrt(distance))  #주어진 값들 사이의 거리에 루트 씌움 (제곱근) , floor처리되어 이미 정수임    
    num_jegob = num**2                # 정수를 제곱근으로 갖는 제곱수(ex. 9 : 9의 제곱근은 3)

    if distance == num_jegob:
        count = (num*2)-1

    elif num_jegob < distance <= num_jegob + num:
        count = (num*2)

    elif (num_jegob + num) < distance:
        count = (num*2) + 1

    elif distance < 4:
        count = distance
    result.append(count)



for x in result:
    print(x)
