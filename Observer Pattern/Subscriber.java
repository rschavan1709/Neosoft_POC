public class Subscriber implements Observer{

    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void notified(String title) {
        System.out.println("Hello "+this.name+" new video uploaded : "+title);
    }

}
