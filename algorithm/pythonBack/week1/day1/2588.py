# (세 자리 수) × (세 자리 수)는 다음과 같은 과정을 통하여 이루어진다.
#(1)과 (2)위치에 들어갈 세 자리 자연수가 주어질 때 (3), (4), (5), (6)위치에 들어갈 값을 구하는 프로그램을 작성하시오.




# 숫자입력
A = int(input())
B = input()



# 세자리 수중 첫번째, 두번째 세번째 값을 찾음
C = int(B[0])
D = int(B[1])
E = int(B[2])

# 연산 출력
print(E * A)
print(D * A)
print(C * A)
print(A * int(B))


# print(A * int(B%10)) ok
# print(A * int(B/10))
# print(A * int(B/100)) ok
# print(A *B)

# 472
# 385