public class BackEndDevFactory extends EmployeeAbstractFactory{
    @Override
    public Employee createEmployee() {
        return new BackEndDeveloper();
    }
}
