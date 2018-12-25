package algorithm.tire;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * DoubleArrayTrie: Java implementation of Darts (Double-ARray Trie System)
 * <p>
 * <p>
 * Copyright(C) 2001-2007 Taku Kudo &lt;taku@chasen.org&gt;<br />
 * Copyright(C) 2009 MURAWAKI Yugo &lt;murawaki@nlp.kuee.kyoto-u.ac.jp&gt;
 * Copyright(C) 2012 KOMIYA Atsushi &lt;komiya.atsushi@gmail.com&gt;
 * </p>
 * <p>
 * <p>
 * The contents of this file may be used under the terms of either of the GNU
 * Lesser General Public License Version 2.1 or later (the "LGPL"), or the BSD
 * License (the "BSD").
 * </p>
 */

public class DoubleArrayTrieTest {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./data/small.dic"));
        String line;
        List<String> words = new ArrayList<String>();
        Set<Character> charset = new HashSet<Character>();
        while ((line = reader.readLine()) != null)
        {
            words.add(line);
            // 制作一份码表debug
            for (char c : line.toCharArray())
            {
                charset.add(c);
            }
        }
        reader.close();
        // 这个字典如果要加入新词必须按字典序，参考下面的代码
//        Collections.sort(words);
//        BufferedWriter writer = new BufferedWriter(new FileWriter("./data/sorted.dic", false));
//        for (String w : words)
//        {
//            writer.write(w);
//            writer.newLine();
//        }
        System.out.println("字典词条：" + words.size());

        {
            String infoCharsetValue = "";
            String infoCharsetCode = "";
            for (Character c : charset)
            {
                infoCharsetValue += c.charValue() + "    ";
                infoCharsetCode += (int)c.charValue() + " ";
            }
            infoCharsetValue += '\n';
            infoCharsetCode += '\n';
            System.out.print(infoCharsetValue);
            System.out.print(infoCharsetCode);
        }

        DoubleArrayTrie dat = new DoubleArrayTrie();
        System.out.println("是否错误: " + dat.build(words));
        System.out.println(dat);
        System.out.println(dat.exactMatchSearch("一举成名"));
        List<Integer> integerList = dat.commonPrefixSearch("一举成名而天下知");
        for (int index : integerList)
        {
            System.out.println(words.get(index));
        }
    }
}