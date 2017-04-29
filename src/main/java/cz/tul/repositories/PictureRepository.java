package cz.tul.repositories;

import cz.tul.data.Picture;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Petr on 27.04.2017.
 */

@Repository
public interface PictureRepository extends CrudRepository<Picture, Integer> {

    @Modifying
    @Transactional
    @Query("update Picture set nlike = nlike + 1, lastUpdate=:lastUpdate where picture_id=:picture_id")
    int incrementNLike(@Param("picture_id") int picture_id,
                       @Param("lastUpdate") String lastUpdate);

    @Modifying
    @Transactional
    @Query("update Picture set ndislike = ndislike + 1, lastUpdate=:lastUpdate where picture_id=:picture_id")
    int incrementNDisLike(@Param("picture_id") int picture_id,
                       @Param("lastUpdate") String lastUpdate);



}
