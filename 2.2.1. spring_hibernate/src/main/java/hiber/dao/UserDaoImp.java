package hiber.dao;

import hiber.model.User;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Transactional
    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        Query query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<User> getUserByCar(String model, int series) {
        Query<User> query = sessionFactory.getCurrentSession()
                .createQuery("FROM User as u WHERE u.car.model =:model AND u.car.series =:series", User.class)
                .setParameter("model", model)
                .setParameter("series", series);
        return query.getResultList();
    }
}
