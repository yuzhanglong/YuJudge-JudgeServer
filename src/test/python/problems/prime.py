# File: prime.py
# Description: 质数判定
# Created: 2020-9-8 14:35:22
# Author: yuzhanglong
# Email: yuzl1123@163.com

requestBody = {
    "AC": {
        "problemId": 10004,
        "codeContent": "#include <bits/stdc++.h>\r\nusing namespace std;\r\ntypedef long long ll;\r\nll p, d, pri[8] = { 3, 5, 7, 11, 13, 17, 19, 23 };\r\ninline ll mul(ll a, ll b) {\r\n    ll r = a * b - ((ll)((long double)a / p * b + 0.5)) * p;\r\n    return r < 0 ? r + p : r;\r\n}\r\ninline ll power(ll a, ll b) {\r\n    ll r = 1;\r\n    for (; b; b >>= 1, a = mul(a, a))\r\n        if (b & 1)\r\n            r = mul(r, a);\r\n    return r;\r\n}\r\ninline bool Miller_Rabin() {\r\n    if (p == 1)\r\n        return 0;\r\n    if (p == 2)\r\n        return 1;\r\n    if (!(p & 1))\r\n        return 0;\r\n    d = p - 1;\r\n    int s = 0;\r\n    while (!(d & 1)) d >>= 1, ++s;\r\n    for (register int i = 0; i < 8; ++i) {\r\n        if (p == pri[i])\r\n            return 1;\r\n        ll x = power(pri[i], d), y;\r\n        for (register int j = 0; j < s; ++j) {\r\n            y = mul(x, x);\r\n            if (y == 1 && !(x == 1 || x == p - 1))\r\n                return 0;\r\n            x = y;\r\n        }\r\n        if (y != 1)\r\n            return 0;\r\n    }\r\n    return 1;\r\n}\r\nint main() {\r\n    while (scanf(\"%lld\", &p) != EOF) Miller_Rabin() ? puts(\"Y\") : puts(\"N\");\r\n    return 0;\r\n}",
        "language": "C_PLUS_PLUS",
        "judgePreference": "ACM"
    },
    "WA": {
        "problemId": 10004,
        "codeContent": "#include <iostream>\r\nusing namespace std;\r\nint main() {\r\n    int a, b;\r\n    cin >> a >> b;\r\n    cout << a - b;\r\n}",
        "language": "C_PLUS_PLUS",
        "judgePreference": "ACM"
    }
}
