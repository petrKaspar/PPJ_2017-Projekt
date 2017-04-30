package cz.tul.service;

import cz.tul.data.Autor;
import cz.tul.repositories.AutorRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by Petr on 09.04.2017.
 */
@Service
@Transactional
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public int create(Autor autor){
        Autor newAutor = autorRepository.save(autor);
        return newAutor.getAutor_id();
    }

    public List<Autor> getAllAutors() {
        return StreamSupport.stream(autorRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
//
    public Autor getAutor(int id) {
        return autorRepository.findOne(id);
    }
////////------------------------------------------------------
    /*
    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @Transactional
    public int create(Autor autor) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("name", autor.getname());
        params.addValue("registration", autor.getRegistration());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        boolean b = jdbc.update("insert into autor (name, registration) values (:name, :registration)",
                params, keyHolder, new String[]{"ID"}) == 1;

        System.out.println("b = " + b);
        System.out.println("keyHolder.getKey() = " + keyHolder.getKey());

        if (b == false) return 0;
        return keyHolder.getKey().intValue();

//        return jdbc.update("insert into autor (name, registration) values (:name, :registration)", params) == 1;
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
                        autor.setname(rs.getString("name"));
                        autor.setRegistration(rs.getString("registration"));

                        return autor;
                    }

                });

    }

    public void deleteAutor(int id) {
        jdbc.getJdbcOperations().execute("DELETE FROM autor where autor_id="+id);
    }
*/
}
