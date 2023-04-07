import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class lc_219_存在重复元素_2 {
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

    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
            line = in.readLine();
            int k = Integer.parseInt(line);
            boolean ret = new Solution_219().containsNearbyDuplicate(nums, k);
            System.out.println(booleanToString(ret));
        }
    }
}

class Solution_219 {
    // 超出时间限制了...
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        int left = 0;
        int right = left+1;
        while (left < nums.length - 1) {
            if (nums[left] != nums[right]) {
                if (right < nums.length - 1) {
                    ++right;
                } else {
                    ++left;
                    right = left + 1;
                }
            } else {
                int cmp = Math.abs(right - left);
                if (cmp <= k) {
                    return true;
                } else {
                    if (right < nums.length - 1) {
                        right++;
                    } else {
                        ++left;
                        right = left + 1;
                    }
                }
            }
        }
        return false;
    }

    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            if (map.containsKey(num) && Math.abs(i - map.get(num)) <= k) {
                return true;
            }
            map.put(num, i);
        }
        return false;
    }

    // 滑动窗口
    public boolean containsNearbyDuplicate3(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            // 如果 i>k, 则下标 i-k-1 处的元素被移除滑动窗口, 因此将 i-k-1 移除哈希集合.
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            // 判断 nums[i] 是否在哈希集合中, 如果在, 则在同一个滑动窗口中, 返回 ture. 如果不在, 则将其加入哈希集合
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

}