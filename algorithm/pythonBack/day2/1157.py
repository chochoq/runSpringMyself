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
#인풋 받은 문자를 모두 대문자로 바꿔줌
many_alphabet = list(set(alphabet)) 

cnt_list = []

for i in many_alphabet :
    cnt = alphabet.count(i)
    cnt_list.append(cnt) # cnt를 리스트에 추가

if cnt_list.count(max(cnt_list)) > 1 : # cnt 최대값이 중복될 시
    print('?')
else :
    max = cnt_list.index(max(cnt_list))
    print(many_alphabet[max])