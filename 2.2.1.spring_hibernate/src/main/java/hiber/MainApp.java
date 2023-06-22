package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        Car bmw = new Car("BMW", 110);
        Car mercedes = new Car("Mercedes", 112);
        Car audi = new Car("Audi", 113);
        Car toyota = new Car("Toyota", 114);

        user1.setCar(bmw);
        user2.setCar(mercedes);
        user3.setCar(audi);
        user4.setCar(toyota);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

//        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println();
        System.out.println("----------------------------------------");
        System.out.println();

        User user = userService.getUserByModel("BMW");
        System.out.println(user);
//
//        List<User> usersByModel = userService.getUserByModel("BMW");
//        for (User user : usersByModel) {
//            System.out.println(user);
//        }
//        List<User> usersBySeries = userService.getUserBySeries(5);
//        for (User user: usersBySeries) {
//            System.out.println(user);
//        }
        context.close();
    }
}
