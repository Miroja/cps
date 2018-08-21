package nl.ict.psa.cps.repository.interfaces;

import nl.ict.psa.cps.entities.HeatMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeatMapDao extends JpaRepository<HeatMap,Integer> {

    HeatMap findOneByName(String name);

}
