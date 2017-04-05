package designpattern.factory;

/**
 * Created by wizardholy on 2017/4/5.
 */
public interface ExportFactory {
    public ExportFile factory(String type);
}
