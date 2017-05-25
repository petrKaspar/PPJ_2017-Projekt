package cz.tul.repositories;

import cz.tul.data.Author;
import cz.tul.data.Picture;
import cz.tul.data.Tag;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by Petr on 27.04.2017.
 */

@Repository
@Transactional
public interface PictureRepository extends CrudRepository<Picture, Integer> {

    @Modifying
    @Transactional
    @Query("update Picture set nlike = nlike + 1, lastUpdate=:lastUpdate where pictureId=:pictureId")
    int incrementNLike(@Param("pictureId") int pictureId,
                       @Param("lastUpdate") LocalDateTime lastUpdate);

    @Modifying
    @Transactional
    @Query("update Picture set ndislike = ndislike + 1, lastUpdate=:lastUpdate where pictureId=:pictureId")
    int incrementNDisLike(@Param("pictureId") int pictureId,
                       @Param("lastUpdate") LocalDateTime lastUpdate);


    List<Picture> findByTitleContaining(String title);

    List<Picture> findByAuthor(Author author);

    List<Picture> findByTagSet(Tag tag);


}
