package designpattern.factory;

/**
 * Created by wizardholy on 2017/4/5.
 */
public class Client {

    public static void main(String[] args){
        String data = Constants.TYPE_FINANCIAL;
        ExportFactory exportFactory = new ExportPdfFactory();
        exportFactory.factory(data).export("");

        data = Constants.TYPE_TXT;
        exportFactory.factory(data).export("");

        data = Constants.TYPE_FINANCIAL;
        exportFactory = new ExportTxtFactory();
        exportFactory.factory(data).export("");

        data = Constants.TYPE_TXT;
        exportFactory.factory(data).export("");
    }
}
