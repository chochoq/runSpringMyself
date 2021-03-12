# 재귀함수
# 1부터 N까지의 합과 곱

# x = 0
# for i in range(1, 101):
#     x += i
# print(x)

# 곱하기
# x = 1
# for i in range(1, 6):
#     x *= i
# print(x)


# 시그마 공식 n*(n+1)//2
# n = 100
# x = 1
# x = n*(n+1)//2
# print(x)

# 재귀함수

# def f(n):
#     if n <= 1:
#         return 1
#     else :
#         return n+ f(n-1)

# n = 100

# print(f(n))

# def f(n):
#     if n <= 1:
#         return 1
#     else :
#         return n * f(n-1)

# n = 5

# print(f(n))

# x = int(input('2진수로 바꿀 숫자를 입력하세요 : '))
# result = ''
# while True: 
#     if x % 2 == 0:
#         result += '0'
#     else:
#         result += '1'

#     x = x // 2
#     if x == 1 or x == 0 :
#         result += str(x)
#         print(result[::-1])
#         break

def leejinsu(n):
    if n < 2 :
        return str(n)
    else:
        return str(leejinsu(n//2))+str(n%2)

print(leejinsu(11))
