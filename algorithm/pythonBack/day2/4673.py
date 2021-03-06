def d(n) :
    r = n 
    s = str(n) 
    #숫자였던 값을 배열로 돌리기전에 문자열로 쪼개기위함
    for c in s :
        r += int(c)
         # r = r + int(c)
    return r

n=10000
arr=[0]*n

for i in range(0,len(arr)):
    dResulet = d(i+1)
    if dResulet - 1 < len(arr) :
        arr[dResulet-1] += 1

for i in range(0,len(arr)) :
    if arr[i] == 0 :
        print(i+1)
