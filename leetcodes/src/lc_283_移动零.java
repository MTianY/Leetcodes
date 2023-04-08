import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class lc_283_移动零 {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) return new int[0];
        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            output[i] = Integer.parseInt(parts[i].trim());
        }
        return output;
    }

    public static String integerArrayToString(int[] nums, int length) {
        if (length == 0) return "[]";
        String ret = "";
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            ret += Integer.toString(num) + ", ";
        }
        return "[" + ret.substring(0, ret.length() - 2) + "]";
    }

    public static String integerArrayToString(int[] nums) {
        return integerArrayToString(nums, nums.length);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
            new Solution_283().moveZeroes(nums);
            String out = integerArrayToString(nums);
            System.out.println(out);
        }
    }

}

class Solution_283 {
    // 0 1 0 3 12
    public void moveZeroes1(int[] nums) {
        if (nums == null) return;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        for (int i = j; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public void moveZeroes(int[] nums) {
        if (nums == null) return;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }
    }

}