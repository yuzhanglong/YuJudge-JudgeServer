# File: contest.py
# Description: 模拟一次比赛
# Created: 2020-9-8 14:55:40
# Author: yuzhanglong
# Email: yuzl1123@163.com

import random

import requests

from base.base import BASE_URL
from problems import bcj, bestCowFences, childString, division, forkBomb, maxSum, money, plus, prime, publicString, \
    square
from user.common import login
from user.userGenerate import generateUsers

SUBMISSION_BASE_URL = BASE_URL + "/submission/submit_code"

AC_KEY = "AC"
WA_KEY = "WA"

data = [bcj, bestCowFences, childString, division, forkBomb, maxSum, money, plus, prime, publicString, square]


def loginUsers(users):
    totalToken = []
    for t in users:
        token = login(t, "password")
        totalToken.append(token)
    return totalToken


def runContest(problemSetId, testTimes):
    nickNames = generateUsers(userAmount=30)
    tokens = loginUsers(nickNames)
    keys = [AC_KEY, WA_KEY]
    for res in range(0, testTimes):
        isAc = random.randint(0, 1)
        # 随机取问题
        problem = data[random.randint(0, 10)]
        json = problem.requestBody[keys[isAc]]
        json['problemSetId'] = problemSetId
        headers = {'Authorization': tokens[random.randint(0, 39)]}
        res = requests.post(url=SUBMISSION_BASE_URL, json=json, headers=headers)
        print(res.json())


if __name__ == '__main__':
    runContest(102, 980)