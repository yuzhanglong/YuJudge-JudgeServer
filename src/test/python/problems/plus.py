# File: plus.py
# Description: A + B problem的测试脚本
# Created: 2020-9-8 14:20:13
# Author: yuzhanglong
# Email: yuzl1123@163.com

requestBody = {
    "AC": {
        "problemId": 10000,
        "codeContent": "#include <iostream>\r\nusing namespace std;\r\nint main() {\r\n    int a, b;\r\n    cin >> a >> b;\r\n    cout << a + b;\r\n}",
        "language": "C_PLUS_PLUS",
        "judgePreference": "ACM"
    },
    "WA": {
        "problemId": 10000,
        "codeContent": "#include <iostream>\r\nusing namespace std;\r\nint main() {\r\n    int a, b;\r\n    cin >> a >> b;\r\n    cout << a - b;\r\n}",
        "language": "C_PLUS_PLUS",
        "judgePreference": "ACM"
    }
}
