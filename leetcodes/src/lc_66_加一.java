import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class lc_66_加一 {

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
        for (int i = 0; i < nums.length; i++) {
            ret += Integer.toString(nums[i]) + ",";
        }
        return "[" + ret.substring(0, ret.length() - 2) + "]";
    }

    public static String integerArrayToString(int[] nums) {
        return integerArrayToString(nums, nums.length);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = in.readLine();
        while ((line = in.readLine()) != null) {
            int[] digits = stringToIntegerArray(line);
            int[] ret = new Solution_66().plusOne(digits);
            String out = integerArrayToString(ret);
            System.out.print(out);
        }
    }

}

class Solution_66 {

    public int[] plusOne(int[] digits) {

        // 找 9
        // 0 1 2 3
        // 1 9 5 9
        // 1 9 6 0
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