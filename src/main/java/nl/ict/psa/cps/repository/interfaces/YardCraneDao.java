package nl.ict.psa.cps.repository.interfaces;

import nl.ict.psa.cps.entities.YardCrane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YardCraneDao extends JpaRepository<YardCrane, Integer> {

}
