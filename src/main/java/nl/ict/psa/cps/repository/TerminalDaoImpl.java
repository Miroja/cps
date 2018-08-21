package nl.ict.psa.cps.repository;

import nl.ict.psa.cps.entities.Terminal;
import nl.ict.psa.cps.repository.interfaces.TerminalDao;

import java.util.List;

public abstract class TerminalDaoImpl implements TerminalDao {

    @Override
    public Terminal findOneByName(String name) {

        List<Terminal> terminalList = this.findAll();

        for(Terminal terminal: terminalList){
            if(terminal.getName().equals(name)){
                return terminal;
            }
        }

        return null;
    }
}
