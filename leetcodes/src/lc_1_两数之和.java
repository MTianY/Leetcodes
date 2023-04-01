import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class lc_1_两数之和 {

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();   // 去掉 String 前后空格
        input = input.substring(1, input.length() - 1); // 去掉首尾 [ ]
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
        for (int i = 0; i <length; i++) {
            int number = nums[i];
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2);
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
            int target = Integer.parseInt(line);
            int[] ret = new Solution1().twoSum(nums, target);
            String out = integerArrayToString(ret);
            System.out.print(out);
        }
    }

}

class Solution1 {
    public int[] twoSum(int[] nums, int target) {
//        Map<Integer,Integer> temp = new HashMap<Integer, Integer>();
//        for (int i = 0; i < nums.length; i++) {
//            if (temp.containsKey(target - nums[i])) {
//                return new int[]{temp.get(target - nums[i]), i};
//            }
//            temp.put(nums[i], i);
//        }
//        return new int[0];

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
}
