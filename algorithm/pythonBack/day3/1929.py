# M이상 N이하의 소수를 모두 출력하는 프로그램을 작성하시오.
# 소수 1과 자신만을 약수로 가지는 수(1보다큼)
# 약수 나머지를 0으로 만드는 수


# math.sqrt 제곱근
# 에라토스테네스의 체 란 무엇?
# 1과 배수를 지우다보면 지워지지않은 것이 소수다!




# m, n = list(map(int, input().split(""))

# s = [0, 0] + [1] * (n-1)
# for i in range(2, int(n **.5)+1):
#     if s[i]:
#         s[i* 2::i] = [0]* ((n-i)//i)
# for i, v in enumerate(s):
#     if v and i >= m:
#         print(i)

import math

m, n = list(map(int, input().split()))
s = [0, 0] + [1] * (n - 1)
for i in range(2, int(n ** .5) + 1):
    # 에라토스테네스의 체 
    if s[i]:
        s[i * 2::i] = [0] * ((n - i) // i)
for i, v in enumerate(s):
    if v and i >= m:
        print(i)

# - 먼저 m, n을 입력으로 받고, 
# 0부터 n까지 숫자에 대한 리스트를 만드는데, 

# 숫자를 그대로 쓰는 것이 아니라 True, False를 이용한 리스트를 만들어줌. 
# 0, 1은 소수가 아니므로 0 (False), 아직 지워지지 않은 2부터는 1 (True)을 값으로 지정함. 
# 예를 들어 n = 3이라면, [0, 0, 1, 1]이라는 리스트를 만들어줌.

# - 2부터 n의 제곱근까지 반복문을 돌림. 
# 여기서 리스트의 i번째 요소가 아직 지워지지 않았다면, 
# i를 제외한 i의 배수들을 모두 지워줌. 
# 즉, 값이 1이었던 것을 값을 0으로 만들어줌.

# - 이렇게 해서 소수번째만 1을 가지고 있는 리스트가 완성됨. 
# m 이상의 수에 대해서, 값이 1인 위치만 출력해주면 됨.


def isPrime(i) :
    if i != 1:
        # 1은 소수가 아님
        j = int(math.sqrt(i)) 
        # 제곱근인것은 약수! 
        # i의 제곱근, 제곱근으로 할시 소수점이기때문에 int로 소수점을 제외시킴
        for x in range(2, j+1):
            # 소수의 정의가 1을 제외하기 때문에 2부터 시작해서 뒤의 j+1은 미만이기때문에 
            if i % x == 0: # -> 나머지가 0이면 약수 
            # 2와 J+1사이의 값들  
            # 나머지가 0일경우 약수기 때문에 false 
                return False
        return True

M, N = map(int, input().split())
# map 요소를 받음

for k  in range(M, N+1):
    if isPrime(k) : 
    #return True 생략
        print(k)