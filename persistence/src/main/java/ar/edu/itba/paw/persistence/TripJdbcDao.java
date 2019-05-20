package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.TripDao;
import ar.edu.itba.paw.model.DateManipulation;
import ar.edu.itba.paw.model.Trip;
import ar.edu.itba.paw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class TripJdbcDao implements TripDao {

    private final SimpleJdbcInsert jdbcInsert;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TripJdbcDao(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("trips")
                .usingGeneratedKeyColumns("id");

    }

    private final static RowMapper<Trip> ROW_MAPPER = (rs, rowNum) -> new Trip(rs.getLong("id"), rs.getLong("placeid"), rs.getString("tripname"),
            rs.getString("description"),
            DateManipulation.dateToCalendar(rs.getDate("startDate")), DateManipulation.dateToCalendar(rs.getDate("endDate")));

    @Override
    public Trip create(long placeid, String tripname, String description, Calendar startDate, Calendar endDate) {
        final Map<String, Object> args = new HashMap<>();
        args.put("placeid", placeid);
        args.put("tripname", tripname);
        args.put("description", description);
        args.put("startDate", startDate.getTime());
        args.put("endDate", endDate.getTime());

        final Number tripId = jdbcInsert.executeAndReturnKey(args);
        return new Trip(tripId.longValue(), placeid, tripname, description, startDate, startDate);
    }

    @Override
    public Optional<Trip> findById(long id) {
        return jdbcTemplate.query("SELECT * FROM trips WHERE id = ?", ROW_MAPPER, id).stream().findFirst();
    }
}