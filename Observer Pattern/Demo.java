import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Demo {

    public static void main(String[] args) throws IOException {

        Subject channel=new YouTubeChannel();
        Observer riddhi=new Subscriber("Riddhi");
        Observer kajal=new Subscriber("Kajal");
        channel.subscribe(riddhi);
        channel.subscribe(kajal);
        channel.newVideoUploaded("Learn Design Pattern");
        channel.newVideoUploaded("New Angular Course");

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        while (true){

            System.out.println("Press 1 to upload Video");
            System.out.println("Press 2 create new Subscriber");
            System.out.println("Press 3 unsubscribe");
            System.out.println("Press 4 to exit");
            int c=Integer.parseInt(br.readLine());
            if (c == 1){
                System.out.println("Enter video title: ");
                String videoTitle=br.readLine();
                channel.newVideoUploaded(videoTitle);

            }else if (c == 2){
                System.out.println("Enter name of subscriber: ");
                String subsName=br.readLine();
                Observer subscriber3 =  new Subscriber(subsName);
                channel.subscribe(subscriber3);
            }else if (c == 3){
                System.out.println("Enter name of subscriber: ");
                String subsName=br.readLine();
                channel.unsubscribe(subsName);
            }else if (c == 4){
                System.out.println("Thank you for using app");
                break;
            }else{
                System.out.println("Wrong input");
            }
        }

    }
}
