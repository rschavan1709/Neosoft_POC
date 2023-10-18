

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException, InterruptedException {
        System.out.println("Creating object using prototype design");

        NetworkConnection networkConnection=new NetworkConnection();

        networkConnection.setIp("192.168.4.4");
        networkConnection.loadVeryImportantData();

        System.out.println(networkConnection);
        NetworkConnection networkConnection2 = (NetworkConnection) networkConnection.clone();
        networkConnection.getDomains().remove(0);
        System.out.println(networkConnection);

        System.out.println(networkConnection2);

    }
}
