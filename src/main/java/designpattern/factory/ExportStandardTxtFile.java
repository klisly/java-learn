package designpattern.factory;

/**
 * Created by wizardholy on 2017/4/5.
 */
public class ExportStandardTxtFile implements ExportFile {
    @Override
    public boolean export(String data) {
        System.out.println("Export standard txt");
        return true;
    }
}
