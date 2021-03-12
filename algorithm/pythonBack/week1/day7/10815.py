# 숫자카드

import sys
input = sys.stdin.readline
n = int(input())
n_arr = set(map(int,input().split()))
m = int(input())
m_arr = list(map(int,input().split()))

for i in range(m) :
    if m_arr[i] in n_arr :
        print(1, end='')
    else:
        print(0, end='')

###

# import sys
# input = sys.stdin.readline
# n = int(input())
# arr = set(map(int,input().split()))
# m = int(input())
# m_arr= list(map(int,input().split()))



# for i in range(m):
#     if m_arr[i] in arr : 
#         print(1,end=' ')
#     else : print(0,end=' ')