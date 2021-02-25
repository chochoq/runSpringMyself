# <!—Bs4 기본 세팅—>

import requests
from bs4 import BeautifulSoup

from pymongo import MongoClient           # pymongo를 임포트 하기(패키지 인스톨 먼저 해야겠죠?)
client = MongoClient('localhost', 27017)  # mongoDB는 27017 포트로 돌아갑니다.
db = client.dbsparta                      # 'dbsparta'라는 이름의 db를 만듭니다.


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

    doc = {
        'weatherArea':weatherArea,
        'weatherIcon':weatherIcon,
        'weatherDo':weatherDo,
        'weatherDoC':weatherDoC
    }

    db.daumWeathers.insert_one(doc)

div = soup.select_one('#daumHead > div.d_service > div > div.today_base > div > div.info_today > strong').text
dateDoc={
    'div':div
}

db.daumDate.insert_one(dateDoc)