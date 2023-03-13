import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 猜字谜
 *
 * 时间限制：1s 空间限制：256MB 限定语言：不限
 *
 * 题目描述：
 *
 * 小王设计了一个简单的猜字谜游戏，游戏的谜面是一个错误的单词，比如nesw，玩家需要猜出谜底库中正确的单词。猜中的要求如下：
 *
 * 对于某个谜面和谜底单词，满足下面任一条件都表示猜中：
 *
 * 1）变换顺序以后一样的，比如通过变换w和e的顺序，“nwes”跟“news”是可以完全对应的；
 *
 * 2）字母去重以后是一样的，比如“woood”和“wood”是一样的，它们去重后都是“wod”
 *
 * 请你写一个程序帮忙在谜底库中找到正确的谜底。谜面是多个单词，都需要找到对应的谜底，如果找不到的话，返回"not found"
 *
 * 输入描述：
 *
 * 1、谜面单词列表，以","分隔
 *
 * 2、谜底库单词列表，以","分隔
 *
 * 输出描述：
 *
 * 匹配到的正确单词列表，以“,”分隔
 *
 * 如果找不到，返回"not found"
 *
 * 补充说明：
 *
 * 1、单词的数量N的范围：0 < N < 1000
 *
 * 2、词汇表的数量M的范围： 0 < M < 1000
 *
 * 3、单词的长度P的范围：0 < P < 20
 *
 * 4、输入的字符只有小写英文字母，没有其它字符
 *
 * 示例1
 * 输入：
 * conection
 *
 * connection,today
 *
 * 输出：
 * connection
 *
 * 示例2
 * 输入：
 * bdni,wooood
 *
 * bind,wrong,wood
 *
 * 输出：
 * bind,wood
 */

public class nk_猜字谜 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 谜面
        String[] mimian = in.nextLine().split(",");
        // 谜底
        String[] midi = in.nextLine().split(",");

        List<String> res = new ArrayList<>();
        for (int i = 0; i < mimian.length; i++) {
            String mm = mimian[i];
            System.out.println("谜面: mm = " + mm);
            boolean isFound = false;
            for (int j = 0; j < midi.length; j++) {
                String md = midi[j];
                System.out.println("谜底: md = " + md);
                if (bianxu(mm, md)) {
                    res.add(md);
                    isFound = true;
                } else if (qichong(mm, md)) {
                    res.add(md);
                    isFound = true;
                }
            }
            if (!isFound) {
                System.out.println("not found");
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.size(); i++) {
            sb.append(res.get(i) + ",");
        }
        String str = sb.toString();
        System.out.println(str.substring(0, str.length() - 1));
    }

    private static boolean bianxu(String mimian, String midi) {
        String[] mimianStr = mimian.split("");
        String[] midiStr = midi.split("");

        for (int i = 0; i < mimianStr.length; i++) {
            System.out.println("变序: mimian = " + mimianStr[i]);
        }

        for (int i = 0; i < midiStr.length; i++) {
            System.out.println("变序: midi = " + midiStr[i]);
        }

        Arrays.sort(mimianStr);
        Arrays.sort(midiStr);

        if (Arrays.equals(mimianStr, midiStr)) {
            return true;
        }
        return false;
    }

    private static boolean qichong(String mimian, String midi) {
        List<Character> mimianList = new ArrayList<>();
        for (int i = 0; i < mimian.length(); i++) {
            if (!mimianList.contains(mimian.charAt(i))) {
                mimianList.add(mimian.charAt(i));
            }
        }

        List<Character> midiList = new ArrayList<>();
        for (int i = 0; i < midi.length(); i++) {
            if (!midiList.contains(midi.charAt(i))) {
                midiList.add(midi.charAt(i));
            }
        }

        if (mimianList.equals(midiList)) {
            return true;
        }

        return false;
    }

}
