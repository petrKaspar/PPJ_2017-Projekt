package cz.tul.repositories;

import cz.tul.data.Comment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Petr on 27.04.2017.
 */
@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {

    @Modifying
    @Transactional
    @Query("update Comment set nlike = nlike + 1, lastUpdate=:lastUpdate where commentId=:commentId")
    int incrementNLike(@Param("commentId") int commentId,
                       @Param("lastUpdate") LocalDateTime lastUpdate);

    @Modifying
    @Transactional
    @Query("update Comment set ndislike = ndislike + 1, lastUpdate=:lastUpdate where commentId=:commentId")
    int incrementNDisLike(@Param("commentId") int commentId,
                          @Param("lastUpdate") LocalDateTime lastUpdate);

}
