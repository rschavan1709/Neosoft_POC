public class Client {

    public static void main(String[] args) {

       Employee e1 = EmployeeFactory.getEmployee(new FrontEndDevFactory());
       e1.name();

       Employee e2 = EmployeeFactory.getEmployee(new BackEndDevFactory());
       e2.name();

       Employee e3 = EmployeeFactory.getEmployee(new ManagerFactory());
       e3.name();
    }
}
