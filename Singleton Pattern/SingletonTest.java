import java.lang.reflect.Constructor;

public class SingletonTest {

    public static void main(String[] args) throws Exception {
       SingletonExample singleton1 = SingletonExample.getSingleton();
       System.out.println(singleton1.hashCode());

//       SingletonExample singleton2 = SingletonExample.getSingleton();
//       System.out.println(singleton2.hashCode());

       Constructor<SingletonExample> constructor = SingletonExample.class.getDeclaredConstructor();
       constructor.setAccessible(true);
       SingletonExample singleton2= constructor.newInstance();
       System.out.println(singleton2.hashCode());
    }
}
