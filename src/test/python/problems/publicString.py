# File: publicString.py
# Description: 最长公共子串
# Created: 2020-9-8 14:43:08
# Author: yuzhanglong
# Email: yuzl1123@163.com

requestBody = {
    "AC": {
        "problemId": 10006,
        "codeContent": "#include <algorithm>\r\n#include <iostream>\r\n#include <iomanip>\r\n#include <cstring>\r\n#include <cstdlib>\r\n#include <cstdio>\r\n#include <cmath>\r\nusing namespace std;\r\nconst int Maxx = 10001, Maxn = 2001, INF = 0x3f3f3f3f, Mod = 1e9 + 7;\r\ninline int read() {\r\n    int res = 0, bj = 0;\r\n    char ch = getchar();\r\n    while (ch < '0' || ch > '9') bj |= (ch == '-'), ch = getchar();\r\n    while (ch >= '0' && ch <= '9') res = (res << 3) + (res << 1) + (ch ^ 48), ch = getchar();\r\n    return bj ? -res : res;\r\n}\r\nvoid print(int x) {\r\n    if (x > 9)\r\n        print(x / 10);\r\n    putchar(x % 10 ^ 48);\r\n}\r\ninline void printnum(int x, char ch) {\r\n    if (x < 0)\r\n        putchar('-'), x = -x;\r\n    print(x), putchar(ch);\r\n}\r\nint rt = 1, lst = 1, tot = 1, ln[Maxx << 1], prt[Maxx << 1];\r\nint ch[Maxx << 1][26];\r\ninline void Insert(int x) {\r\n    int u = ++tot, p = lst, q;\r\n    ln[u] = ln[p] + 1;\r\n    while (p && !ch[p][x]) ch[p][x] = u, p = prt[p];\r\n    if (!p)\r\n        prt[u] = rt;\r\n    else if (ln[p] + 1 == ln[q = ch[p][x]])\r\n        prt[u] = q;\r\n    else {\r\n        ln[++tot] = ln[p] + 1, memcpy(ch[tot], ch[q], sizeof(ch[q]));\r\n        prt[tot] = prt[q], prt[q] = prt[u] = tot;\r\n        while (ch[p][x] == q) ch[p][x] = tot, p = prt[p];\r\n    }\r\n    lst = u;\r\n}\r\nint n, rk[Maxx << 1], Sum[Maxx], Cur[Maxx << 1], Ans[Maxx << 1];\r\nchar A[Maxx];\r\ninline int Max(int a, int b) { return a > b ? a : b; }\r\ninline int Min(int a, int b) { return a < b ? a : b; }\r\ninline void FillCur(char X[], int len) {\r\n    int p = rt, op = 0;\r\n    for (int i = 0, t; i < len; ++i) {\r\n        t = X[i] - 'a';\r\n        while (p && !ch[p][t]) op = ln[p = prt[p]];\r\n        if (!p)\r\n            p = 1, op = 0;\r\n        else\r\n            p = ch[p][t], Cur[p] = Max(Cur[p], ++op);\r\n    }\r\n}\r\nint main() {\r\n    //\tfreopen(\"in.txt\",\"r\",stdin);\r\n    //\tfreopen(\"out.txt\",\"w\",stdout);\r\n    n = read(), scanf(\"%s\", A);\r\n    int i = 0;\r\n    while (A[i]) Insert(A[i++] - 'a');\r\n    for (int i = 1; i <= tot; ++i) ++Sum[ln[i]];\r\n    for (int i = 1; i <= ln[lst]; ++i) Sum[i] += Sum[i - 1];\r\n    for (int i = tot; i; --i) rk[Sum[ln[i]]--] = i;\r\n    memset(Ans, 0x3f, sizeof(Ans));\r\n    while (--n) {\r\n        scanf(\"%s\", A);\r\n        FillCur(A, strlen(A));\r\n        for (int i = tot, t; i; --i)\r\n            if (Cur[t = rk[i]])\r\n                Cur[prt[t]] = Min(Max(Cur[prt[t]], Cur[t]), ln[prt[t]]);\r\n        for (int i = 1; i <= tot; ++i) Ans[i] = Min(Ans[i], Cur[i]), Cur[i] = 0;\r\n    }\r\n    int Mx = 0;\r\n    for (int i = 1; i <= tot; ++i) Mx = Max(Mx, Ans[i]);\r\n    printnum(Mx, '\\n');\r\n    return 0;\r\n}",
        "language": "C_PLUS_PLUS",
        "judgePreference": "ACM"
    },
    "WA": {
        "problemId": 10006,
        "codeContent": "#include <iostream>\r\nusing namespace std;\r\nint main() {\r\n    int a, b;\r\n    cin >> a >> b;\r\n    cout << a - b;\r\n}",
        "language": "C_PLUS_PLUS",
        "judgePreference": "ACM"
    }
}