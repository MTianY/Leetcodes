import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class lc_118_杨辉三角 {
    public static String integerArrayListToString(List<Integer> nums, int length) {
        if (length == 0) return "[]";
        String result = "";
        for (int i = 0; i < length; i++) {
            Integer number = nums.get(i);
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayListToString(List<Integer> nums) {
        return integerArrayListToString(nums, nums.size());
    }

    public static String int2dListToString(List<List<Integer>> nums) {
        StringBuilder sb = new StringBuilder("[");
        for (List<Integer> list: nums) {
            sb.append(integerArrayListToString(list));
            sb.append(",");
        }
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int numRows = Integer.parseInt(line);
            List<List<Integer>> ret = new Solution_118().generate(numRows);
            String out = int2dListToString(ret);
            System.out.println(out);
        }
    }

}

class Solution_118 {
    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> result = new LinkedList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> subList = new LinkedList<>();
            System.out.println(">>> i = " + i);
            for (int j = 0; j <= i; j++) {
                System.out.println("j = " + j);
                if (j == 0 || j == i) {
                    subList.add(1);
                } else {
                    subList.add(result.get(i-1).get(j-1) + result.get(i-1).get(j));
                }
            }
            result.add(subList);
        }

        return result;
    }
}

