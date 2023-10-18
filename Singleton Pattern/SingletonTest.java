import java.io.*;
import java.lang.reflect.Constructor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingletonTest {

    public static void main(String[] args) throws Exception {
        SingletonExample instanceOne = SingletonExample.getSingleton();
        System.out.println(instanceOne.hashCode());

//       SingletonExample singleton2 = SingletonExample.getSingleton();
//       System.out.println(singleton2.hashCode());

        Constructor<SingletonExample> constructor = SingletonExample.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        SingletonExample singleton2= constructor.newInstance();
        System.out.println(singleton2.hashCode());

        SingletonEnum singleton = SingletonEnum.INSTANCE;
        System.out.println(singleton.hashCode());
//
//        Constructor<SingletonEnum> constructor = SingletonEnum.class.getDeclaredConstructor();
//       constructor.setAccessible(true);
//       SingletonEnum singleton2= constructor.newInstance();
//       System.out.println(singleton2.hashCode());

        ObjectOutput out = new ObjectOutputStream(new FileOutputStream( "filename.ser"));

        out.writeObject(instanceOne); out.close();

        ObjectInput in = new ObjectInputStream(new FileInputStream( "filename.ser"));

        SingletonExample instanceTwo = (SingletonExample) in.readObject();
        in.close();

        System.out.println("instanceOne hashCode="+instanceOne.hashCode());
        System.out.println("instanceTwo hashCode="+instanceTwo.hashCode());

        SingletonExample cloneSingleton= (SingletonExample) instanceOne.clone();
        System.out.println("clone object: "+cloneSingleton.hashCode());

        int noOfThreads = 5;

        ExecutorService executorService = Executors.newFixedThreadPool(noOfThreads);

        for (int i = 0; i < noOfThreads; i++) {
            executorService.execute(new SingletonThread());
        }
    }
}
