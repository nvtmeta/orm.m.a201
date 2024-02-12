package fa.training.dao;

import fa.training.entities.Movie;

import java.util.List;

public interface MovieDao {

    void save(Movie movie);

    List<Movie> findAll();

    Movie getById(Integer id);

    void update(Movie movie);

    void removeById(Integer id);
}
