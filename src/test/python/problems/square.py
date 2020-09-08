# File: square.py
# Description: 矩阵乘法的测试脚本
# Created: 2020-9-8 14:21:04
# Author: yuzhanglong
# Email: yuzl1123@163.com

requestBody = {
    "AC": {
        "problemId": 10002,
        "codeContent": "#include <stdio.h>\r\n#include <bits/stdc++.h>\r\nusing namespace std;\r\nconst int N = 505;\r\ntypedef long long ll;\r\nll a[N][N], b[N][N], c[N][N];\r\nint main() {\r\n    int n, p, m;\r\n    ll mod = 1e9 + 7;\r\n    scanf(\"%d%d%d\", &n, &p, &m);\r\n    for (int i = 1; i <= n; i++) {\r\n        for (int j = 1; j <= p; j++) {\r\n            scanf(\"%lld\", &a[i][j]);\r\n        }\r\n    }\r\n    for (int i = 1; i <= p; i++) {\r\n        for (int j = 1; j <= m; j++) {\r\n            scanf(\"%lld\", &b[i][j]);\r\n        }\r\n    }\r\n    for (int i = 1; i <= n; i++) {\r\n        for (int j = 1; j <= m; j++) {\r\n            for (int k = 1; k <= p; k++) {\r\n                c[i][j] = (c[i][j] + a[i][k] * b[k][j] + mod * mod + mod) % mod;\r\n            }\r\n        }\r\n    }\r\n    for (int i = 1; i <= n; i++) {\r\n        for (int j = 1; j <= m; j++) {\r\n            printf(\"%lld \", c[i][j] % mod);\r\n        }\r\n        puts(\"\");\r\n    }\r\n}",
        "language": "C_PLUS_PLUS",
        "judgePreference": "ACM"
    },
    "WA": {
        "problemId": 10002,
        "codeContent": "#include <stdio.h>\r\n#include <bits/stdc++.h>\r\nusing namespace std;\r\nconst int N = 505;\r\ntypedef long long ll;\r\nll a[N][N], b[N][N], c[N][N];\r\nint main() {\r\n    int n, p, m;\r\n    ll mod = 1e9 + 7;\r\n    scanf(\"%d%d%d\", &n, &p, &m);\r\n    for (int i = 1; i <= n; i++) {\r\n        for (int j = 1; j <= p; j++) {\r\n            scanf(\"%lld\", &a[i][j]);\r\n        }\r\n    }\r\n    for (int i = 1; i <= p; i++) {\r\n        for (int j = 1; j <= m; j++) {\r\n            scanf(\"%lld\", &b[i][j]);\r\n        }\r\n    }\r\n    for (int i = 5; i <= n; i++) {\r\n        for (int j = 5; j <= m; j++) {\r\n            for (int k = 1; k <= p; k++) {\r\n                c[i][j] = (c[i][j] + a[i][k] * b[k][j] + mod * mod + mod) % mod;\r\n            }\r\n        }\r\n    }\r\n    for (int i = 1; i <= n; i++) {\r\n        for (int j = 1; j <= m; j++) {\r\n            printf(\"%lld \", c[i][j] % mod);\r\n        }\r\n        puts(\"\");\r\n    }\r\n}",
        "language": "C_PLUS_PLUS",
        "judgePreference": "ACM"
    }
}
