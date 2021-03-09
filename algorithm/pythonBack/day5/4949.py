# 스택은
# 입출구가 합쳐져 구멍이 오직 하나밖에 없음,
# 따라서 가장 마지막에 들어온 데이터가 가장 먼저 처리된다.(last in first out LIFO)
# 입력을 push -> append(), 출력은 pop -> pop()
#   버블버블 팝ㄹ팝
# 비유를 보통 택배차

# a = [1,2,3,4,5]
# a.append(10)
# a.append(20)
# print(a.pop()) 
# 여기서 한번 pop을 해주면 20이 이미 나와서 아래 for에서는 10부터 pop이된다
# print('스택이란')

# for _ in range(len(a)):
#     print(a.pop())


# word = input("단어를 입력하시길")
# word_list=list(word)
# result = []
# for _ in range(len(word_list)):
#     result.append(word_list.pop())
#     # print(word_list.pop())
# # print("".join(result))
# print(word[::-1])

while(1):
 
    vps=input()
    if vps == '.':
        break
    # . 일때 브레낏
 
    stack_check_list=[]
    check = 1 

    for i in vps:
        if i=='(':
             stack_check_list.append(0)

        elif i=='[':
            stack_check_list.append(1)

        elif i==')':
            if len(stack_check_list)==0:
                check = 0
                break
            if stack_check_list.pop()!=0: 
                #엇갈렸을때
                check = 0
                break

        elif i==']':
            if len(stack_check_list)==0:
                check = 0
                break
            if stack_check_list.pop()!=1: 
                #엇갈렸을때
                check = 0
                break

#check가 0이면 no 1일때 yes
    if len(stack_check_list)==0 and check:
        print("yes")

    else:
        print("no")
