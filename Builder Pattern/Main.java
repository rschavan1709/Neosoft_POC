public class Main {

    public static void main(String[] args) {

        User user = new User.UserBuilder()
                .setUserId("RIDDHI123")
                .setUserName("Riddhi")
                .setEmailId("rsc@gmail.com")
                .build();

        System.out.println(user);

        User user2 = User.UserBuilder.builder()
                .setUserId("KAJAL123")
                .setUserName("Kajal")
                .setEmailId("kpm@gmail.com")
                .build();

        System.out.println(user2);
    }
}
