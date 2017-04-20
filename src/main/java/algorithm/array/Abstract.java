package algorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 题目：抽象点说，就是在一个字符串中，找一些目标字符串，找到包含所有目标字符串的最小字符串。
 题目虽然叫做最短摘要生成，但和实际的搜索snippet的计算还是有比较大的差距的。

 解法：文献[1]给出了一种比较好的解法，策略还是使用双指针，双指针对于很多算法设计很有价值，
 算法的思想是采用两个指针，开始两个指针都指向缓冲区的头部，尾指针向后扫描，
 直到头指针和尾指针中间包含了全部的关键字，那么头指针向后移动，直到包含全部关键字这个条件失败，
 这时截取字串并和已取得的最小字串比较，如果小则替换。头指针、尾指针都向后一个位置
 (这点很重要，开始就忘记了移动头指针，导致程序出错)，继续扫描。另外，由于一个关键字可能重复多次，
 因此在判断是否包含全部关键字时要采用计数机制，才能保证查看的准确。这样只要头指针和尾指针扫
 描两次字符串就可以完成生成算法。
 */
public class Abstract {

/*
 * 编程之美 最短摘要生成
 * */

    private int[] keywordsArray; //记录关键字被访问次数的数组
    private int pBegin = 0;//查找起始点
    private int pEnd = 0;//查找终点
    private int abstractBegin = 0;//摘要起始点
    private int abstractEnd = 0;//摘要终点
    private int targetLen;//摘要最小长度
    private Map<String, Integer> map;//将关键字映射成数字

    public Abstract(String[] keywords) {
        int len = keywords.length;
        this.keywordsArray = new int[len];
        this.map = keywordsMap(keywords);
    }

    public String extractSummary(String description, String[] keywords) {
        String[] array = description.split(" ");//将字符串转化为数组
        return extract(array, keywords);
    }

    //实际的抽取函数
    public String extract(String[] description, String[] keywords) {
        String summary = "";
        int nLen = description.length;
        targetLen = nLen + 1;
        while (true) {
            while (!isAllExisted() && pEnd < nLen) {
                if (this.map.get(description[pEnd]) != null) {
                    setKeywordsArray(keywordsArray, this.map.get(description[pEnd]), 0);
                }
                pEnd++;
            }
            while (isAllExisted()) {
                if (pEnd - pBegin < targetLen) {
                    targetLen = pEnd - pBegin;
                    abstractBegin = pBegin;
                    abstractEnd = pEnd - 1;
                }
                if (map.get(description[pBegin]) != null) {
                    setKeywordsArray(keywordsArray, map.get(description[pBegin]), 1);
                }
                pBegin++;
            }
            if (pEnd >= nLen) {
                break;
            }
        }
        for (int j = abstractBegin; j <= abstractEnd; j++) {
            if (j != abstractEnd) {
                summary = summary + description[j] + " ";
            } else {
                summary += description[j];
            }
        }
        return summary;
    }

    public Map<String, Integer> keywordsMap(String[] keywords) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        int len = keywords.length;
        for (int i = 0; i < len; i++) {
            map.put(keywords[i], i);
        }
        return map;
    }

    //设置关键字被访问到的次数
    public void setKeywordsArray(int[] keywordsArray, int i, int flag) { //flag:0 add flag:1 sub
        if (flag == 0) {
            keywordsArray[i]++;
        } else {
            keywordsArray[i]--;
        }
    }

    //检查是否包含全部关键字
    public boolean isAllExisted() {
        boolean result = true;
        for (int a : keywordsArray) {
            if (a == 0) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String description = "hello software hello test world spring sun flower hello";
        String[] keywords = {"hello", "world", "flower"};
        Abstract nAbstract = new Abstract(keywords);
        System.out.println(nAbstract.extractSummary(description, keywords));
    }
}
