public class FrontEndDevFactory extends EmployeeAbstractFactory{
    @Override
    public Employee createEmployee() {
        return new FrontEndDeveloper();
    }
}
