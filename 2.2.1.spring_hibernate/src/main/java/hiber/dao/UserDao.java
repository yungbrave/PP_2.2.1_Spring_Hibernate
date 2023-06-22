package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);
   List<User> listUsers();

   User getUserByModel(String model);

   List<User> getUserBySeries(int series);
}
