alphabet = ["c=","c-","dz=","d-","lj","nj","s=","z="]

a = input()

for i in alphabet :
    a = a.replace(i,' ')
    #찾은 문자를 빈칸으로 바꿔줌 (찾은문자를 또 찾는 경우를 방지하기위해)

print(len(a))
#a의 길이 출력