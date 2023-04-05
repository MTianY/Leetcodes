import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class lc_119_杨辉三角_2 {
    public static String integerArrayListToString(List<Integer> nums, int length) {
        if (length == 0) {
            return "[]";
        }

        String result = "";
        for(int index = 0; index < length; index++) {
            Integer number = nums.get(index);
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayListToString(List<Integer> nums) {
        return integerArrayListToString(nums, nums.size());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int rowIndex = Integer.parseInt(line);

            List<Integer> ret = new Solution_119().getRow(rowIndex);

            String out = integerArrayListToString(ret);

            System.out.print(out);
        }
    }
}

class Solution_119 {
    public List<Integer> getRow(int rowIndex) {

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i <= rowIndex; i++) {
            List<Integer> rowList = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    rowList.add(1);
                } else {
                    rowList.add(result.get(i-1).get(j-1) + result.get(i-1).get(j));
                }
            }
            result.add(rowList);
        }

        if (rowIndex == 0) {
            return result.get(0);
        } else {
            return result.get(rowIndex);
        }
    }

    // 优化: 用一个数组实现
    public List<Integer> getRow_1(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        result.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            result.add(0);
            for (int j = i; j > 0; j--) {
                result.set(j, result.get(j) + result.get(j - 1));
            }
        }
        return result;
    }
}

