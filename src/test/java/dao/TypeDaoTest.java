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

public class TypeDaoTest {

    public static TypeDao typeDao;

    @BeforeAll
    public static void setUp() {
        typeDao = new TypeDaoImpl();
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
        type.setMovieType(movieTypes);


        typeDao.save(type);

        Type typeSave = typeDao.getById(type.getId());
        System.out.println("typeSave" + typeSave);
        assertNotNull(typeSave);

    }

    @Test
    public void testSaveNull() {
        Type type = new Type();


        // Perform assertions'
        assertThrows(Exception.class, () -> {
            typeDao.save(type);
        });
    }

    @Test
    public void testGetById() {
        // Retrieve the saved Type entity by ID
//        Type savedType = typeDao.findAll().getFirst();
        Type savedType = typeDao.getById(1);
        // Perform assertions
        assertNotNull(savedType);
        assertEquals("Type Name", savedType.getTypeName());
        assertEquals("Type Description", savedType.getTypeDescription());

    }

    @Test
    public void testGetByIdNotExist() {
        // Retrieve the saved Type entity by ID
        Type savedType = typeDao.getById(1000000000);

        // Perform assertions
        assertNull(savedType);

    }

    @Test
    public void testGetAll() {
        // Retrieve the saved Type entity by ID
        List<Type> types = typeDao.findAll();
        // Perform assertions
        assertNotNull(types);
        assertEquals(1, types.size());
    }

    @Test
    public void testUpdate() {
//        Type savedType = typeDao.findAll().getFirst();
        Type savedType = typeDao.getById(1);
        savedType.setTypeDescription("Type Description Updated");

        typeDao.update(savedType);
        assertEquals("Type Description Updated", typeDao.getById(savedType.getId()).getTypeDescription());
    }

    @Test
    public void testUpdateNotExist() {

        Type savedType = typeDao.getById(1000000000);


        // Perform assertions
        assertThrows(Exception.class, () -> {
            savedType.setTypeDescription("Type Description Updated");
            typeDao.update(savedType);
        });
    }


    @Test
    public void testRemoveById() {

//        Type savedType = typeDao.findAll().getFirst();
        Type savedType = typeDao.getById(1);
        typeDao.removeById(savedType.getId());
        assertNull(typeDao.getById(savedType.getId()));
    }

    @Test
    public void testRemoveByIdNotExist() {

        Type savedType = typeDao.getById(1000000000);

        // Perform assertions
        assertThrows(Exception.class, () -> {
            typeDao.removeById(savedType.getId());
        });

    }

}
