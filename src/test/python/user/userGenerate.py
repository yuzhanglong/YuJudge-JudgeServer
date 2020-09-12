# File: userGenerate.py
# Description: 生成用户
# Created: 2020-9-8 15:14:29
# Author: yuzhanglong
# Email: yuzl1123@163.com


import requests

from base.base import ADMIN_USER_NAME, ADMIN_PASSWORD, BASE_URL
from user.common import login

USER_CREATE_BASE_URL = BASE_URL + "/user/user"


# 生成一定数目的用户，返回用户的昵称列表，密码默认为password
def generateUsers(userAmount, password="password"):
    adminToken = login(ADMIN_USER_NAME, ADMIN_PASSWORD)
    nickNames = []
    for i in range(0, userAmount):
        data = {
            "nickname": "testUser" + str(i),
            "password": password
        }
        headers = {'Authorization': adminToken}
        res = requests.post(url=USER_CREATE_BASE_URL, json=data, headers=headers)
        nickNames.append(data['nickname'])
    return nickNames
