import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class lc_217_存在重复元素 {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1,input.length() - 1);
        if (input.length() == 0) return new int[0];

        String[] parts = input.split(",");
        int[] outputs = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            outputs[i] = Integer.parseInt(parts[i].trim());
        }
        return outputs;
    }

    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
            boolean ret = new Solution_217().containsDuplicate(nums);
            String out = booleanToString(ret);
            System.out.println(out);
        }
    }

}

class Solution_217 {

    // 哈希表
    public boolean containsDuplicate1(int[] nums) {
        Map<Integer, Integer> numMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (numMap.containsKey(nums[i])) {
                Integer v = numMap.get(nums[i]);
                ++v;
                numMap.put(nums[i], v);
            } else {
                numMap.put(nums[i], 1);
            }
            if (numMap.get(nums[i]) > 1) return true;
        }
        return false;
    }

    // 排序
    public boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    // HashSet
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            // set.add 集合中还没有的话,返回 true, 有的话返回 false
            if (!set.add(x)) {
                return true;
            }
        }
        return false;
    }

}