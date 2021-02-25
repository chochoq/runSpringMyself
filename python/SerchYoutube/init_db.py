# <!—Bs4 기본 세팅—>

import requests
from bs4 import BeautifulSoup


headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64)AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36'}
data = requests.get('https://www.daum.net/', headers=headers)

soup = BeautifulSoup(data.text, 'html.parser')

lis = soup.select('#minitoday > ul > li')

for li in lis:
    weatherArea = li.select_one('span.txt_part').text
    weatherIcon = li.select_one('strong').text
    weatherDo = li.select_one('em').text
    weatherDoC = li.select_one('span.img_weather.ico_degree > span').text
    print(weatherArea,weatherIcon,weatherDo,weatherDoC)

div = soup.select_one('#daumHead > div.d_service > div > div.today_base > div > div.info_today > strong').text
print(div)


# 날씨
# 지역
#minitoday > ul > li:nth-child(1) > span.txt_part
# 현재날씨아이콘으로(가져올수있나..)
#minitoday > ul > li:nth-child(1) > strong
# 몇도
#minitoday > ul > li:nth-child(1) > em
# 뒤에 도
#minitoday > ul > li:nth-child(19) > span.img_weather.ico_degree > span

# 날짜
#daumHead > div.d_service > div > div.today_base.today_on > div > div.info_today > strong