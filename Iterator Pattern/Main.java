public class Main {

    public static void main(String[] args) {
        UserManagement userManagement=new UserManagement();

        userManagement.addUser(new User("Riddhi","17"));
        userManagement.addUser(new User("Siddhi","12"));
        userManagement.addUser(new User("Kajal","10"));
        userManagement.addUser(new User("Anchal","04"));

        MyIterator myIterator = userManagement.getIterator();
        while (myIterator.hasNext()){
           User user = (User) myIterator.next();
           System.out.println(user.getName());
        }
    }
}
