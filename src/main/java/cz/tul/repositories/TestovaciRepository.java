package cz.tul.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Petr on 27.04.2017.
 */
@Repository
public interface TestovaciRepository extends CrudRepository<TestovaciRepository, Integer> {
}
