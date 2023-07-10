package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public User getUserByModel(String model) {

        User user = sessionFactory.getCurrentSession()
                .createQuery("select u from User u inner join u.car where Car.model = :model", User.class)
                .setParameter("model", model).getSingleResult();
        return user;
        //        List<Car> cars = sessionFactory.getCurrentSession()
//                .createQuery("from Car where model = model", Car.class).list();

//        List<User> users = new ArrayList<>();
//        for (Car car: cars) {
//            users.add(car.getUser());
//        }
    }

    @Override
    public List<User> getUserBySeries(int series) {
        List<Car> cars = sessionFactory.getCurrentSession().
                createQuery("from Car where series = series", Car.class).list();
        List<User> users = new ArrayList<>();
        for (Car car: cars) {
            users.add(car.getUser());
        }
        return users;
    }
}
