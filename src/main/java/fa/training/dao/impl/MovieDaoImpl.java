package fa.training.dao.impl;

import fa.training.dao.MovieDao;
import fa.training.entities.Movie;
import fa.training.utils.HibernateUtils;
import jakarta.persistence.Query;
import org.hibernate.Session;

import java.util.List;

public class MovieDaoImpl implements MovieDao {
    @Override
    public void save(Movie movie) {
        Session session = HibernateUtils.getCurrentSession();

        session.beginTransaction();
        session.persist(movie);
        session.getTransaction().commit();
    }

    @Override
    public List<Movie> findAll() {
        Session session = HibernateUtils.getCurrentSession();
        session.beginTransaction();
        String hql = "FROM Movie";
        Query query = session.createQuery(hql, Movie.class);
        System.out.println("query" + query);
        List<Movie> movies = query.getResultList();
        session.getTransaction().commit();
        return movies;
    }

    @Override
    public Movie getById(Integer id) {
        Session session = HibernateUtils.getCurrentSession();
        session.beginTransaction();
        Movie movie = session.get(Movie.class, id);
        session.getTransaction().commit();
        return movie;
    }

    @Override
    public void update(Movie movie) {
        Session session = HibernateUtils.getCurrentSession();
        session.beginTransaction();
        session.merge(movie);
        session.getTransaction().commit();

    }

    @Override
    public void removeById(Integer id) {
        Session session = HibernateUtils.getCurrentSession();
        session.beginTransaction();
        session.remove(session.get(Movie.class, id));
        session.getTransaction().commit();
        session.close();
    }
}
