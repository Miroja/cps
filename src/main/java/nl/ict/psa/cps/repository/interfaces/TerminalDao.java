package nl.ict.psa.cps.repository.interfaces;

import nl.ict.psa.cps.entities.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminalDao extends JpaRepository<Terminal, Integer> {

    Terminal findOneByName(String name);
}
