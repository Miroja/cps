package nl.ict.psa.cps.services;

import nl.ict.psa.cps.entities.TransportContainer;
import nl.ict.psa.cps.repository.interfaces.TransportContainerDao;
import nl.ict.psa.cps.services.interfaces.TransportContainerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportContainerServiceImpl implements TransportContainerService {

    private final TransportContainerDao transportContainerDao;

    public TransportContainerServiceImpl(TransportContainerDao transportContainerDao) {
        this.transportContainerDao = transportContainerDao;
    }

    @Override
    public TransportContainer findTransportContainerById(int id) {
        return transportContainerDao.findOne(id);
    }

    @Override
    public TransportContainer findTransportContainerByName(String name) {
        List<TransportContainer> transportContainers = transportContainerDao.findAll();

        for(TransportContainer transportContainer: transportContainers){
            if(transportContainer.getContainerName().equals(name)){
                return transportContainer;
            }
        }
        return null;
    }

    @Override
    public List<TransportContainer> findAllTransportContainers() {
        return transportContainerDao.findAll();
    }

    @Override
    public void saveTransportContainer(TransportContainer container) {
        transportContainerDao.save(container);
    }

    @Override
    public void deleteTransportContainer(TransportContainer container) {
        transportContainerDao.save(container);
    }

    @Override
    public void deleteAllTransportContainers() {
        transportContainerDao.deleteAll();
    }
}
