package dao.impl;

import dao.iDao;
import model.odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoMemoria implements iDao<odontologo> {
    private static final Logger logger = Logger.getLogger(OdontologoDAOMemoria.class);
    private final List<odontologo> odontologo = new ArrayList<>();

    @Override
    public odontologo save(odontologo odontologo) {
        odontologo.add(odontologo);
        logger.info("Odontologo guardado en memoria: " + odontologo);
        return null;
    }

    @Override
    public void update(odontologo odontologo, int id) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public odontologo get(int id) {
        return null;
    }

    @Override
    public List<odontologo> getAll() {
        logger.info("Odontologos listados desde memoria: " + odontologo.size());
        return new ArrayList<>(odontologo);
    }

}
