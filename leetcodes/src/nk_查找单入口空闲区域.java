import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 查找单入口空闲区域
 * 时间限制：1s 空间限制：256MB 限定语言：不限
 *
 * 题目描述：
 * 给定一个 m x n 的矩阵，由若干字符 'X' 和 'O'构成，'X'表示该处已被占据，'O'表示该处空闲，请找到最大的单入口空闲区域。
 *
 * 解释：
 *
 * 空闲区域是由连通的'O'组成的区域，位于边界的'O'可以构成入口，单入口空闲区域即有且只有一个位于边界的'O'作为入口的由连通的'O'组成的区域。
 *
 * 如果两个元素在水平或垂直方向相邻，则称它们是“连通”的。
 *
 * 输入描述：
 * 第一行输入为两个数字，第一个数字为行数m，第二个数字列数n，两个数字以空格分隔，1 <= m, n <= 200，
 *
 * 剩余各行为矩阵各行元素，元素为'X' 或 'O'，各元素间以空格分隔。
 *
 * 输出描述：
 * 若有唯一符合要求的最大单入口空闲区域，输出三个数字，第一个数字为入口行坐标（范围为0~行数-1），第二个数字为入口列坐标（范围为0~列数-1），第三个数字为区域大小，三个数字以空格分隔；
 *
 * 若有多个符合要求的最大单入口空闲区域，输出一个数字，代表区域的大小；
 *
 * 若没有，输出NUL。
 *
 * 示例1
 * 输入：
 *
 * 4 4
 *
 * X X X X
 *
 * X O O X
 *
 * X O O X
 *
 * X O X X
 *
 * 输出：
 *
 * 3 1 5
 *
 * 说明：
 *
 * 存在最大单入口区域，入口行坐标3，列坐标1，区域大小5
 *
 * 示例2
 * 输入：
 *
 * 4 5
 *
 * X X X X X
 *
 * O O O O X
 *
 * X O O O X
 *
 * X O X X O
 *
 * 输出：
 *
 * 3 4 1
 *
 * 说明：
 *
 * 存在最大单入口区域，入口行坐标3，列坐标4，区域大小1
 *
 * 示例3
 * 输入：
 *
 * 5 4
 *
 * X X X X
 *
 * X O O O
 *
 * X O O O
 *
 * X O O X
 *
 * X X X X
 *
 * 输出：
 *
 * NULL
 *
 * 说明：
 *
 * 不存在最大单入口区域
 *
 * 示例4
 *
 * 输入：
 *
 * 5 4
 *
 * X X X X
 *
 * X O O O
 *
 * X X X X
 *
 * X O O O
 *
 * X X X X
 *
 * 输出：
 *
 * 3
 *
 * 说明：
 *
 * 存在两个大小为3的最大单入口区域，两个入口横纵坐标分别为1,3和3,3
 *
 * 解题思路：
 *
 * 通过回溯法求出所有满足的区域
 *
 * 在回溯的同时记录其入口坐标
 *
 * 入口个数大于1则不符合要求；
 *
 * 入口个数等于1时，判断其区域大小；如果存在多个区域，且区域大小相同，则
 *
 * 只需记录其大小；其他情况则需要记录区域最大值和横纵坐标。
 *
 * ps:这道题感觉究极逆天，居然只有100分，建议uu考试之前还是攒一攒人品
 */

public class nk_查找单入口空闲区域 {

    static int m;
    static int n;
    static String[][] map = new String[m][n];
    static int[] res = new int[2];
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        map = new String[m][n];
        List<int []> quyu = new ArrayList<>();  // 统计 最大区域的入口坐标 x y 及其区域大小
        int maxSize = 0;
        sc.nextLine();
        for (int i = 0; i < m; i++) {
            String[] val = sc.nextLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = val[j];
            }
        }

        // 遍历 map
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j].equals("O")) {
                    map[i][j] = "X"; // 替换坐标
                    List<int[]> position = new ArrayList<>();   // 位置集合
                    f(i,j,position);
                    if (count == 1) {
                        if (maxSize == position.size()) {
                            quyu.clear();
                        } else if (maxSize < position.size()) {
                            quyu.clear();
                            quyu.add(new int[]{res[0], res[1], position.size()});
                            maxSize = position.size();
                        }
                    }
                }
                // 重置参数
                count = 0;
                res = new int[2];
            }
        }

        if (quyu.size() == 1) {
            int[] outRes = quyu.get(0);
            System.out.println(outRes[0] + " " + outRes[1] + " " + outRes[2]);
        } else if (maxSize != 0) {
            System.out.println(maxSize);
        } else {
            System.out.println("NULL");
        }

    }

    /**
     * @param x 横坐标
     * @param y 纵坐标
     * @param list 坐标列表
     */
    static void f(int x, int y, List<int[]> list) {
        // 判断是否为入口
        if (x == 0 || y == 0 || x == m - 1 || y == n - 1) {
            count++;
            res[0] = x;
            res[1] = y;
        }
        if (x < m - 1) {
            if (map[x + 1][y].equals("O")) {
                map[x + 1][y] = "X";
                list.add(new int[]{x + 1, y});
                f(x + 1, y, list);
            }
        }
        if (y < n - 1) {
            if (map[x][y + 1].equals("O")) {
                map[x][y + 1] = "X";
                list.add(new int[]{x, y + 1});
                f(x + 1, y, list);
            }
        }
        if (x > 0) {
            if (map[x - 1][y].equals("O")) {
                map[x - 1][y] = "X";
                list.add(new int[]{x - 1, y});
                f(x - 1, y, list);
            }
        }
        if (y > 0) {
            if (map[x][y - 1].equals("O")) {
                map[x][y - 1] = "X";
                list.add(new int[]{x + 1, y});
                f(x, y - 1, list);
            }
        }
    }

}
