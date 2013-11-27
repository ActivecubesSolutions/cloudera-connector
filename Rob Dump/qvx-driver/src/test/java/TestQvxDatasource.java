import com.ontometrics.qvx.writer.HeaderConfiguration;
import com.ontometrics.qvx.writer.QvxDataSource;

import java.util.Iterator;
import java.util.List;

/**
 * TestQvxDatasource.java
 * Created on 10 10, 2013 by Andrey Chorniy
 */
public class TestQvxDatasource implements QvxDataSource {
    private HeaderConfiguration headerConfiguration;
    private List<Object[]> dataList;

    public TestQvxDatasource(HeaderConfiguration headerConfiguration, List<Object[]> dataList) {
        this.headerConfiguration = headerConfiguration;
        this.dataList = dataList;
    }

    @Override
    public HeaderConfiguration geHeaderConfiguration() {
        return headerConfiguration;
    }

    @Override
    public Iterator<Object[]> getDataIterator() {
        return dataList.iterator();
    }
}
