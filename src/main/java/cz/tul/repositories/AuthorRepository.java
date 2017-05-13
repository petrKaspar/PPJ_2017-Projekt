package cz.tul.repositories;

import cz.tul.data.Author;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
/**
 * Created by Petr on 27.04.2017.
 */
@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer>{

}