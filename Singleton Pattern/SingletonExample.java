import java.io.Serializable;

public class SingletonExample implements Serializable,Cloneable {

    private static SingletonExample singleton;

    private SingletonExample(){
//        if (singleton != null)
//            throw new RuntimeException("Try to break singleton pattern");
    }

    public static SingletonExample getSingleton(){
        if (singleton == null) {
            synchronized (SingletonExample.class) {
                if (singleton == null) {
                    singleton = new SingletonExample();
                }
            }
        }
        return singleton;
    }

    public Object readResolve(){
        return singleton;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return singleton;
    }
}
