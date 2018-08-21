package nl.ict.psa.cps.services;

import nl.ict.psa.cps.entities.Terminal;
import nl.ict.psa.cps.repository.interfaces.TerminalDao;
import nl.ict.psa.cps.services.interfaces.TerminalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerminalServiceImpl implements TerminalService {


    private final TerminalDao terminalDao;


    public TerminalServiceImpl(TerminalDao terminalDao) {
        this.terminalDao = terminalDao;
    }

    @Override
    public Terminal findTerminalById(int id) {
        return terminalDao.findOne(id);
    }

    @Override
    public Terminal findTerminalByName(String name) {
        return terminalDao.findOneByName(name);
    }

    @Override
    public List<Terminal> findAllTerminals() {
        return terminalDao.findAll();
    }

    @Override
    public void saveTerminal(Terminal terminal) {
        terminalDao.save(terminal);
    }

    @Override
    public void deleteTerminal(Terminal terminal) {
        terminalDao.delete(terminal);
    }

    @Override
    public void deleteAllTerminals() {
        terminalDao.deleteAll();
    }
}
