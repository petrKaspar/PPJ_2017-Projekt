package cz.tul.service;

import cz.tul.data.Testovaci;
import cz.tul.repositories.TestovaciRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Petr on 03.04.2017.
 * Urceno pro me vlastni pokusy a zkouseni, jak co funguje. Ve finalni verzi semestralky nebude.
 */
@Transactional
public class TestovaciService {

    private final TestovaciRepository testovaciRepository;

    @Autowired
    private SessionFactory sessionFactory;

    public TestovaciService(TestovaciRepository testovaciRepository) {
        this.testovaciRepository = testovaciRepository;
    }

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
        int nLike = testovaci.getPocet();
        System.out.println("+++++++++"+nLike+"++++++++++");
        testovaci.setPocet(nLike + 1);
        int updatedKey = (int) session().save(testovaci);
        System.out.println(updatedKey);
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
//        params.addValue("title", testovaci.gettitle());
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
*/

}
