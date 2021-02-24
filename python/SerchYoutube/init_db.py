# <!—Bs4 기본 세팅—>

import requests
from bs4 import BeautifulSoup


headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64)AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36'}
data = requests.get('https://www.daum.net/', headers=headers)

soup = BeautifulSoup(data.text, 'html.parser')

print(soup)

# 날씨
#daumHead > div.d_service > div > div.today_base > div
#minitoday > ul > li:nth-child(1)
#minitoday > ul > li:nth-child(2)

# 날짜
#daumHead > div.d_service > div > div.today_base > div
#minitoday
#daumHead > div.d_service > div > div.today_base > div > div.info_today > strong