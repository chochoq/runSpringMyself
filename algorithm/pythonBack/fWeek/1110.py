# 첫째 줄에 N이 주어진다. 
# N은 0보다 크거나 같고, 99보다 작거나 같은 정수이다.


# while을 트루로
#  %의 나머지값 
# N = input()
# cnt = 0

# print(N[0])
# print(N[1])

# A= int(N[0])+int(N[1])
# print(A)

# B= A + int(N[1])
# print(B)

# C= B + int()




# i = input()
# i = 26
# A = int(i)/10
# B = int(i)%10
# print(int(A), B)

# C = int(A)+B

# D = B + C

# A = D

# A = int(i)/10
# B = int(i)%10
# print(int(A), B)

# C = int(A)+B

# D = B + C

# print(D)



# num = int(input())

# i = num

# cnt = int(0)


# while True :
#      ten = int(i/10)   #2
#      one = i%10   # 6
#      total = ten+one   # 08
     
#      i = int(str(one) + str(total%10)) 
#      cnt += 1
#      if(num == i) : 
#           break

# print(cnt)

# #  대조군tmep 와 변화될 값 num
# num = int(input()) 
# cnt = 0

# # while True :
# #     ten = num // 10 # int(num/10)과 같음
# #     one = num % 10

# ten = num // 10 # 34입력시 3
# a=int(num/10)   #3

# b=int(num)/10   3.4
# c=num/10    3.4


num = temp = 26
count = 0
while True: #조건이 명확하지않을 때 while을 자주사용!!
    ten = num // 10 # int(num/10)과 같음 나눈 몫 
    one = num % 10 # 나머지
    total = ten + one
    count += 1 
    num = int(str(num % 10) + str(total % 10))
    if(temp == num): # 비교군temp와 계속 변화했던 수num의 비교한후 브레이크
        break
print(count)

# http://pythontutor.com/live.html#mode=edit