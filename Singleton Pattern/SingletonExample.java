

public class SingletonExample {

    private static SingletonExample singleton;

    private SingletonExample(){

    }

    public static SingletonExample getSingleton(){
        if (singleton == null){
            singleton=new SingletonExample();
        }
        return singleton;
    }
}
