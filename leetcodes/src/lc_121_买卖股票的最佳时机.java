import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class lc_121_买卖股票的最佳时机 {

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

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
            int[] prices = stringToIntegerArray(line);
            int ret = new Solution_121().maxProfit(prices);
            String out = String.valueOf(ret);
            System.out.println(out);
        }
    }

}

class Solution_121 {

    // 暴力法; 但是 leetcode 提交一直超时....
    public int maxProfit1(int[] prices) {

        // 最大利润
        int maxPrice = 0;
        for (int i = 0; i < prices.length; i++) {
            System.out.println("i = " + i);
            for (int j = i + 1; j < prices.length; j++) {
                maxPrice = Math.max(maxPrice, prices[j] - prices[i]);
                System.out.println("j = " + j + "; rest = " + maxPrice);
            }
        }

        return maxPrice;

    }

    public int maxProfit(int[] prices) {
        // 找到历史最低点
        int minPrice = Integer.MAX_VALUE;
        // 利润
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }

}