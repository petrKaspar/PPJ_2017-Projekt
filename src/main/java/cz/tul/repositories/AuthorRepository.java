package cz.tul.repositories;

import cz.tul.data.Author;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
/**
 * Created by Petr on 27.04.2017.
 */
//@RepositoryRestResource(collectionResourceRel = "authors", path = "authors")
@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer>{

}
