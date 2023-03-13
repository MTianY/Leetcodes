# Leetcode 刷题

# 1. KMP 算法

## KMP 算法解决的问题:

解决字符串匹配的问题

如: 文本串: aabaabaaf. 模式串: aabaaf. 文本串中是否出现模式串.

### 前缀表

以`aabaaf` 为例, 

其`前缀`: 前缀是包含首字母, 不包含尾字母的所有子串.

- a
- aa
- aab
- aaba
- aabaa
- aabaaf ❌ 不是, 包含了尾字母

`后缀`: 后缀是包含尾字母, 不包含首字母的所有子串.

- f
- af
- aaf
- baaf
- abaaf
- aabaaf ❌ 不是, 包含了首字母.

求最长相等前后缀.

- a      0
- aa     1
- aab    0
- aaba   1
- aabaa  2
- aabaaf 0

所以`aabaaf`的前缀表就是 `0 1 0 1 2 0`.

所以找重复字符串:

文本串:  aabaabaaf
模式串:  aabaaf

找到 b 和 f, 发现不相等. 模式串跳回到 b 的位置.重新和文本串匹配. 

## 代码实现

前缀表定义为 `next`数组.

