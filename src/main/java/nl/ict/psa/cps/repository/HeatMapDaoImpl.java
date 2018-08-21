package nl.ict.psa.cps.repository;

import nl.ict.psa.cps.entities.HeatMap;
import nl.ict.psa.cps.repository.interfaces.HeatMapDao;

import java.util.List;

public abstract class HeatMapDaoImpl implements HeatMapDao {

    @Override
    public HeatMap findOneByName(String name) {

        List<HeatMap> heatMapList = this.findAll();

        for(HeatMap heatmap: heatMapList){
            if(heatmap.getName().equals(name)){
                return heatmap;
            }
        }

        return null;
    }
}
