# File: contest.py
# Description: 模拟一次比赛
# Created: 2020-9-8 14:55:40
# Author: yuzhanglong
# Email: yuzl1123@163.com
import random

import requests

from base.base import HEADERS
from problems import bcj, bestCowFences, childString, division, forkBomb, maxSum, money, plus, prime, publicString, \
    square
from user.common import login
from user.userGenetare import geneateUsers

SUBMISSION_BASE_URL = "http://localhost:8080/submission/submit_code"

AC_KEY = "AC"
WA_KEY = "WA"

data = [bcj, bestCowFences, childString, division, forkBomb, maxSum, money, plus, prime, publicString, square]


def loginUsers(users):
    totalToken = []
    for t in users:
        token = login(t, "password")
        totalToken.append(token)
    return totalToken


def run(problemSetId, testTimes):
    nickNames = geneateUsers(userAmount=40)
    tokens = loginUsers(nickNames)
    keys = [AC_KEY, WA_KEY]
    for res in range(0, testTimes):
        isAc = random.randint(0, 1)
        # 随机取问题
        problem = data[random.randint(0, 10)]
        json = problem.requestBody[keys[isAc]]
        json['problemSetId'] = problemSetId
        HEADERS['Authorization'] = tokens[random.randint(0, 39)]
        res = requests.post(url=SUBMISSION_BASE_URL, json=json, headers=HEADERS)
        print(res.json())


if __name__ == '__main__':
    run(102, 980)
