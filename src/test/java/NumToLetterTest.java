import java.util.*;
import java.util.regex.Pattern;

/**
 * <p>本程序用控制台输入的数字代替输入数组，方便测试</p >
 *
 * @Author: 李观龙
 * @Version 1.0
 * @Since JDK1.8
 */
public class NumToLetterTest {
    public static void main(String[] args) {
        System.out.println("-----------------------------NumToLetterTest-----------------------------");
        System.out.println("Please enter  positive integer and  press enter");
        Scanner scanner = new Scanner(System.in);
        String numsString = scanner.nextLine();
        Pattern pattern = Pattern.compile("[0-9]*");
        boolean numFlag = pattern.matcher(numsString).matches();
        //结果集
        List<String> ans;

        //如果输入为纯数字才执行,否则结束程序
        if (numFlag) {
            char[] oldCharArray = numsString.toCharArray();
            StringBuilder sb = new StringBuilder();
            //去除0跟1
            for (char c : oldCharArray) {
                if (c != '0' && c != '1') {
                    sb.append(c);
                }
            }
            ans = letterCombinations(String.valueOf(sb));

            //矩阵方式输出到控制台
            System.out.println("Input: " + numsString);
            System.out.println("The number of outputs is: " + ans.size());
            System.out.println("Output: ");
            if (ans.size() > 0) {
                for (int i = 0; i < ans.size(); i++) {
                    if ((i % 10 == 0 && i != 0)) {
                        System.out.println("");
                    }
                    System.out.print(ans.get(i) + " ");
                    if (i == ans.size() - 1) {
                        System.out.println("");
                    }
                }
            }

        } else {
            System.out.println("The input data is illegal");
        }
        System.out.println("-------------------Test is over. Thank you for your use-------------------");

    }

    //合并数字组合
    private static List<String> letterCombinations(String numsString) {
        List<String> ans = new LinkedList<>();
        //特殊判断
        if (numsString.length() == 0) {
            return ans;
        }
        //初始化map
        Map<Character, String> map = new LinkedHashMap<>();
        map.put('2', "ABC");
        map.put('3', "DEF");
        map.put('4', "GHI");
        map.put('5', "JKL");
        map.put('6', "MNO");
        map.put('7', "PQRS");
        map.put('8', "TUV");
        map.put('9', "WXYZ");
        char[] chars = numsString.toCharArray();
        StringBuilder sb = new StringBuilder();
        backtrace(0, map, chars, sb, ans);
        return ans;
    }

    //回溯法
    private static void backtrace(int index, Map<Character, String> map, char[] chars, StringBuilder sb, List<String> ans) {
        //如果sb的容量达到了digits的长度，代表这次回溯已经完成，存入结果集
        if (sb.length() == chars.length) {
            ans.add(sb.toString());
            return;
        }
        //获取当前数字对应的多个字符
        String s = map.get(chars[index]);
        for (int i = 0; i < s.length(); i++) {
            //尝试加入当前数字对应的其中一个字符
            sb.append(s.charAt(i));
            //回溯
            backtrace(index + 1, map, chars, sb, ans);
            //删除最后一个加入的字符，达到回溯的目的
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
