package designpattern.factory;

/**
 * Created by wizardholy on 2017/4/5.
 */
public class ExportPdfFactory implements ExportFactory {
    @Override
    public ExportFile factory(String type) {
        if(type.equals(Constants.TYPE_FINANCIAL)){
            return new ExportFinancialPdfFile();
        } else if(type.equals(Constants.TYPE_TXT)){
            return new ExportStandardPdfFile();
        }
        return null;
    }
}
