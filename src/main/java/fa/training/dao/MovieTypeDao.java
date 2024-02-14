package fa.training.dao;

import fa.training.entities.Movie;
import fa.training.entities.MovieType;

import java.util.List;

public interface MovieTypeDao {

    void save(MovieType movie);

    List<MovieType> findAll();

    MovieType getById(Integer movieId, Integer typeId);

    void update(MovieType movie);

    void removeById(Integer movieId, Integer typeId);
}
