# Leetcode 数组

[TOC]

## 数组基础理论:

[参考文章: 代码随想录-数组理论基础](https://www.programmercarl.com/%E6%95%B0%E7%BB%84%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html#%E6%95%B0%E7%BB%84%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80)

总结:

- 数组是存放在连续内存空间上的相同类型数据的集合.
- 数组下标都是从 0 开始
- 数组内存空间的地址是连续的, 所以在`删除`或者`增加`元素的时候, 难免要移动其他元素的地址
- 数组的元素不能删除, 只能覆盖.

/* 1, 3, 5, 6     2 
   0  1  2  3  
   
   
*/

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


