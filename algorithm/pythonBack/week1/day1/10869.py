# 두 자연수 A와 B가 주어진다. 
# 이때, A+B, A-B, A*B, A/B(몫), A%B(나머지)를 출력하는 프로그램을 작성하시오. 


# 숫자입력
A, B = input().split()

# 숫자출력
# print(A)
# print(B)

# str -> int 변환
A=int(A)
B=int(B)

# 연산하기 
print(A + B)
print(int(A -B))
print(A*B)
print(int(A/B))
print(A%B)

