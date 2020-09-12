# File: common.py
# Description: 用户操作集
# Created: 2020-9-8 15:31:35
# Author: yuzhanglong
# Email: yuzl1123@163.com

# 需要关闭验证码模式
import requests

from base.base import BASE_URL

LOGIN_BASE_URL = BASE_URL + "/user/login"


# 返回token
def login(name, password):
    data = {
        "nickname": name,
        "password": password,
        "checkCodeContent": "kweyx",
        "checkCodeKey": "b9208d5c-9081-4ee9-a3bc-2ffecc4fcb43"
    }
    res = requests.post(url=LOGIN_BASE_URL, json=data)
    json = res.json()
    return json['data']['accessToken']
