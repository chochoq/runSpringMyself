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

        
---

# stack = []
# answer = []
# while stack != ['.']:
#   stack = list(input())
#   if stack == ['.']:
#     break
#   lst = ['(', ')', '[', ']']
#   n = -1
#   # for 반복문으로 stack에 뒤에부터 lst안에 있는 element 빼고 다른 element는 다 빼낸다.
#   for i in range(len(stack)):
#     if stack[n] not in lst:
#       stack.pop(n)
#     else: #lst안에 있는 element가 stack[n]자리에 있으면 stack[n-1]값을 검사한다.
#       n = n-1
#   string = ""
#   for ele in stack:
#     string += ele
#   # string안에 괄호가 제대로 되어있는지 알기위해서 '()', '[]' 값이랑 비교하고 그 값들을 없앤다.
#   # ')('이나 '][' 제대로 괄호가 나열되어있지 않으면 string에 값이 남아 있을 것이다.
#   while  '()' in string or '[]' in string: 
#     string = string.replace('()', '')
#     string = string.replace('[]', '')
#   if not string: #만약 string변수 안에 아무런 값도 없으면!
#     answer.append('yes')
#   else:
#     answer.append('no')
# for i in answer:
#   print(i)