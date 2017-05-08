package cz.tul.data;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Created by Petr on 03.04.2017.
 * Urceno pro me vlastni pokusy a zkouseni, jak co funguje. Ve finalni verzi semestralky nebude.
 */
@Transactional
public class TestovaciDao {


    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    public boolean create(Testovaci testovaci) {
        System.out.println("-------------------------");
        System.out.println("New ID = "+  session().save(testovaci));
        System.out.println("-------------------------");
        return true;
    }

    public boolean incrementNLike(int id) {
        Testovaci testovaci = (Testovaci) session().load(Testovaci.class, id);
//        Criteria crit = session().createCriteria(Testovaci.class);
        int nLike = testovaci.getPocet();
        System.out.println("+++++++++"+nLike+"++++++++++");
        testovaci.setPocet(nLike + 1);
        int updatedKey = (int) session().save(testovaci);
        System.out.println(updatedKey);
        return true;
    }
//
    public boolean ldt(int id) {
        Testovaci testovaci = (Testovaci) session().load(Testovaci.class, id);
        System.out.println("+++++++++"+LocalDateTime.now()+"++++++++++");
//        testovaci.setPocet(nLike + 1);
        testovaci.setLocal_date_time(LocalDateTime.now());
//        int updatedKey = (int) session().save(testovaci);
//        System.out.println(updatedKey);
        return true;
    }


/*
    public boolean exists(String username) {
        Criteria crit = session().createCriteria(User.class);
        crit.add(Restrictions.idEq(username));
        Testovaci testovaci = (Testovaci) crit.uniqueResult();
        return testovaci != null;
    }*/



    /*
    @Autowired
    private NamedParameterJdbcOperations jdbc;

    public boolean create(Testovaci testovaci) {

        MapSqlParameterSource params = new MapSqlParameterSource();
//        params.addValue("title", testovaci.getTitle());
//        params.addValue("pocet", testovaci.getPocet());
//        params.addValue("name", testovaci.getName());

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
*/

}
