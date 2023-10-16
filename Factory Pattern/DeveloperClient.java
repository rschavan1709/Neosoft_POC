

public class DeveloperClient {

    public static void main(String[] args) {
      Employee employee=  EmployeeFactory.getEmployee("FRONTEND DEVELOPER");
      System.out.println("Salary: "+employee.salary());

      Employee employee1=EmployeeFactory.getEmployee("BACKEND DEVELOPER");
      System.out.println("Salary: "+employee1.salary());
    }
}
