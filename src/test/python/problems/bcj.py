# File: bcj.py
# Description: 并查集
# Created: 2020-9-8 14:33:14
# Author: yuzhanglong
# Email: yuzl1123@163.com

requestBody = {
    "AC": {
        "problemId": 10003,
        "codeContent": "#include <bits/stdc++.h>\r\nusing namespace std;\r\nstring s;\r\nint f[5000100];\r\nint read() {\r\n    char ch;\r\n    ch = getchar();\r\n    int f = 1, ans = 0;\r\n    while (!isdigit(ch)) {\r\n        if (ch == '-')\r\n            f = -1;\r\n        ch = getchar();\r\n    }\r\n    while (isdigit(ch)) {\r\n        ans = ans * 10 + ch - '0';\r\n        ch = getchar();\r\n    }\r\n    return ans * f;\r\n}\r\nint get(int x) {\r\n    if (f[x] == x)\r\n        return x;\r\n    return f[x] = get(f[x]);\r\n}\r\nvoid merge(int x, int y) { f[get(x)] = get(y); }\r\nint main() {\r\n    int n, m;\r\n    n = read();\r\n    m = read();\r\n    long long ans = 0;\r\n    for (int i = 1; i <= n; i++) f[i] = i;\r\n    for (int i = 1; i <= m; i++) {\r\n        int op, u, v;\r\n        op = read();\r\n        u = read();\r\n        v = read();\r\n        if (op == 0) {\r\n            merge(u, v);\r\n        } else {\r\n            if (get(u) == get(v)) {\r\n                ans = (ans << 1) + 1;\r\n            } else\r\n                ans = ans << 1;\r\n            ans %= 998244353;\r\n        }\r\n    }\r\n    printf(\"%lld\\n\", ans);\r\n    return 0;\r\n}",
        "language": "C_PLUS_PLUS",
        "judgePreference": "ACM"
    },
    "WA": {
        "problemId": 10003,
        "codeContent": "#include <cstdio>\r\n#define n_MAX 4000000L\r\nlong fat[n_MAX + 1L];\r\nlong find(long x) { return x == fat[x] ? x : fat[x] = find(fat[x]); }\r\nint main() {\r\n    long n, m, u, v, ans = 0L;\r\n    int op;\r\n    scanf(\"%ld%ld\", &n, &m);\r\n    for (long i = 1L; i <= n; ++i) fat[i] = i;\r\n    while (m--) {\r\n        scanf(\"%d%ld%ld\", &op, &u, &v);\r\n        switch (op) {\r\n            case 0:\r\n                fat[find(u)] = find(v);\r\n                break;\r\n            case 1:\r\n                ans = ((ans << 1) + (find(u) == find(v) ? 1L : 0L)) % 998244353L;\r\n        }\r\n    }\r\n    printf(\"%s\\n\", ans);\r\n printf(\"%s\\n\", ans);\r\n   return 0;\r\n}",
        "language": "C_PLUS_PLUS",
        "judgePreference": "ACM"
    }
}
