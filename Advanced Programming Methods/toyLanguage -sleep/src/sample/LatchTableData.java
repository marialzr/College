package sample;

import javafx.beans.property.SimpleIntegerProperty;

public class LatchTableData {
    private SimpleIntegerProperty id;
    private SimpleIntegerProperty counterValue;
    public LatchTableData(Integer id, Integer counterValue){
        this.id=new SimpleIntegerProperty(id);
        this.counterValue=new SimpleIntegerProperty(counterValue);
    }
    public Integer getId() {
        return id.get();
    }

    public  Integer getCounterValue() {
        return counterValue.get();
    }
}
