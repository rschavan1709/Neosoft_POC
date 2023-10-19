import java.util.ArrayList;
import java.util.List;

public class YouTubeChannel implements Subject {

    List<Observer>  subscribers = new ArrayList<>();

    @Override
    public void subscribe(Observer ob) {
        this.subscribers.add(ob);
    }

    @Override
    public void unsubscribe(String subsName) {
        Observer observer = (Observer) this.subscribers.stream().filter(s -> s.getName().equalsIgnoreCase(subsName) ).findAny().get();
        this.subscribers.remove(observer);
    }

    @Override
    public void newVideoUploaded(String title) {
        for (Observer ob:this.subscribers){
            ob.notified(title);
        }
    }
}
