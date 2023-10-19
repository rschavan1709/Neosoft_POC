public interface Subject {

    void subscribe(Observer ob);
    void unsubscribe(String subsName);
    void newVideoUploaded(String title);
}
