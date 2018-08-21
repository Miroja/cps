package nl.ict.psa.cps.services.interfaces;

import nl.ict.psa.cps.entities.Terminal;

import java.util.List;

public interface TerminalService {

    Terminal findTerminalById(int id);

    Terminal findTerminalByName(String name);

    List<Terminal> findAllTerminals();

     void saveTerminal(Terminal terminal);

     void deleteTerminal(Terminal terminal);

    void deleteAllTerminals();
}
