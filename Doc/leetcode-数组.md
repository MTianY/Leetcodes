# Leetcode 数组

### 26. 删除有序数组中的重复项. 

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

