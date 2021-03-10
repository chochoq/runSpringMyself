# 첫째 줄에 두 정수 H와 M이 주어진다. (0 ≤ H ≤ 23, 0 ≤ M ≤ 59) 그리고 이것은 현재 상근이가 설정한 놓은 알람 시간 H시 M분을 의미한다.
# 입력 시간은 24시간 표현을 사용한다. 24시간 표현에서 하루의 시작은 0:0(자정)이고, 끝은 23:59(다음날 자정 1분 전)이다. 시간을 나타낼 때, 불필요한 0은 사용하지 않는다.


i = input().split(" ")

H = int(i[0])
M = int(i[1])

time = M - 45
if time >= 0 :
    M = time
else : 
    H = H - 1
    if H < 0 :
        H = 24 + H
    M = 60 + time
    
print(H,M)


# # 변수를 입력받는 방법
# H,M = input().split()
# #문자를 숫자로
# H = int(H)
# M = int(M)
# # 풀이
# if M < 45 and M >= 0:
#     if H < 24 and H > 0:
#         H = H - int(1)
#         M = int(60) + (M - int(45))
#     elif H == int(0):
#         H = 23
#         M = int(60) + (M - int(45))        
# elif M >= 45 and M < 60:
#     M = M - int(45)
# print(H,M)
