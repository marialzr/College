package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import sample.utils.FileData;

public class FileTableData {
    private SimpleIntegerProperty identifier;
    private SimpleStringProperty fileName;

    public FileTableData(Integer identifier, String fileName){
        this.identifier=new SimpleIntegerProperty(identifier);
        this.fileName=new SimpleStringProperty(fileName);
    }

    public Integer getIdentifier() {
        return identifier.get();
    }

    public String getFileName() {
        return fileName.get();
    }


}
