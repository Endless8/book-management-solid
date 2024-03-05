package org.example.bookmanagement.datasource;

import org.example.bookmanagement.wrapper.MapperWrapper;

public class DatasourceFactoryImp implements DatasourceFactory {

    public Datasource createDatasource(MapperWrapper mapperWrapper, DatasourceType datasourceType) {
        return new DatasourceImp(mapperWrapper, datasourceType);
    }
}
