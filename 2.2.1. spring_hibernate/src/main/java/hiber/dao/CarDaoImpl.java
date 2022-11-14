package hiber.dao;

import hiber.model.Car;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CarDaoImpl implements CarDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Transactional
    @Override
    @SuppressWarnings("unchecked")
    public List<Car> listCars() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }
}
