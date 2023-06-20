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

    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        user.setCar(new Car("BMW", 5));
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public List<User> getUserByModel(String model) {
        List<Car> cars = sessionFactory.getCurrentSession()
                .createQuery("from Car where model = model", Car.class).list();
        List<User> users = new ArrayList<>();
        for (Car car: cars) {
            users.add(car.getUser());
        }
        return users;
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
