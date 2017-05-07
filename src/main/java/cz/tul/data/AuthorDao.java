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
public class AuthorDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @Transactional
    public int create(Author author) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("name", author.getname());
        params.addValue("registration", author.getRegistration());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        boolean b = jdbc.update("insert into author (name, registration) values (:name, :registration)",
                params, keyHolder) == 1;

        System.out.println("b = " + b);
        System.out.println("keyHolder.getKey() = " + keyHolder.getKey());

        if (b == false) return 0;
        return keyHolder.getKey().intValue();

//        return jdbc.update("insert into author (name, registration) values (:name, :registration)", params) == 1;
    }

    public List<Author> getAllAuthors() {
        return jdbc.query("select * from author", BeanPropertyRowMapper.newInstance(Author.class));
    }

    public Author getAuthor(int id){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select * from author WHERE authorId=:id", params,
                new RowMapper<Author>() {

                    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Author author = new Author();
                        author.setAuthorId(rs.getInt("authorId"));
                        author.setname(rs.getString("name"));
                        author.setRegistration(rs.getDate("registration"));

                        return author;
                    }

                });

    }

    public void deleteAuthor(int id) {
        jdbc.getJdbcOperations().execute("DELETE FROM author where authorId="+id);
    }

}
