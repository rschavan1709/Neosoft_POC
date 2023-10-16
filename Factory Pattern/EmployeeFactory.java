

public class EmployeeFactory {

    public static Employee getEmployee(String empType){
        if (empType.trim().equalsIgnoreCase("FRONTEND DEVELOPER")){
            return new FrontEndDeveloper();
        }else if (empType.trim().equalsIgnoreCase("BACKEND DEVELOPER")){
            return new BackEndDeveloper();
        }
        else {
            return null;
        }
    }
}
