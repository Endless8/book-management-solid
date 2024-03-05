package org.example.bookmanagement.datasource;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.bookmanagement.wrapper.MapperWrapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DatasourceImp implements Datasource {

    private final String fileName;
    private final MapperWrapper mapperWrapper;

    public DatasourceImp(MapperWrapper mapperWrapper, DatasourceType datasourceType) {
        this.mapperWrapper = mapperWrapper;
        fileName = String.format("%s.json", datasourceType.getCollectionName());
    }

    @Override
    public <T> void writeRecordsToFile(List<T> objects) {
        mapperWrapper.writeValue(new File(fileName), objects);
    }

    public <T> List<T> getRecordsFromFile() {
        File file = new File(fileName);

        if (!file.exists())
            return new ArrayList<>();

        return mapperWrapper.readValue(file, new TypeReference<>() {
        });
    }
}
