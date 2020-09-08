# File: division.py
# Description: 高精度除法
# Created: 2020-9-8 14:49:08
# Author: yuzhanglong
# Email: yuzl1123@163.com


requestBody = {
    "AC": {
        "problemId": 10009,
        "codeContent": "#include <bits/stdc++.h>\r\n#define mod 513546\r\nusing namespace std;\r\nlong long t, k, n, m, head[1000005];\r\nstruct data {\r\n    long long num, nex, j;\r\n} vec[1000005];\r\nlong long ksm(long long x, long long y, long long z) {\r\n    long long ans = 1;\r\n    while (y) {\r\n        if (y & 1)\r\n            ans = (ans * x) % z;\r\n        x = (x * x) % z;\r\n        y >>= 1;\r\n    }\r\n    return ans;\r\n}\r\nlong long ny(long long x, long long y, long long z) { return (ksm(x, z - 2, z) * y) % z; }\r\nvoid hash_in(long long x, long long y) {\r\n    long long down = x % mod;\r\n    for (long long i = head[down]; i; i = vec[i].nex)\r\n        if (vec[i].num == x)\r\n            return;\r\n    n++;\r\n    vec[n].nex = head[down];\r\n    vec[n].num = x;\r\n    vec[n].j = y;\r\n    head[down] = n;\r\n}\r\nlong long hash_find(long long x, long long y) {\r\n    long long down = x % mod;\r\n    for (long long i = head[down]; i; i = vec[i].nex)\r\n        if (vec[i].num == x)\r\n            return y * m - vec[i].j;\r\n    return -1;\r\n}\r\nint main() {\r\n    scanf(\"%lld%lld\", &t, &k);\r\n    while (t--) {\r\n        long long y, z, p, o = -1;\r\n        scanf(\"%lld%lld%lld\", &y, &z, &p);\r\n        if (k == 1)\r\n            printf(\"%lld\\n\", ksm(y, z, p));\r\n        if (k == 2) {\r\n            if (y % p == 0)\r\n                printf(\"Orz, I cannot find x!\\n\");\r\n            else\r\n                printf(\"%lld\\n\", ny(y, z, p));\r\n        }\r\n        if (k == 3) {\r\n            n = 0;\r\n            memset(head, 0, sizeof(head));\r\n            if (y % p == 0) {\r\n                printf(\"Orz, I cannot find x!\\n\");\r\n                continue;\r\n            }\r\n            m = sqrt(p);\r\n            if (m * m != p)\r\n                m++;\r\n            for (long long i = 0; i < m; i++) hash_in((z * ksm(y, i, p)) % p, i);\r\n            for (long long i = 0; i <= m; i++) {\r\n                o = hash_find(ksm(y, i * m, p), i);\r\n                if (o >= 0)\r\n                    break;\r\n            }\r\n            if (o >= 0)\r\n                printf(\"%lld\\n\", o);\r\n            else\r\n                printf(\"Orz, I cannot find x!\\n\");\r\n        }\r\n    }\r\n    return 0;\r\n}",
        "language": "C_PLUS_PLUS",
        "judgePreference": "ACM"
    },
    "WA": {
        "problemId": 10009,
        "codeContent": "#include <iostream>\r\nusing namespace std;\r\nint main() {\r\n    int a, b;\r\n    cin >> a >> b;\r\n    cout << a - b;\r\n}",
        "language": "C_PLUS_PLUS",
        "judgePreference": "ACM"
    }
}
