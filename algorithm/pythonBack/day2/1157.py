# def find_all_alphabet(string):
#     all_alphabet = [0] * 26

#     for char in string :
#         if not char.isalpha(): 
#             continue # 배열안에 알파벳이 아닌 다른것이 들어갈경우 
#         arr_index = ord(char) - ord("a")
#         all_alphabet[arr_index] +=1
#          
#     return all_alphabet
    


# print(find_all_alphabet("hello my name is sparta"))


# input = "hello my name is sparta"

# def find_max_occurred_alphabet(string):
#     alphabet_array = ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
#                       "t", "u", "v", "x", "y", "z"]

#     max_occurrence = 0
#     max_alphabet = alphabet_array[0]

#     for alphabet in alphabet_array :
#         occurrence = 0
#         for char in string :
#             if char == alphabet:
#                 occurrence += 1

#         if occurrence > max_occurrence :
#             max_occurrence = occurrence
#             max_alphabet = alphabet

#     return max_alphabet

# result = find_max_occurred_alphabet(input)
# print(result)



# input = "hello my name is sparta"

# def find_max_occurred_alphabet(string):
#     alphabet_occurrence_array = [0] * 26

#     for char in string:
#         if not char.isalpha():
#             continue
#         arr_index = ord(char) - ord('a')
#         alphabet_occurrence_array[arr_index] += 1

#     max_occurrence = 0
#     max_alphabet_index = 0
#     for index in range(len(alphabet_occurrence_array)):
#         alphabet_occurrence = alphabet_occurrence_array[index]
#         if alphabet_occurrence > max_occurrence:
#             max_occurrence = alphabet_occurrence
#             max_alphabet_index = index
# result = find_max_occurred_alphabet(input)
# print(result)


alphabet = input().upper() 
#먼저 인풋 받은 문자를 모두 대문자로 바꿔줌
print(alphabet)

one_alphabet = list(set(alphabet)) 
# set함수의 중복된 값을 자동으로 제거하는 특성을 이용해 중복된 알파벳 제거 후 리스트에 담아줌
# set 내장함수
# set 안에 input을 넣을 경우 list 안에 넣어줘야함 
# set해서 알파벳을 하나씩만 추출함

print(one_alphabet)

cnt_list = []

for i in one_alphabet :
    cnt = alphabet.count(i)
    # 같은 알파벳이 몇개 있는지 count
    cnt_list.append(cnt) 
    # cnt를 리스트에 append

if cnt_list.count(max(cnt_list)) > 1 : 
    # cnt 최대값이 중복될 시 ? 출력
    print('?')
else :
    max = cnt_list.index(max(cnt_list))
    # 최대값이 1개라면 index함수로 위치를 찾는다
    print(one_alphabet[max])


# 대호님코드
# word = input().upper()
# word_lst = list(set(word))
# cnt = 0
# char_lst = []
# for i in word_lst:
#   cnt_1 = word.count(i)
#   if cnt_1 >= cnt:
#     if cnt_1 == cnt:
#       char_lst.append(i)
#     else:
#       char_lst = []
#       char_lst.append(i)
#     cnt = cnt_1

# if len(char_lst) > 1:
#   print('?')
# else:
#   print(char_lst[0])