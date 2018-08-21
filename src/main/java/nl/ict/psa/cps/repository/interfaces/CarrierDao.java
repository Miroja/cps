package nl.ict.psa.cps.repository.interfaces;

import nl.ict.psa.cps.entities.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface to use the carrier model in the H2 database
 */
@Repository
public interface CarrierDao extends JpaRepository<Carrier, Integer> {

    Carrier findOneByTransporterCode(String transporterCode);
}


