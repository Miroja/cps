package nl.ict.psa.cps.repository;

import nl.ict.psa.cps.entities.Carrier;
import nl.ict.psa.cps.repository.interfaces.CarrierDao;

import java.util.List;

public abstract class CarrierDaoImpl implements CarrierDao {
    @Override
    public Carrier findOneByTransporterCode(String transporterCode) {

        List<Carrier> carrierList = this.findAll();

        for(Carrier carrier: carrierList){
            if(carrier.getTransporterCode().equals(transporterCode)){
                return carrier;
            }
        }
        return null;
    }
}
