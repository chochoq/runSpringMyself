import math
import sys

# 2 - 1 = 1 + 2 -1 = 2 + 2 -1 = 3 + 2 -정상 못내려옴
# 나무높이를 구하는 식
# (a-b)*n+a=V
# (2-1)*n+2=5
# 5 - 1 / 2- 1



# a, b, v  = input().split(" ")
# day = (v-b)/(a-b)
# print(int(day))


a, b, v =map(int, sys.stdin.readline().split())
day = (v-a)/(a-b)+1
# 마지막날에는 잠을 자지않는다는걸 생각한 수식
print(math.ceil(day))