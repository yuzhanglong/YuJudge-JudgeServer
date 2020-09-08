# File: childString.py
# Description: 子串查找
# Created: 2020-9-8 14:41:12
# Author: yuzhanglong
# Email: yuzl1123@163.com

requestBody = {
    "AC": {
        "problemId": 10005,
        "codeContent": "#include <iostream>\r\n#include <cstring>\r\n\r\nusing namespace std;\r\n\r\nconst int N = 1e6 + 10;\r\n\r\nchar s[N], p[N];\r\n\r\nint ne[N], res;\r\n\r\nint main() {\r\n    cin >> s + 1 >> p + 1;\r\n\r\n    int ssize = strlen(s + 1);\r\n    int psize = strlen(p + 1);\r\n\r\n    for (int i = 2, j = 0; i <= psize; i++) {\r\n        while (j && p[i] != p[j + 1]) j = ne[j];\r\n\r\n        if (p[i] == p[j + 1])\r\n            j++;\r\n\r\n        ne[i] = j;\r\n    }\r\n\r\n    for (int i = 1, j = 0; i <= ssize; i++) {\r\n        while (j && p[j + 1] != s[i]) j = ne[j];\r\n\r\n        if (p[j + 1] == s[i])\r\n            j++;\r\n\r\n        if (j == psize) {\r\n            res++;\r\n            j = ne[j];\r\n        }\r\n    }\r\n\r\n    cout << res << endl;\r\n\r\n    return 0;\r\n}",
        "language": "C_PLUS_PLUS",
        "judgePreference": "ACM"
    },
    "WA": {
        "problemId": 10005,
        "codeContent": "#include <iostream>\r\nusing namespace std;\r\nint main() {\r\n    int a, b;\r\n    cin >> a >> b;\r\n    cout << a - b;\r\n}",
        "language": "C_PLUS_PLUS",
        "judgePreference": "ACM"
    }
}
