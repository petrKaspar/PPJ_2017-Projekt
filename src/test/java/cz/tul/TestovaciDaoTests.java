package cz.tul;

import cz.tul.data.Testovaci;
import cz.tul.data.TestovaciDao;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Petr on 09.04.2017.
 * Urceno pro me vlastni pokusy a zkouseni, jak co funguje. Ve finalni verzi semestralky nebude.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
//@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestovaciDaoTests {

    @Autowired
    TestovaciDao testovaciDao;// = new TestovaciDao();

    @Test
    public void testTestovaci() {

//        testovaciDao.deleteTestovaci(7);

       Testovaci testovaci = new Testovaci("ahoj Test", 100, "Petr333");

//        testovaciDao.create(testovaci);
       assertTrue("Offer creation should return true", testovaciDao.create(testovaci));
    }

}