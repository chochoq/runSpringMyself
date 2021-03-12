# 대학생 새내기들의 90%는 자신이 반에서 평균은 넘는다고 생각한다. 당신은 그들에게 슬픈 진실을 알려줘야 한다.

# C = 5 #인풋받아야함 


#  문제의 갯수 와 점수들을 곱해 평균 구하기


# 평균을 for로 구함

# A = input().split(" ")
# B = input().split(" ")
# C = input().split(" ")
# D = input().split(" ")
# E = input().split(" ")

n = int(input()) 

for i in range(0,n): 
    #range는 0번부터 n번까지 돌린다 n값이 들어오면 0-n까지 for가 돔
    l = list(input().split())
    sum = 0
    studentNum = int(l[0])
    
    for j in range(1,len(l)): 
#len은 length, range를 1부터 돌리는 이유는 성적이 1번부터 담겨있기때문에
        sum +=int(l[j])
        # 각 점수의 합계를 구한다
    avg = sum/studentNum #평균을 구함
    cnt = 0
    for k in range(1,len(l)): 
        if int(l[k]) > avg :
            cnt += 1

    percent = cnt / studentNum * 100 
    # 퍼센트로 만들어주기위해 100을 곱해줌
    print("%.3f" % percent + "%")
    # %.3f % 퍼센트의 소수점을 제한하는 치트시트

# 치트시트 생코 
# https://opentutorials.org/course/1750/9689
    
# 5
# 5 50 50 70 80 100
# 7 100 95 90 80 70 60 50
# 3 70 90 80
# 3 70 90 81
# 9 100 99 98 97 96 95 94 93 91