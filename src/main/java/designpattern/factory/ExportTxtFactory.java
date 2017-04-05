package designpattern.factory;

/**
 * Created by wizardholy on 2017/4/5.
 */
public class ExportTxtFactory implements ExportFactory {
    @Override
    public ExportFile factory(String type) {
        if(type.equals(Constants.TYPE_FINANCIAL)){
            return new ExportFinancialTxtFile();
        } else if(type.equals(Constants.TYPE_TXT)){
            return new ExportStandardTxtFile();
        }
        return null;
    }
}
