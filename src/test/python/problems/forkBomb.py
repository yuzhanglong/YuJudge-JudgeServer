# File: forkBomb.py
# Description: fork炸弹测试
# Created: 2020-9-8 14:26:39
# Author: yuzhanglong
# Email: yuzl1123@163.com

requestBody = {
    "AC": {
        "problemId": 10001,
        "codeContent": "#include <iostream>\r\nusing namespace std;\r\nint main() {\r\n    int a, b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}",
        "language": "C_PLUS_PLUS",
        "judgePreference": "ACM"
    },
    "WA": {
        "problemId": 10001,
        "codeContent": "#include <stdio.h>\r\n#include <unistd.h>\r\nint main()\r\n{\r\n    if (!fork())\r\n    {\r\n        while (1)\r\n        {\r\n            fork();\r\n        }\r\n    }\r\n    return 0;\r\n}",
        "language": "C",
        "judgePreference": "ACM"
    }
}
