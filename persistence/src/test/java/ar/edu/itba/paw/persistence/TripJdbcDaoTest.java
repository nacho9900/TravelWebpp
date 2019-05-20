package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.model.Trip;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;


import javax.sql.DataSource;
import java.util.Calendar;
import java.util.Optional;

@Transactional
@Sql("classpath:schema2.sql")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class TripJdbcDaoTest {

    private static final long PLACEID = 1;

    private static final String NAME = "name";

    private static final String DESCRIP = "my description";

    private static final long ID = 2;


    private final Calendar STARTDATE = initiateDate();
    private final Calendar ENDDATE = initiateDate2();


    public Calendar initiateDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1997,Calendar.JUNE,16);
        return calendar;
    }

    public Calendar initiateDate2() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1997,Calendar.JUNE,28);
        return calendar;
    }

    @Autowired
    private DataSource ds;

    @Autowired
    private TripJdbcDao tripDao;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    @Test
    public void testCreate() {
        final Trip trip = tripDao.create(PLACEID, NAME, DESCRIP, STARTDATE, ENDDATE);
        Assert.assertNotNull(trip);
        //       Assert.assertEquals(ID, user.getId());
        Assert.assertEquals(PLACEID, trip.getPlaceid());
        Assert.assertEquals(NAME, trip.getName());
        JdbcTestUtils.countRowsInTableWhere(jdbcTemplate,"trips","tripname = " + "'" + NAME + "'");
    }

    @Test
    public void TestfindById() {
        Optional<Trip> trip = tripDao.findById(ID);
        Assert.assertTrue(trip.isPresent());
        Assert.assertEquals(ID, trip.get().getId());
        Assert.assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "trips"));

    }
}

