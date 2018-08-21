package nl.ict.psa.cps.services.interfaces;

import nl.ict.psa.cps.entities.HeatMap;

import java.util.Date;
import java.util.List;

public interface HeatMapService {

    HeatMap findHeatMapById(int id);

    HeatMap findHeatMapByName(String name);

    List<HeatMap> findAllHeatMaps();

    void saveHeatMap(HeatMap heatMap);

    void deleteHeatMap(HeatMap heatMap);

    void deleteAllHeatMaps();

    void filterHeatMap(Date start, Date end);

}
