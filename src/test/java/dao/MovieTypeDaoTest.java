package dao;

import fa.training.dao.MovieTypeDao;
import fa.training.dao.impl.MovieTypeDaoImpl;
import fa.training.entities.Movie;
import fa.training.entities.MovieType;
import fa.training.entities.MovieTypeId;
import fa.training.entities.Type;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovieTypeDaoTest {

    public static MovieTypeDao movieTypeDao;


    @BeforeAll
    public static void setUp() {
        movieTypeDao = new MovieTypeDaoImpl();
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

        movieTypeDao.save(movieType);

        MovieType movieTypeSaved = movieTypeDao.getById(movie.getId(), type.getId());
        assertNotNull(movieTypeSaved);
    }

    @Test
    public void testSaveNotExist() {
        MovieType movieType = new MovieType();
        movieType.setMtDescription("Movie Type Description");

        assertThrows(Exception.class, () -> movieTypeDao.save(movieType));

    }

    @Test
    public void testFindAll() {
        List<MovieType> movieTypes = movieTypeDao.findAll();

        assertEquals(1, movieTypes.size());
    }

    @Test
    public void testGetById() {
        MovieType movieType = movieTypeDao.getById(1, 1);
        assertNotNull(movieType);
        assertEquals("Movie Type Description", movieType.getMtDescription());
    }

    @Test
    public void testGetByIdNotExist() {
        MovieType movieType = movieTypeDao.getById(1000000000, 10000000);
        assertNull(movieType);
    }

    @Test
    public void testUpdate() {

        MovieType movieType = movieTypeDao.getById(1, 1);
        movieType.setMtDescription("Movie Type Description Updated");
        movieTypeDao.update(movieType);
        MovieType movieTypeUpdated = movieTypeDao.getById(1, 1);
        assertEquals("Movie Type Description Updated", movieTypeUpdated.getMtDescription());
    }

    @Test
    public void testUpdateNotExist() {
        MovieType movieType = movieTypeDao.getById(1000000000, 10000000);

        assertThrows(Exception.class, () -> {
            movieType.setMtDescription("Movie Type Description Updated");
            movieTypeDao.update(movieType);
        });
    }

    @Test
    public void testRemoveById() {

        movieTypeDao.removeById(1, 1);
        MovieType movieType = movieTypeDao.getById(1, 1);
        assertNull(movieType);

    }
    @Test
    public void testRemoveByIdNotExist() {
        assertThrows(Exception.class, () -> {
            movieTypeDao.removeById(1000000000, 10000000);
        });
    }


}
