# File: userGenetare.py
# Description: 生成用户
# Created: 2020-9-8 15:14:29
# Author: yuzhanglong
# Email: yuzl1123@163.com


import requests

from base.base import HEADERS

USER_CREATE_BASE_URL = "http://localhost:8080/user/user"


# 生成一定数目的用户，返回用户的昵称列表，密码默认为password
def geneateUsers(userAmount, password="password"):
    nickNames = []
    for i in range(0, userAmount):
        data = {
            "nickname": "testUser" + str(i),
            "password": password
        }
        res = requests.post(url=USER_CREATE_BASE_URL, json=data, headers=HEADERS)
        nickNames.append(data['nickname'])
    return nickNames
