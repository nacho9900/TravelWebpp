package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.model.Trip;

import java.util.Calendar;
import java.util.Optional;

public interface TripDao {

    public Trip create(long placeid, String name, String description, Calendar startDate, Calendar endDate);
    public Optional<Trip> findById(long id);


}
