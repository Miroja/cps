package nl.ict.psa.cps.utils;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeHelper {

    private static final Logger LOGGER = Logger.getLogger(DateTimeHelper.class);

    public DateTimeHelper() {
    }

    public Date ConvertToComparableDate(Date dateTime) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            return dateFormat.parse(dateTime.toString());

        } catch (Exception e) {
            LOGGER.info("Could not execute ConvertToComparableDate" + dateTime, e);
            return null;
        }
    }
}
