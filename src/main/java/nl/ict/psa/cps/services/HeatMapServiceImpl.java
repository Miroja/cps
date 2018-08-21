package nl.ict.psa.cps.services;

import nl.ict.psa.cps.entities.CarrierRoutePoint;
import nl.ict.psa.cps.entities.HeatMap;
import nl.ict.psa.cps.repository.interfaces.HeatMapDao;
import nl.ict.psa.cps.services.interfaces.HeatMapService;
import nl.ict.psa.cps.utils.DateTimeHelper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Service
public class HeatMapServiceImpl implements HeatMapService {

    private static final Logger LOGGER = Logger.getLogger(HeatMapServiceImpl.class);
    private final HeatMapDao heatMapDao;


    public HeatMapServiceImpl(HeatMapDao heatMapDao) {
        this.heatMapDao = heatMapDao;
    }

    @Override
    public HeatMap findHeatMapById(int id) {
        return heatMapDao.findOne(id);
    }

    @Override
    public HeatMap findHeatMapByName(String name) {
        return heatMapDao.findOneByName(name);
    }

    @Override
    public List<HeatMap> findAllHeatMaps() {
        return heatMapDao.findAll();
    }

    @Override
    public void saveHeatMap(HeatMap heatMap) {
        heatMapDao.save(heatMap);
    }

    @Override
    public void deleteHeatMap(HeatMap heatMap) {
        heatMapDao.delete(heatMap);
    }

    @Override
    public void deleteAllHeatMaps() {
        heatMapDao.deleteAll();
    }

    @Override
    public void filterHeatMap(Date start, Date end) {

        HeatMap heatMap = heatMapDao.findOneByName("heatmap");
        List<CarrierRoutePoint> heatMapRoutePointsList = heatMap.getHeatMapRoutePoints();

        HeatMap filteredHeatMap = heatMapDao.findOneByName("filteredHeatMap");
        List<CarrierRoutePoint> newHeatMapRoutePointsList = new ArrayList<>();

        for(CarrierRoutePoint routePoint: heatMapRoutePointsList){
            Date routePointDate = routePoint.getDateTime();

            DateTimeHelper dateTimeHelper = new DateTimeHelper();
            Date convertedDate = dateTimeHelper.ConvertToComparableDate(routePointDate);

            LOGGER.info(" DATEofPOINT: " + routePointDate + " start: " + start + " end: " +  end + " convertedDate " + convertedDate);
            if((convertedDate.compareTo(start) >= 0) && (convertedDate.compareTo(end) <=0)){
                LOGGER.info(" DATEofPOINT inserted: " + routePointDate + convertedDate);

                CarrierRoutePoint newRoutePoint = new CarrierRoutePoint();
                newRoutePoint.setLongitude(routePoint.getLongitude());
                newRoutePoint.setLatitude(routePoint.getLatitude());
                newRoutePoint.setDateTime(routePointDate);

                newHeatMapRoutePointsList.add(newRoutePoint);
            }
        }

        filteredHeatMap.setHeatMapRoutePoints(newHeatMapRoutePointsList);
        heatMapDao.save(filteredHeatMap);
    }
}
