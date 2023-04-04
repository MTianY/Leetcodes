import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class lc_35_插入搜索位置 {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) return new int[0];

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
            line = in.readLine();
            int target = Integer.parseInt(line);
            int ret = new Solution_35().searchInsert(nums, target);
            String out = String.valueOf(ret);
            System.out.println(out);
        }
    }
}

class Solution_35 {
    public int searchInsert(int[] nums, int target) {
        // 二分查找
        // 1 3 5 6,  5
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