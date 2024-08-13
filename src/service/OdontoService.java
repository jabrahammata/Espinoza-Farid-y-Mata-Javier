package service;

import dao.iDao;
import model.odontologo;

import java.util.List;

public class OdontoService {
    private iDao<odontologo> odontologoIDao;

    public OdontoService(iDao<odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public odontologo saveOdontologo(odontologo odontologo){
        return odontologoIDao.save(odontologo);
    }

    public odontologo getOdontologo(int id){
        return odontologoIDao.get(id);
    }

    public void deleteOdontologo(int id){
        odontologoIDao.delete(id);
    }

    public void updateOdontologo(odontologo odontologo, int id){
        odontologoIDao.update(odontologo, id);
    }

    public List<odontologo> getAllOdontologo(){
        return odontologoIDao.getAll();
    }
}
