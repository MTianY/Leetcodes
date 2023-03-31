import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {

    // 时间复杂度: O(n)
    // 空间复杂度: O(1)
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

    // 优化
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

}

public class lc_26_删除有序数组中的重复项 {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }
        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static String integerArrayToString(int[] nums, int length) {
        if (length == 0) {
            return "[]";
        }
        String result = "";
        for (int index = 0; index < length; index++) {
            int number = nums[index];
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayToString(int[] nums) {
        return integerArrayToString(nums, nums.length);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
            int ret = new Solution().removeDuplicates(nums);
            String out = integerArrayToString(nums,ret);
            System.out.print(out);
        }
    }
}
