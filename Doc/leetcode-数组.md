# Leetcode 数组

[TOC]

## 数组基础理论:

[参考文章: 代码随想录-数组理论基础](https://www.programmercarl.com/%E6%95%B0%E7%BB%84%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html#%E6%95%B0%E7%BB%84%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80)

总结:

- 数组是存放在连续内存空间上的相同类型数据的集合.
- 数组下标都是从 0 开始
- 数组内存空间的地址是连续的, 所以在`删除`或者`增加`元素的时候, 难免要移动其他元素的地址
- 数组的元素不能删除, 只能覆盖.


## 1. 两数之和

> easy. [leetcode_1_两数之和](https://leetcode.cn/problems/two-sum/)

给定一个整数数组 `nums` 和一个整数目标值 `target`, 请你在该数组中找出 **和为目标值`target`**的那**两个**整数, 并返回它们的数组下标.

你可以假设每种输入只会对应一个答案. 但是, 数组中同一个元素在答案里不能重复出现.

你可以按任意顺序返回答案.

**示例 1:**

```c
输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
```

**示例 2:**

```c
输入：nums = [3,2,4], target = 6
输出：[1,2]
```

**示例 3:**

```c
输入：nums = [3,3], target = 6
输出：[0,1]
```

**提示**

- 2 <= nums.length <= 10^4
- -10^9 <= nums[i] <= 10^9
- -10^9 <= target <= 10^9
- ***只会存在一个有效答案**

**进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？**

**解题思路:**

##### 1. 暴力枚举 ( 复杂度 O(n²), 最坏情况下匹配 n * n 次. 空间复杂度 O(1) )

- 枚举数组中的每一个数 `x`, 找到数组中是否存在 `target - x`.
- 注意每一个位于 `x` 之前的元素都已经和 `x` 匹配过, 因此不需要再进行匹配.

```java
public int[] twoSum(int[] nums, int target) {
   // 暴力枚举
   for (int i = 0; i < nums.length; i++) {
       for (int j = i + 1; j < nums.length; j++) {
           if (nums[i] + nums[j] == target) {
               return new int[]{i, j};
           }
       }
   }

   return new int[0];
}
```

##### 2. 哈希表

- 枚举整个数组, 如果哈希表中存在 `target - nums[i]` 元素. 则返回 `target - nums[i]` 的索引, 和 `i` 的索引.
- 如果哈希表中不存在 `target - nums[i]`, 则将 `nums[i] 和 i`存入哈希表, key 是 `nums[i]`, value 是 `i`.

```java
public int[] twoSum(int[] nums, int target) {
   Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
   for (int i = 0; i < nums.length; ++i) {
       if (hashtable.containsKey(target - nums[i])) {
           return new int[]{hashtable.get(target - nums[i]), i};
       }
       hashtable.put(nums[i], i);
   }
   return new int[0];
}
```


## 26. 删除有序数组中的重复项. 

> easy
> [leetcode_26_删除有序数组中的重复项](https://leetcode.cn/problems/remove-duplicates-from-sorted-array/)

给你一个 `升序排列` 的数组 `nums`, 请你`原地`删除重复出现的元素, 使每个元素`只出现一次`, 返回删除后数组的新长度. 元素的`相对顺序`应该保持`一致`.

由于在某些语言中不能改变数组的长度，所以必须将结果放在数组`nums`的第一部分。更规范地说，如果在删除重复项之后有 `k` 个元素，那么 `nums` 的前 `k` 个元素应该保存最终结果。

将最终结果插入 `nums` 的前 `k` 个位置后返回 `k`.

不要使用额外的空间, 你必须在`原地`修改输入数组`并在使用 O(1)额外空间的条件下完成`.

**判题标准:**

系统会用下面的代码来测试你的题解:

```c
int[] nums = [...]; // 输入数组
int[] expectedNums = [...]; // 长度正确的期望答案

int k = removeDuplicates(nums); // 调用

assert k == expectedNums.length;
for (int i = 0; i < k; i++) {
    assert nums[i] == expectedNums[i];
}
```

如果所有断言都通过，那么您的题解将被 `通过`。

**示例 1:**

```c
输入：nums = [1,1,2]
输出：2, nums = [1,2,_]
解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
```

**示例 2:**

```c
输入：nums = [0,0,1,1,1,2,2,3,3,4]
输出：5, nums = [0,1,2,3,4]
解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
```

**提示:**

- 1 <= nums.length <= 3 * 10^4
- -10^4 <= nums[i] <= 10^4
- nums 已按 升序 排列

#### 解题思路:

**双指针**;

- 定义 `begin` , `end` 两个指针. `begin = 0, end = 1;`
- 比较两个位置元素是否相等
    - 相等的话, `end++`
    - 不相等的话, 将`end`位置元素覆盖到 `begin+1` 位置上.
- 直到 end > 数组长度时结束循环.

```java
public int removeDuplicates1(int[] nums) {
   if (nums == null || nums.length == 0) return 0;
   int begin = 0;
   int end = 1;
   while (end < nums.length) {
       if (nums[end] - nums[begin] == 0) {
           // 相等: begin 不动, end 加 1
           end++;
           System.out.println("end1 = " + end);
       }else if (nums[end] - nums[begin] > 0) {
           // end 位置元素大于 begin 位置: end 位置元素覆盖到 begin+1 位置. begin++, end++

           nums[begin+1] = nums[end];
           begin++;
           end++;
           System.out.println("end2 = " + end + " begin = " + begin);
       }
   }

   for (int i = 0; i < nums.length; i++) {
       System.out.print(nums[i] + "_");
   }
   return begin + 1;
}
```

**优化**

如果数组中没有重复元素, 每次比较 begin 和 end 元素都不相等, 每次都要覆盖元素. 非必要操作.

加个判断, 当 `end - begin > 1` 时, 才覆盖.

```java
public int removeDuplicates(int[] nums) {
   if (nums == null || nums.length == 0) return 0;
   int begin = 0;
   int end = 1;
   while (end < nums.length) {
       if (nums[begin] != nums[end]) {
           if (end - begin > 1) {
               nums[begin+1] = nums[end];
           }
           begin++;
       }
       end++;
   }
   return begin + 1;
}
```

## 27. 移除元素

> easy. [leetcode_27_移除元素](https://leetcode.cn/problems/remove-element/)

给你一个数组`nums`和一个值`val`,你需要`原地`移除所有数值等于`val`的元素,并返回移除后数组的新长度.

不要使用额外的数组空间, 你必须仅使用`O(1)`额外空间并`原地`修改输入数组.

元素的顺序可以改变. 你不需要考虑数组中超出新长度后面的元素.

**说明**

为什么返回数值是整数，但输出的答案是数组呢?

请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。

你可以想象内部操作如下:

```c
// nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
int len = removeElement(nums, val);

// 在函数里修改输入数组对于调用者是可见的。
// 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
for (int i = 0; i < len; i++) {
    print(nums[i]);
}
```

**示例 1:**

```c
输入：nums = [3,2,2,3], val = 3
输出：2, nums = [2,2]
解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
```

**示例 2:**

```c
输入：nums = [0,1,2,2,3,0,4,2], val = 2
输出：5, nums = [0,1,4,0,3]
解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。
```

**提示:**

- 0 <= nums.length <= 100
- 0 <= nums[i] <= 50
- 0 <= val <= 100

#### 解题思路: 双指针

- 定义两个指针, begin = 0 和 end = 0.
- 根据题意解题, end 小于数组长度, end++, 当 nums[end] != val 时, 将 begin 位置元素用 end 位置元素覆盖掉.同时 begin++, 指向新覆盖位置元素 

```java
public int removeElement(int[] nums, int val) {
   // 双指针: 时间复杂度: O(n), 空间复杂度:O(1)
   begin = 0;
   for (int end = 0; end < nums.length; end++) {
     if (nums[end] != val) {
         nums[begin] = nums[end];
         begin++;
        }
   }
   return begin;
}
```

- 双指针优化: 因为题意说元素的顺序可以改变, 所以将 end 为数组长度. begin = 0;
- 当 begin == end 时, 结束循环.

```java
public int removeElement(int[] nums, int val) {

   /**
    * begin
    *   3,   2,   2,   3  val=3
    *                 end-1
    */

   // 双指针优化;
   int begin = 0;
   int end = nums.length;
   while (begin < end) {
       if (nums[begin] == val) {
           nums[begin] = nums[end - 1];
           end--;
       } else {
           begin++;
       }
   }
   return begin;
}
```

## 35. 搜索插入位置

给定一个排序数组和一个目标值, 在数组中找到目标值, 并返回其索引. 如果目标值不存在于数组中, 返回它将会被按顺序插入的位置.

请必须使用时间复杂度为 `O(log n)` 的算法.

示例 1:

```c
输入: nums = [1,3,5,6], target = 5
输出: 2
```

示例 2:

```c
输入: nums = [1,3,5,6], target = 2
输出: 1
```

示例 3:

```c
输入: nums = [1,3,5,6], target = 7
输出: 4
```

#### 解题思路: 二分查找

```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        int begin = 0;
        int end = nums.length;
        int ret = end;  // 这个位置要传数组长度, 解决 target 大于所有值的情况,即插入位置
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (nums[mid] < target) {
                begin = mid + 1;
            } else if (nums[mid] > target) {
                end = mid;
                ret = mid;
            } else {
                ret = mid;
                return mid;
            }
        }
        return ret;
    }
}
```


## 66. 加一

给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。

最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。

你可以假设除了整数 0 之外，这个整数不会以零开头。

示例 1:

```c
输入：digits = [1,2,3]
输出：[1,2,4]
解释：输入数组表示数字 123。
```

示例 2:

```c
输入：digits = [4,3,2,1]
输出：[4,3,2,2]
解释：输入数组表示数字 4321。
```

示例 3:

```c
输入：digits = [0]
输出：[1]
```

#### 解题思路: 找出最长的后缀 9

对数组加 1 时, 只需要关注数组末尾出现了多少个 9 即可. 

- 第 1 种: 如果数组末尾没有 9, 例如 [1,2,3], 那么直接将末尾的数加 1, 得到 [1,2,4] 并返回
- 第 2 种: 如果数组末尾有若干个 9, 例如 [1,2,3,9,9], 那么只需要找出从末尾开始的第一个不为 9 的元素,即 3,将其加 1.得到[1,2,4,9,9], 随后将末尾的 9 全部置零.得到[1,2,4,0,0]返回
- 第 3 种: 所有元素都是 9, [9,9,9,9,9], 那么答案是[1,0,0,0,0,0,0] .只需要构造一个长度比原数组多 1 个新数组,将首元素置为 1,其余元素均为 0 即可.

```java
class Solution {
    public int[] plusOne(int[] digits) {
        for (int i = digits.length-1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                for (int j = i + 1; j < digits.length; j++) {
                    digits[j] = 0;
                }
                return digits;
            }
        }

        int[] ret = new int[digits.length + 1];
        ret[0] = 1;
        return ret;
    }
}
```

## 108. 将有序数组转换为二叉搜索树

## 118. 杨辉三角

给定一个非负整数 `numRows`, 生成 [杨辉三角] 的前 `numRows` 行.

在 [杨辉三角] 中, 每个数是它左上方和右上方的数的和.

示例 1:

```c
输入: numRows = 5
输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
```

实例 2:

```c
输入: numRows = 1
输出: [[1]]
```

##### 解题思路:

- 杨辉三角: 每行的一个元素值, 等于其上一行左上角的值加右上角的值.
- 考虑只有 1 行的情况.
- 杨辉三角每行数字左右对称, 每行开始及结尾数字均为 1.

```java
public List<List<Integer>> generate(int numRows) {

   List<List<Integer>> result = new LinkedList<>();

   for (int i = 0; i < numRows; i++) {
       List<Integer> subList = new LinkedList<>();
       System.out.println(">>> i = " + i);
       for (int j = 0; j <= i; j++) {
           System.out.println("j = " + j);
           if (j == 0 || j == i) {
               // 开始及结尾数字均为 1
               subList.add(1);
           } else {
               // 其余数字, 用上一行的左上角和右上角值相加
               subList.add(result.get(i-1).get(j-1) + result.get(i-1).get(j));
           }
       }
       result.add(subList);
   }

   return result;
}
```


## 119. 杨辉三角 II

给定一个非负索引 `rowIndex`, 返回 [杨辉三角] 的第 `rowIndex` 行. 在 [杨辉三角] 中, 每个数是它左上方和右上方的数的和.

示例 1:

```c
输入: rowIndex = 3
输出: [1,3,3,1]
```

示例 2:

```c
输入: rowIndex = 0
输出: [1]
```

示例 3:

```c
输入: rowIndex = 1
输出: [1,1]
```

#### 解题思路:

杨辉三角另一种. 返回单个行.

注意: 这里第一行的 rowIndex = 0.

```java
class Solution_119 {
    public List<Integer> getRow(int rowIndex) {

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i <= rowIndex; i++) {
            List<Integer> rowList = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    rowList.add(1);
                } else {
                    rowList.add(result.get(i-1).get(j-1) + result.get(i-1).get(j));
                }
            }
            result.add(rowList);
        }

        if (rowIndex == 0) {
            return result.get(0);
        } else {
            return result.get(rowIndex);
        }
    }
}
```


## 121. 买卖股票的最佳时机

给定一个数组 `prices`, 它的第 `i` 个元素 `prices[i]` 表示一支给定股票第 `i` 天的价格.
你只能选择 `某一天` 买入这只股票, 并选择在 `未来的某一个不同的日子` 卖出该股票. 设计一个算法来计算你所能获取的最大利润.
返回你可以从这笔交易中获取的最大利润. 如果你不能获取任何利润, 返回 `0`;

示例 1:

```c
输入：[7,1,5,3,6,4]
输出：5
解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
```

示例 2:

```c
输入：prices = [7,6,4,3,1]
输出：0
解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
```

#### 解题思路:

暴力法: (leetcode 提交不通过, 超时...)

```java
public int maxProfit1(int[] prices) {

   // 最大利润
   int maxPrice = 0;
   for (int i = 0; i < prices.length; i++) {
       System.out.println("i = " + i);
       for (int j = i + 1; j < prices.length; j++) {
           maxPrice = Math.max(maxPrice, prices[j] - prices[i]);
           System.out.println("j = " + j + "; rest = " + maxPrice);
       }
   }

   return maxPrice;

}
```

一次遍历, 找出最低点. 然后用比最低点大的值 减去 最低点的值, 算出利润.

```java
public int maxProfit(int[] prices) {
   // 找到历史最低点
   int minPrice = Integer.MAX_VALUE;
   // 利润
   int maxProfit = 0;
   for (int i = 0; i < prices.length; i++) {
       if (prices[i] < minPrice) {
           minPrice = prices[i];
       } else if (prices[i] - minPrice > maxProfit) {
           maxProfit = prices[i] - minPrice;
       }
   }
   return maxProfit;
}
```

## 136. 只出现一次的数字

给你一个 非空 整数数组 `nums`, 除了某个元素只出现一次以外, 其余每个元素均出现两次. 找出那个只出现了一次的元素.

你必须设计并实现线性时间复杂度的算法来解决此问题, 且该算法只使用常量额外空间.

示例 1:

```c
输入：nums = [2,2,1]
输出：1
```

示例 2:

```c
输入：nums = [4,1,2,1,2]
输出：4
```

示例 3:

```c
输入：nums = [1]
输出：1
```

#### 解题思路: 

- 如果不考虑时间复杂度和空间复杂度的限制, 可以使用如下解法:
    - 集合. 存储数字. 遍历数组中的每个数字, 如果集合中没有该数字, 则将该数字加入集合, 如果集合已经有该数字, 则将该数字从集合中删除, 最后剩下的数字就是.
    - 哈希表. 存储每个数字和该数字出现的次数. 遍历数组即可得到每个数字出现的次数, 并更新哈希表, 最后遍历哈希表, 得到只出现一次的数字.
- 上面需要额外的 O(n) 的空间, n 是数字长度.
- 根据题意,使用`位运算`. `异或`运算. 特点如下:
    - a^0 = a
    - a^a = 0
    - 交换律: a^b^a = b^a^a
    - 结合律: (a^b)^c = a^(b^c) 

假设数组中有 2m + 1 个数, m 是出现 2 次的数, 那么数组所有元素异或运算结果肯定是剩下的单个数(即 a[m+1]).

- (a1^a1)^(a2^a2)^...^(am^am)^a[m+1]
- 简化为 0^0^0^...^0^a[m+1]
- 结果就是单个数  a[m+1]

```java
class Solution_136 {
    public int singleNumber(int[] nums) {

        /**
         * 异或: 同为真, 不同为假. 二进制位运算
         * 结合律: (a^b)^c = a^(b^c)
         * 交换律: a^b=b^a
         * 与自身异或: a^a=0
         * 与0 异或: a^0 = a
         */

        /**
         * 0 1
         * 1 1
         * 1 0
         */

        int single = 0;
        for (int num : nums) {
            System.out.println("[1] single = " + single + "; num = " + num);
            single = single^num;
            System.out.println("[2] single = " + single);
            System.out.println("============= ");
        }
        return single;
    }
}
```

## 217. 存在重复元素

给你一个整数数组`nums`. 如果任一值在数组中出现**至少两次**, 返回`true`. 如果数组中每个元素互不相同, 返回`false`.

示例 1:

```sh
输入: nums = [1,2,3,1]
输出: true
```

示例 2:

```sh
输入: nums = [1,2,3,4]
输出: false
```

示例 3:

```sh
输入: nums = [1,1,1,3,3,4,3,2,4,2]
输出: true
```

#### 解题思路:

**1. HashSet**

- 使用 `HashSet` 数据结构. 遍历数组`nums`, 执行函数`set.add(x)`, 其含义是: 集合中还没有元素的话,返回 true, 有的话返回 false.

```java
 public boolean containsDuplicate(int[] nums) {
      Set<Integer> set = new HashSet<>();
      for (int x : nums) {
          // set.add 集合中还没有的话,返回 true, 有的话返回 false
          if (!set.add(x)) {
              return true;
          }
      }
      return false;
  }
```

**2. HashMap**

```java
public boolean containsDuplicate1(int[] nums) {
      Map<Integer, Integer> numMap = new HashMap<Integer, Integer>();
      for (int i = 0; i < nums.length; i++) {
          if (numMap.containsKey(nums[i])) {
              Integer v = numMap.get(nums[i]);
              ++v;
              numMap.put(nums[i], v);
          } else {
              numMap.put(nums[i], 1);
          }
          if (numMap.get(nums[i]) > 1) return true;
      }
      return false;
  }
```

**3. 排序**

- 对数组从小到大排序.
- 排序后比较相邻两元素是否相同, 如有相同则返回 true.

```java
public boolean containsDuplicate2(int[] nums) {
      Arrays.sort(nums);
      for (int i = 0; i < nums.length-1; i++) {
          if (nums[i] == nums[i + 1]) {
              return true;
          }
      }
      return false;
  }
```



