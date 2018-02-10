package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BarrierData {
    private SimpleIntegerProperty address;
    private SimpleStringProperty threads;
    private SimpleIntegerProperty value;

    public BarrierData(Integer address, String threads, Integer value){
        this.address=new SimpleIntegerProperty(address);
        this.threads=new SimpleStringProperty(threads);
        this.value=new SimpleIntegerProperty(value);
    }

    public int getAddress() {
        return address.get();
    }

    public SimpleIntegerProperty addressProperty() {
        return address;
    }

    public String getThreads() {
        return threads.get();
    }

    public SimpleStringProperty threadsProperty() {
        return threads;
    }

    public int getValue() {
        return value.get();
    }

    public SimpleIntegerProperty valueProperty() {
        return value;
    }

}
