# # 1-10 의 합

# x = 0
# for i in range(11):
#     x += i
# print(x)


# def f(n):
#     print(n)
#     if n <= 1:
#         return 1
#     else:
#         return n + f(n-1)

# print(f(10))


# 반복문
# x = 0
# for i in '323':
#     x += int(i)
# print(x)


# def f(n):
#     if n < 10:
#         return n
#     return (n%10)+f(n//10)

# print(f(323))

# def f(n):
#     if n <= 0 :
#         print('끝')
#     else:
#         print(n)
#         f(n-1)
# f(10)

# a = 0
# b = 1
# print(a)
# for i in range(10):
#     print(b)
#     a, b = b, a+b

def f(n):
    if n == 0 or n ==1 :
        return 1
    return f(n-1) + f(n-2)
print(f(5))