import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class lc_136_只出现一次的数字 {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) return new int[0];

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i].trim();
            output[i] = Integer.parseInt(part);
        }
        return output;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
            int ret = new Solution_136().singleNumber(nums);
            String out = String.valueOf(ret);
            System.out.println(out);
        }
    }

}

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