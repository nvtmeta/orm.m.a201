package dao;

import fa.training.dao.MovieDao;
import fa.training.dao.TypeDao;
import fa.training.dao.impl.MovieDaoImpl;
import fa.training.dao.impl.TypeDaoImpl;
import fa.training.entities.Movie;
import fa.training.entities.MovieType;
import fa.training.entities.MovieTypeId;
import fa.training.entities.Type;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class MovieDaoTest {

    public static MovieDao movieDao;


    @BeforeAll
    public static void setUp() {
        movieDao = new MovieDaoImpl();
    }


    @Test
    public void testSave() {

        Type type = new Type();
        type.setTypeName("Type Name");
        type.setTypeDescription("Type Description");

        // Create a new Movie object
        Movie movie = new Movie();
        movie.setActor("Actor ");
        movie.setContent("Movie Description");
        movie.setDuration(BigDecimal.valueOf(1.0));
        movie.setDirector("Director");
        movie.setFromDate(java.time.LocalDate.now());
        movie.setToDate(java.time.LocalDate.now());
        movie.setMovieProductionCompany("Movie Production Company");
        movie.setVersion("Version");
        movie.setMovieNameEng("Movie Name");
        movie.setMovieNameVn("Movie Name");
        movie.setLargeImage("Large Image");
        movie.setSmallImage("Small Image");


        // Create a new MovieTypeId object
        MovieTypeId movieTypeId = new MovieTypeId();
        movieTypeId.setMovieId(movie.getId());
        movieTypeId.setTypeId(type.getId());
        // Create a new MovieType object

        MovieType movieType = new MovieType();
        movieType.setId(movieTypeId);
        movieType.setMtDescription("Movie Type Description");
        movieType.setMovie(movie);
        movieType.setType(type);


        Set<MovieType> movieTypes = new HashSet<>();
        movieTypes.add(movieType);

        movie.setMovieType(movieTypes);

        movieDao.save(movie);

        Movie movieSave = movieDao.getById(movie.getId());
        System.out.println("movieSave" + movieSave);
        assertNotNull(movieSave);
    }

    @Test
    public void testSaveNotExist() {
        Movie movie = new Movie();

        assertThrows(Exception.class, () -> movieDao.save(movie));
    }

    @Test
    public void testFindAll() {
        List<Movie> movies = movieDao.findAll();

        assertNotNull(movies);
        assertEquals(1, movies.size());
    }

    @Test
    public void testGetById() {
//        Movie movie = movieDao.findAll().getFirst();
        Movie movie = movieDao.getById(1);
        assertNotNull(movie);
    }

    @Test
    public void testGetByIdNotExist() {
        Movie movie = movieDao.getById(1000000000);
        assertNull(movie);
    }

    @Test
    public void testUpdate() {
        Movie movie = movieDao.findAll().getFirst();

        movie.setActor("Actor Updated");
        movieDao.update(movie);

        System.out.println("movieDao.getById" + movieDao.getById(14));

        assertEquals("Actor Updated", movieDao.getById(movie.getId()).getActor());

    }

    @Test
    public void testUpdateNotExist() {
        Movie movie = movieDao.getById(1000000000);

        assertThrows(Exception.class, () -> {
            movie.setActor("Actor Updated");
            movieDao.update(movie);
        });
    }

    @Test
    public void testRemoveById() {
//        Movie movie = movieDao.findAll().getFirst();
        Movie movie = movieDao.getById(1);

        movieDao.removeById(movie.getId());
        assertNull(movieDao.getById(movie.getId()));
    }

    @Test
    public void testRemoveByIdNotExist() {
        Movie movie = movieDao.getById(1000000000);
        assertThrows(Exception.class, () -> movieDao.removeById(movie.getId()));
    }
}
