package cz.tul.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.SQLException;
import java.time.OffsetDateTime;

/**
 * Created by Petr on 10.04.2017.
 */
public class KomentarDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    private OffsetDateTime odt = OffsetDateTime.now();
    public int create(Komentar komentar) throws SQLException {
// TODO
//        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
//                komentar);
//
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        boolean b = jdbc.update("insert into komentar (autor_id, url, nazev, vytvoreni) " +
//                        "values (:autor_id, :url, :nazev, :vytvoreni)",
//                params, keyHolder, new String[]{"ID"}) == 1;
//
//        System.out.println("b = " + b);
//        System.out.println("keyHolder.getKey() = " + keyHolder.getKey());
//
//        if (b == false) return 0;
//        return keyHolder.getKey().intValue();
        return 0;
    }



}
