package cz.tul.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

/**
 * Created by Petr on 03.04.2017.
 * Urceno pro me vlastni pokusy a zkouseni, jak co funguje. Ve finalni verzi semestralky nebude.
 */
public class TestovaciDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    public boolean create(Testovaci testovaci) {

        MapSqlParameterSource params = new MapSqlParameterSource();
//        params.addValue("title", testovaci.getTitle());
//        params.addValue("pocet", testovaci.getPocet());
//        params.addValue("name", testovaci.getname());

        params.addValue("title", "asdf");
        params.addValue("pocet", 666);
        params.addValue("name", "ssssss");


//        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(testovaci);

        return jdbc
                .update("insert into testovaci (title, pocet, name) values ('asdf', 32, 'asdfafasfd')",
                        params) == 1;
    }

    public boolean incrementPpocet(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.update("update testovaci set pocet = pocet + 1 where id_testovaci=:id", params) == 1;
    }


    public void deleteTestovaci(int id) {
//        MapSqlParameterSource params = new MapSqlParameterSource();
//        params.addValue("id", id);
        jdbc.getJdbcOperations().execute("DELETE FROM testovaci where id_testovaci="+id);
//        return jdbc.update("DELETE FROM testovaci where id_testovaci=:id", params) == 1;
    }

}
