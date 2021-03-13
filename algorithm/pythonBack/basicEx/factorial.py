# 팩토리얼 사용법

#내장함수이용
import math

print(math.factorial(5))

print(math.factorial(20))

# 단순반복문
# n부터 1까지 곱해서 반복문을 통해 늘려나간다
def factorial_for(n):
    ret = 1
    for i in range(1, n+1):
        ret *= i
    return ret
print(factorial_for(5))
print(factorial_for(20))

# 재귀함수

def factorial_recursive(n):
    return n* factorial_recursive(n-1) if n > 1 else 1
    #삼항연산자사용

print(factorial_recursive(5))
print(factorial_recursive(20))