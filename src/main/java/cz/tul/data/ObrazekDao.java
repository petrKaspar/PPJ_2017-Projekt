package cz.tul.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by Petr on 09.04.2017.
 */
public class ObrazekDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    private OffsetDateTime odt = OffsetDateTime.now();;
    public int create(Obrazek obrazek) throws SQLException {

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
                obrazek);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        boolean b = jdbc.update("insert into obrazek (autor_id, url, nazev, vytvoreni) " +
                        "values (:autor_id, :url, :nazev, :vytvoreni)",
                params, keyHolder, new String[]{"ID"}) == 1;

        System.out.println("b = " + b);
        System.out.println("keyHolder.getKey() = " + keyHolder.getKey());
        
        if (b == false) return 0;
        return keyHolder.getKey().intValue();

//        System.out.printf("insert into obrazek (autor_id, url, nazev, vytvoreni) values (:autor_id, :url, :nazev, :vytvoreni)",params);
//        return jdbc
//                .update("insert into obrazek (autor_id, url, nazev, vytvoreni) values (:autor_id, :url, :nazev, :vytvoreni)",
//                        params) == 1;
    }

    public boolean incrementNLike(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("aktualizace", odt.toEpochSecond()+"");

        System.out.printf("update obrazek set nlike = nlike + 1 where obrazek_id=:id", params);
        return jdbc.update("update obrazek set nlike = nlike + 1, " +
                "aktualizace = :aktualizace where obrazek_id=:id", params) == 1;
    }

    public boolean incrementNDislike(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("aktualizace", odt.toEpochSecond()+"");

        System.out.printf("update obrazek set ndislike = ndislike + 1aktualizace = :aktualizace where obrazek_id=:id", params);
        return jdbc.update("update obrazek set ndislike = ndislike + 1, " +
                "aktualizace = :aktualizace where obrazek_id=:id", params) == 1;
    }

    public boolean setAktualizace(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("aktualizace", odt.toEpochSecond()+"");

        System.out.printf("update obrazek set nlike = nlike + 1 where obrazek_id=:id", params);
        return jdbc.update("update obrazek set aktualizace = :aktualizace where obrazek_id=:id", params) == 1;
    }

    public List<Obrazek> getObrazky_innerjoin() {

        return jdbc
                .query("select * from obrazek join autor using (autor_id)",
                        (ResultSet rs, int rowNum) -> {
                            Autor autor = new Autor();
                            autor.setAutor_id(rs.getInt("autor_id"));
                            autor.setJmeno(rs.getString("jmeno"));
                            autor.setRegistrace(rs.getString("registrace"));

                            Obrazek obrazek = new Obrazek();
                            obrazek.setObrazek_id(rs.getInt("obrazek_id"));
                            obrazek.setAutor_id(rs.getInt("autor_id"));
                            obrazek.setUrl(rs.getString("url"));
                            obrazek.setNazev(rs.getString("nazev"));
                            obrazek.setVytvoreni(rs.getString("vytvoreni"));
                            obrazek.setVytvoreni(rs.getString("vytvoreni"));
                            obrazek.setAktualizace(rs.getString("aktualizace"));
                            obrazek.setNlike(rs.getInt("nlike"));
                            obrazek.setNdislike(rs.getInt("ndislike"));
                            obrazek.setAutor(autor);

                            return obrazek;
                        }
                );
    }


    public Obrazek getObrazek(int id) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select * from obrazek, autor where obrazek.autor_id=autor.autor_id and obrazek_id=:id", params,
                new RowMapper<Obrazek>() {

                    public Obrazek mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Autor autor = new Autor();
                        autor.setAutor_id(rs.getInt("autor_id"));
                        autor.setJmeno(rs.getString("jmeno"));
                        autor.setRegistrace(rs.getString("registrace"));

                        Obrazek obrazek = new Obrazek();
                        obrazek.setObrazek_id(rs.getInt("obrazek_id"));
                        obrazek.setAutor_id(rs.getInt("autor_id"));
                        obrazek.setUrl(rs.getString("url"));
                        obrazek.setNazev(rs.getString("nazev"));
                        obrazek.setVytvoreni(rs.getString("vytvoreni"));
                        obrazek.setVytvoreni(rs.getString("vytvoreni"));
                        obrazek.setAktualizace(rs.getString("aktualizace"));
                        obrazek.setNlike(rs.getInt("nlike"));
                        obrazek.setNdislike(rs.getInt("ndislike"));
                        obrazek.setAutor(autor);

                        return obrazek;
                    }

                });
    }

}
