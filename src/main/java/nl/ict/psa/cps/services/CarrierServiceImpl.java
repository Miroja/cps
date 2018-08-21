package nl.ict.psa.cps.services;

import nl.ict.psa.cps.entities.Carrier;
import nl.ict.psa.cps.repository.interfaces.CarrierDao;
import nl.ict.psa.cps.services.interfaces.CarrierService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrierServiceImpl implements CarrierService {

    private final CarrierDao carrierDao;

    public CarrierServiceImpl(CarrierDao carrierDao) {
        this.carrierDao = carrierDao;
    }

    @Override
    public Carrier findCarrierById(int id) {
        return carrierDao.findOne(id);
    }

    @Override
    public Carrier findCarrierByTransporterCode(String transportCode) {
        return carrierDao.findOneByTransporterCode(transportCode);
    }

    @Override
    public List<Carrier> findAllCarriers() {
        return carrierDao.findAll();
    }

    @Override
    public void saveCarrier(Carrier carrier) {
        carrierDao.save(carrier);
    }

    @Override
    public void deleteCarrier(Carrier carrier) {
        carrierDao.delete(carrier);
    }

    @Override
    public void deleteAllCarriers() {
        carrierDao.deleteAll();
    }
}
