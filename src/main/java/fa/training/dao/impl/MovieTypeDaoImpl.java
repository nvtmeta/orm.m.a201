package fa.training.dao.impl;

import fa.training.dao.MovieTypeDao;
import fa.training.entities.MovieType;
import fa.training.entities.MovieTypeId;
import fa.training.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.List;

public class MovieTypeDaoImpl implements MovieTypeDao {
    @Override
    public void save(MovieType movie) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(movie);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<MovieType> findAll() {
        Session session = HibernateUtils.getCurrentSession();
        session.beginTransaction();
        List<MovieType> movieTypes = session.createQuery("SELECT a FROM movie_type a", MovieType.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return movieTypes;
    }

    @Override
    public MovieType getById(Integer movieId, Integer typeId) {
        Session session = HibernateUtils.getCurrentSession();
        session.beginTransaction();
        MovieTypeId movieTypeId = new MovieTypeId(movieId, typeId);
        MovieType movieType = session.get(MovieType.class, movieTypeId);
        session.getTransaction().commit();
        session.close();
        return movieType;
    }

    @Override
    public void update(MovieType movie) {
        Session session = HibernateUtils.getCurrentSession();
        session.beginTransaction();
        session.merge(movie);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeById(Integer movieId, Integer typeId) {
        Session session = HibernateUtils.getCurrentSession();
        session.beginTransaction();
        MovieTypeId movieTypeId = new MovieTypeId(movieId, typeId);
        session.remove(session.get(MovieType.class, movieTypeId));
        session.getTransaction().commit();
        session.close();
    }
}
