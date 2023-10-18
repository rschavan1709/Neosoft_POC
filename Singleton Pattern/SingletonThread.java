public class SingletonThread implements Runnable{
    @Override
    public void run() {
        SingletonExample singleton = SingletonExample.getSingleton();
        System.out.println("hash code: "+singleton.hashCode());
    }
}
