package sample.utils;

public class IdGenerator {
    private static int id=0;
    public IdGenerator(){};
    public static int generateId(){
        id++;
        return id;
    }
}
