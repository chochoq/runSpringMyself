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



num = int(input())

i = num

cnt = int(0)


while True :
     ten = int(i/10)   #2
     one = i%10   # 6
     total = ten+one   # 08
     
     i = int(str(one) + str(total%10)) 
     cnt += 1
     if(num == i) : 
          break

print(cnt)


num = temp = int(input())
count = 0
while True:
    ten = num // 10
    one = num % 10
    total = ten + one
    count += 1
    num = int(str(num % 10) + str(total % 10))
    if(temp == num):
        break
print(count)
