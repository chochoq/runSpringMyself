#(1 ≤ N ≤ 1,000,000, 1 ≤ M ≤ 2,000,000,000) 과같이  큰 탐색범위를 보면 이진탐색으로해결해야한다

# 
# n, m =list(map(int, input().split(' ')))
# array = list(map(int,input().split()))

# start = 0
# end = max(array)

# result = 0
# while(start<=end):
#     total = 0 
#     mid =(start+end)//2
#     for i in array :
#         if i > mid:
#             total += i - mid
#     if total < m:
#         end = min -1
#     else : 
#         result = mid
#         start =mid+1

# print(result)

n,m = map(int,input().split())
tree = list(map(int,input().split()))
start, end = 1,max(tree) 
# 범위설정 가장 짧은 길이와 가장 긴 길이

while start <= end:
    #  적절한 벌목높이를 찾는 알고리즘
    mid =(start+end)//2 
    # 중간 범위를 찾는다

    log = 0 
    # 벌목된 나무 총합
    for i in tree :
        if i >= mid :
            # 
            log += i -mid

    # 벌목 높이를 이분탐색
    if log >= m:
        # 중간범위보다 나무총합이 클경우 mid를 +1
        # satrt를 mid+1로 준후 while을 다시돌림
        start = mid + 1
    else :
        end = mid -1
        # 중간범위보다 나무총합이 클경우 mid를 -1
        # satrt를 mid-1로 준후 while을 다시돌림
print(end)