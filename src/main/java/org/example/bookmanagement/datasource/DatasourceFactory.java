package org.example.bookmanagement.datasource;

import org.example.bookmanagement.wrapper.MapperWrapper;

public interface DatasourceFactory {

    Datasource createDatasource(MapperWrapper mapperWrapper, DatasourceType datasourceType);
}
