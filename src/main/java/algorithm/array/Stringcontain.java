package algorithm.array;

import java.util.Hashtable;

/**
 题目描述：
 假设这有一个各种字母组成的字符串A，和另外一个字符串B，字符串里B的字母数相对少一些。
 什么方法能最快的查出所有小字符串B里的字母在大字符串A里都有？

 比如，如果是下面两个字符串：
 String 1: ABCDEFGHLMNOPQRS
 String 2: DCGSRQPO
 答案是true，所有在string2里的字母string1也都有。

 如果是下面两个字符串：
 String 1: ABCDEFGHLMNOPQRS
 String 2: DCGSRQPZ
 答案是false，因为第二个字符串里的Z字母不在第一个字符串里。
 */
public class Stringcontain {
    public static void main(String[] args){
        String str1 = "ABCDEFGHLMNOPQRS";
        String str2 = "DCGSRQPO";
        Hashtable<Integer, Integer> table = new Hashtable<>();
        for(int i = 0 ; i < str2.length(); i++){
            table.put(str2.charAt(i) - 'A', 0);
        }
        System.out.println(table.size());
        for(int i = 0; i < str1.length(); i++){
            if(table.get(str1.charAt(i) - 'A') != null && table.get(str1.charAt(i) - 'A') == 0){
                table.put(str1.charAt(i) - 'A', 1);
            }
        }
        System.out.println(table.size());
        int count = 0;
        for(int i = 0; i < str2.length(); i++){
            count += table.get(str2.charAt(i) - 'A');
        }
        System.out.println(count);
        System.out.println(count == str2.length());
    }

}
