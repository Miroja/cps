package nl.ict.psa.cps.services.interfaces;

import nl.ict.psa.cps.entities.TransportContainer;

import java.util.List;

public interface TransportContainerService {

    TransportContainer findTransportContainerById(int id);

    TransportContainer findTransportContainerByName(String name);

    List<TransportContainer> findAllTransportContainers();

    void saveTransportContainer(TransportContainer carrier);

    void deleteTransportContainer(TransportContainer carrier);

    void deleteAllTransportContainers();

}
