package nl.ict.psa.cps.repository.interfaces;

import nl.ict.psa.cps.entities.TransportContainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportContainerDao extends JpaRepository<TransportContainer,Integer> {
}
