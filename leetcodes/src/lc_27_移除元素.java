import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class lc_27_移除元素 {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) return new int[0];
        String[] strs = input.split(",");
        int[] output = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            output[i] = Integer.parseInt(str);
        }
        return output;
    }

    public static String integerArrayToString(int[] nums, int length) {
        if (length == 0) return "[]";
        String result = "";
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            result += Integer.toString(num) + ", ";
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
            line = in.readLine();
            int val = Integer.parseInt(line);
            int ret = new Solution_27().removeElement(nums,val);
            String out = integerArrayToString(nums,ret);
            System.out.println(out);
        }

    }

}

class Solution_27 {
    public int removeElement(int[] nums, int val) {

        /**
         * begin
         *   3,   2,   2,   3  val=3
         *                 end-1
         */

        // 双指针: 时间复杂度: O(n), 空间复杂度:O(1)
//        int begin = 0;
//        for (int end = 0; end < nums.length; end++) {
//            if (nums[end] != val) {
//                nums[begin] = nums[end];
//                begin++;
//            }
//        }
//        return begin;

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
}

