public class BackEndDeveloper implements Employee{
    @Override
    public int salary() {
        return 55000;
    }

    @Override
    public String name() {
        System.out.println("I am Backend Developer");
        return "BACKEND DEVELOPER";
    }
}
