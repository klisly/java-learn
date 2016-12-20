import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class JsoupParse {
    String[] my_userAgent = new String[]{
            "Mozilla/5.0 (Windows NT 5.1; rv:37.0) Gecko/20100101 Firefox/37.0",
            "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:6.0) Gecko/20100101 Firefox/6.0",
            "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; GTB7.0)",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.835.163 Safari/535.1",
            "Opera/9.80 (Windows NT 6.1; U; zh-cn) Presto/2.9.168 Version/11.50"};

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.parse(new URL("http://www.23wxw.cc/html/19/"), 6000);
        Elements eles = doc.select("body > div.wrapper > div:nth-child(4) > div.layout > div.detailWrap > div.detailTxt > div.detailTil > h1");
        if (eles.size() > 0) {
            System.out.print(eles.get(0).html());
        }
    }
}
