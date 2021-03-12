# 최솟값 정렬
# array = [4,6,3,2,1,5,9]

# for i in range(len(array)):
#     min_index = i 
#     # 가장작은 원소의 인덱스
#     for j in range(i+1, len(array)):
#         if array[min_index] > array[j]:
#             min_index = j
#     array[i],array[min_index] = array[min_index], array[i]

# print(array)

num = int(input())
a = []
#리스트를 만듦
for i in range(num) :
    [x,y] = map(int,input().split())
    #입력받은 숫자의 횟수만큼, x,y좌표를 리스트의 형태로 입력받는다
    rev = [y,x]
    a.append(rev)
    # y,x 순서의 좌표가 필요함으로 뒤집어서 a리스트에 추가함
b = sorted(a)
# a리스트를 sorted로 정렬
for i in range(num):
    print(b[i][1],b[i][0])
    