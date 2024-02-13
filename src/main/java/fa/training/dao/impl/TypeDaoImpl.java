package fa.training.dao.impl;

import fa.training.dao.TypeDao;
import fa.training.entities.Type;
import fa.training.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.List;

public class TypeDaoImpl implements TypeDao {
    @Override
    public void save(Type movie) {
        Session session = HibernateUtils.getCurrentSession();
        session.beginTransaction();
        session.persist(movie);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Type> findAll() {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Type> types = session.createQuery("SELECT a FROM type a", Type.class).getResultList();

        System.out.println("Types: " + types.size());
        session.getTransaction().commit();
        session.close();
        return types;
    }

    @Override
    public Type getById(Integer id) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Type type = session.get(Type.class, id);
        session.getTransaction().commit();
        session.close();
        return type;
    }

    @Override
    public void update(Type movie) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.merge(movie);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeById(Integer id) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.remove(session.get(Type.class, id));
        session.getTransaction().commit();
        session.close();
    }
}
