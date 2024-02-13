package fa.training.dao;

import fa.training.entities.Type;

import java.util.List;

public interface TypeDao {

    void save(Type movie);

    List<Type> findAll();

    Type getById(Integer id);

    void update(Type movie);

    void removeById(Integer id);
}
