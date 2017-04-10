package cz.tul.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Petr on 09.04.2017.
 */
public class AutorDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @Transactional
    public int create(Autor autor) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("jmeno", autor.getJmeno());
        params.addValue("registrace", autor.getRegistrace());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        boolean b = jdbc.update("insert into autor (jmeno, registrace) values (:jmeno, :registrace)",
                params, keyHolder, new String[]{"ID"}) == 1;

        System.out.println("b = " + b);
        System.out.println("keyHolder.getKey() = " + keyHolder.getKey());

        if (b == false) return 0;
        return keyHolder.getKey().intValue();

//        return jdbc.update("insert into autor (jmeno, registrace) values (:jmeno, :registrace)", params) == 1;
    }

    public List<Autor> getAllAutors() {
        return jdbc.query("select * from autor", BeanPropertyRowMapper.newInstance(Autor.class));
    }

    public Autor getAutor(int id){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select * from autor WHERE autor_id=:id", params,
                new RowMapper<Autor>() {

                    public Autor mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Autor autor = new Autor();
                        autor.setAutor_id(rs.getInt("autor_id"));
                        autor.setJmeno(rs.getString("jmeno"));
                        autor.setRegistrace(rs.getString("registrace"));

                        return autor;
                    }

                });

    }

    public void deleteAutor(int id) {
        jdbc.getJdbcOperations().execute("DELETE FROM autor where autor_id="+id);
    }

}
