# File: money.py
# Description: 最小费用流
# Created: 2020-9-8 14:45:59
# Author: yuzhanglong
# Email: yuzl1123@163.com

requestBody = {
    "AC": {
        "problemId": 10007,
        "codeContent": "#include <stdio.h>\r\n#include <string.h>\r\n#include <algorithm>\r\n#include <iostream>\r\n#include <queue>\r\n\r\n#define ll long long\r\n#define NM 1005\r\n#define nm 40005\r\nusing namespace std;\r\nconst ll inf = 100000000000000005;\r\nll read() {\r\n    ll x = 0, f = 1;\r\n    char ch = getchar();\r\n    while (!isdigit(ch)) {\r\n        if (ch == '-')\r\n            f = -1;\r\n        ch = getchar();\r\n    }\r\n    while (isdigit(ch)) x = x * 10 + ch - '0', ch = getchar();\r\n    return f * x;\r\n}\r\n\r\nstruct edge {\r\n    int t;\r\n    ll v, w;\r\n    edge *next, *rev;\r\n} e[nm], *h[NM], *o = e, *p[NM];\r\nvoid _add(int x, int y, int w, int v) {\r\n    o->t = y;\r\n    o->v = v;\r\n    o->w = w;\r\n    o->next = h[x];\r\n    h[x] = o++;\r\n}\r\nvoid add(int x, int y, int w, int v) {\r\n    _add(x, y, w, v);\r\n    _add(y, x, 0, -v);\r\n    h[x]->rev = h[y];\r\n    h[y]->rev = h[x];\r\n}\r\nint n, m, _x, _y, _t, _w;\r\nll ans, s, d[NM], w[NM];\r\nbool v[NM];\r\nqueue<int> q;\r\n\r\nint spfa() {\r\n    memset(d, 0, sizeof(d));\r\n    memset(v, 0, sizeof(v));\r\n    memset(w, 0, sizeof(w));\r\n    memset(p, 0, sizeof(p));\r\n    for (int i = 1; i <= n; i++) d[i] = inf;\r\n    d[1] = 0;\r\n    v[1]++;\r\n    w[1] = inf;\r\n    q.push(1);\r\n    while (!q.empty()) {\r\n        int t = q.front();\r\n        q.pop();\r\n        v[t] = false;\r\n        for (edge *j = h[t]; j; j = j->next)\r\n            if (j->w && d[j->t] > d[t] + j->v) {\r\n                d[j->t] = d[t] + j->v;\r\n                w[j->t] = min(w[t], j->w);\r\n                p[j->t] = j;\r\n                if (!v[j->t])\r\n                    v[j->t]++, q.push(j->t);\r\n            }\r\n    }\r\n    return w[n];\r\n}\r\n\r\nint main() {\r\n    n = read();\r\n    m = read();\r\n    for (int i = 1; i <= m; i++) {\r\n        _x = read();\r\n        _y = read();\r\n        _w = read();\r\n        _t = read();\r\n        add(_x, _y, _w, _t);\r\n    }\r\n    while (spfa()) {\r\n        ans += w[n];\r\n        s += w[n] * d[n];\r\n        for (int x = n; p[x]; x = p[x]->rev->t) p[x]->w -= w[n], p[x]->rev->w += w[n];\r\n    }\r\n    return 0 * printf(\"%lld %lld\\n\", ans, s);\r\n}",
        "language": "C_PLUS_PLUS",
        "judgePreference": "ACM"
    },
    "WA": {
        "problemId": 10007,
        "codeContent": "#include <iostream>\r\nusing namespace std;\r\nint main() {\r\n    int a, b;\r\n    cin >> a >> b;\r\n    cout << a - b;\r\n}",
        "language": "C_PLUS_PLUS",
        "judgePreference": "ACM"
    }
}
