public class FrontEndDeveloper implements Employee{
    @Override
    public int salary() {
        return 50000;
    }

    @Override
    public String name() {
        System.out.println("I am Frontend Developer");
        return "FRONTEND DEVELOPER";
    }
}
