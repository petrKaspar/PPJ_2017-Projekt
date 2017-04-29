package cz.tul.repositories;

import cz.tul.data.Testovaci;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Petr on 27.04.2017.
 */
@Repository
public interface TestovaciRepository extends CrudRepository<Testovaci, Integer> {

    @Query("SELECT t.title FROM Testovaci as t where t.id_testovaci = :id_testovaci")
    String findTitleById(@Param("id_testovaci") int id_testovaci);

}
