# File: bestCowFences.py
# Description: Best Cow Fences
# Created: 2020-9-8 14:51:41
# Author: yuzhanglong
# Email: yuzl1123@163.com


requestBody = {
    "AC": {
        "problemId": 10010,
        "codeContent": "#include <cstdio>\r\n#include <iostream>\r\nusing namespace std;\r\n\r\nconst int N = 100005;\r\nint n, F;\r\nconst double eps = 1e-6;\r\ndouble ans, l, r, mid, a[N], sum[N], t[N];\r\n\r\nbool check(double m) {\r\n    for (register int i = n; i >= 1; i--) t[i] = max(a[i] - m, t[i + 1] + a[i] - m);\r\n    for (register int i = 1; i <= n - F + 1; i++) {\r\n        if (sum[i + F - 1] - sum[i - 1] >= F * m)\r\n            return 1;\r\n        if (sum[i + F - 1] - sum[i - 1] - F * m + t[i + F] >= 0)\r\n            return 1;\r\n    }\r\n    return 0;\r\n}\r\n\r\nint main() {\r\n    scanf(\"%d%d\", &n, &F);\r\n    l = 0x3f3f3f3f;\r\n    for (register int i = 1; i <= n; i++) {\r\n        scanf(\"%lf\", &a[i]), sum[i] = sum[i - 1] + a[i];\r\n        l = min(l, (double)a[i]), r = max(r, (double)a[i]);\r\n    }\r\n    for (register int i = 1; i <= 60; i++) {\r\n        mid = (l + r) / 2;\r\n        if (check(mid))\r\n            ans = mid, l = mid + eps;\r\n        else\r\n            r = mid - eps;\r\n    }\r\n    printf(\"%d\", int(ans * 1000));\r\n}",
        "language": "C_PLUS_PLUS",
        "judgePreference": "ACM"
    },
    "WA": {
        "problemId": 10010,
        "codeContent": "#include <iostream>\r\nusing namespace std;\r\nint main() {\r\n    int a, b;\r\n    cin >> a >> b;\r\n    cout << a - b;\r\n}",
        "language": "C_PLUS_PLUS",
        "judgePreference": "ACM"
    }
}
