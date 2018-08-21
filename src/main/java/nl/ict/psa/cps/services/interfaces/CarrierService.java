package nl.ict.psa.cps.services.interfaces;

import nl.ict.psa.cps.entities.Carrier;

import java.util.List;

public interface CarrierService {

    Carrier findCarrierById(int id);

    Carrier findCarrierByTransporterCode(String transportCode);

    List<Carrier> findAllCarriers();

    void saveCarrier(Carrier carrier);

    void deleteCarrier(Carrier carrier);

    void deleteAllCarriers();
}
