import java.util.Scanner;

/**
 * 查找重复代码
 *
 * 时间限制：1s 空间限制：32MB 限定语言：不限
 *
 * 题目描述：
 *
 * 小明负责维护项目下的代码，需要查找出重复代码，用以支撑后续的代码优化，请你帮助小明找出重复的代码，。
 *
 * 重复代码查找方法：以字符串形式给定两行代码（字符串长度 1 < length <= 100，由英文字母、数字和空格组成），找出两行代码中的最长公共子串。
 *
 * 注： 如果不存在公共子串，返回空字符串
 *
 * 输入描述：
 *
 * 输入的参数text1, text2分别表示两行代码
 *
 * 输出描述：
 *
 * 输出任一最长公共子串
 *
 * 示例1
 *
 * 输入：
 *
 * hello123world
 *
 * hello123abc4
 *
 * 输出：
 *
 * hello123
 *
 * 说明：
 *
 * text1 = "hello123world", text2 = "hello123abc4", 最长的公共子串为 "hello123"
 *
 * 示例2
 *
 * 输入：
 *
 * private_void_method
 *
 * public_void_method
 *
 * 输出：
 *
 * _void_method
 *
 * 说明：
 *
 * text1 = "private_void_method", text2 = "public_void_method", 最长的公共子串为 "_void_method"
 *
 * 示例3
 *
 * 输入：
 *
 * hiworld
 *
 * hiweb
 *
 * 输出：
 *
 * hiw
 *
 * 说明：
 *
 * text1 = "hiworld", text2 = "hiweb", 最长的公共子串为 "hiw"
 *
 * 解题思路：
 *
 * 比较出两个字符串的长短
 *
 * 使用双层for循环截取短字符串，并判断是否为长字符串的子串，并找出其中最长的。没有的话，输出空字符串。
 */
public class nk_查找重复代码 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String text1 = in.nextLine();
        String text2 = in.nextLine();

        String longStr = text2.length() > text1.length() ? text2 : text1;
        String smallStr = text2.length() > text1.length() ? text1 : text2;
        String res = "";
        System.out.println("longStr = " + longStr);
        System.out.println("smallStr = " + smallStr);

        System.out.println("smallStr.length = " + smallStr.length());
        for (int i = 0; i < smallStr.length() - 1; i++) {
            System.out.println("i = " + i);
            for (int j = i + 1; j < smallStr.length(); j++) {
                System.out.println("j = " + j);
                String str = smallStr.substring(i, j);
                System.out.println("str = " + str);
                if (longStr.contains(str)) {
                    if (str.length() > res.length()) {
                        res = str;
                        System.out.println("res = " + res);
                    }
                }
            }
        }
        System.out.println(res);

    }

}
