package sample;

import javafx.beans.property.SimpleIntegerProperty;

public class HeapData {
    private SimpleIntegerProperty address;
    private SimpleIntegerProperty value;
    public HeapData(Integer address, Integer value){
        this.address=new SimpleIntegerProperty(address);
        this.value=new SimpleIntegerProperty(value);
    }
    public Integer getAddress() {
        return address.get();
    }

    public  Integer getValue() {
        return value.get();
    }
}
