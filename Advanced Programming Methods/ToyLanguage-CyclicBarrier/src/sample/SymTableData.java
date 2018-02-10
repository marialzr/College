package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SymTableData {
    private SimpleStringProperty name;
    private SimpleIntegerProperty value;

    public SymTableData(String name, Integer value){
        this.name=new SimpleStringProperty(name);
        this.value=new SimpleIntegerProperty(value);
    }

    public String getName(){
        return this.name.get();
    }

    public Integer getValue(){
        return this.value.get();
    }
}
