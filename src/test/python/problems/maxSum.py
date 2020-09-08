# File: maxSum.py
# Description: 最大连续和
# Created: 2020-9-8 14:54:05
# Author: yuzhanglong
# Email: yuzl1123@163.com

requestBody = {
    "AC": {
        "problemId": 10011,
        "codeContent": "#include <bits/stdc++.h>\r\nusing namespace std;\r\n#define Max(x, y) (x > y ? x : y)\r\n#define For(i, x, y) for (i = x; i <= y; i++)\r\nint a[200005];\r\nint read() {\r\n    int A;\r\n    bool K;\r\n    char C;\r\n    C = A = K = 0;\r\n    while (C < '0' || C > '9') K |= C == '-', C = getchar();\r\n    while (C > '/' && C < ':') A = (A << 3) + (A << 1) + (C ^ 48), C = getchar();\r\n    return (K ? -A : A);\r\n}\r\nint main() {\r\n    int n, m, i, l = 1, r, s, mx = -1000;\r\n    n = read(), m = read();\r\n    For(i, 1, n) a[i] = read();\r\n    r = s = 0;\r\n    while (l <= n) {\r\n        while (l <= n && r < n && r - l + 1 < m) {\r\n            s += a[++r];\r\n            mx = Max(mx, s);\r\n            if (s <= 0)\r\n                s = 0, l = r + 1;\r\n        }\r\n        s -= a[l++];\r\n        while (a[l] <= 0 && l <= r) s -= a[l++];\r\n    }\r\n    cout << mx;\r\n    return 0;\r\n}",
        "language": "C_PLUS_PLUS",
        "judgePreference": "ACM"
    },
    "WA": {
        "problemId": 10011,
        "codeContent": "#include <iostream>\r\nusing namespace std;\r\nint main() {\r\n    int a, b;\r\n    cin >> a >> b;\r\n    cout << a - b;\r\n}",
        "language": "C_PLUS_PLUS",
        "judgePreference": "ACM"
    }
}
