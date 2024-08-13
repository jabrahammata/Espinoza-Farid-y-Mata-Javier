package dao.impl;

import dao.iDao;
import db.h2hot;
import model.odontologo;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class odontologoDAO implements iDao<odontologo> {
    public static final String INSERT = "INSERT INTO odontologos (nombre, apellido, matricula) VALUES (?, ?, ?)";
    public static final String SELECT_ID = "SELECT * FROM odontologos WHERE id = ?";
    public static final String SELECT_ALL = "SELECT * FROM odontologos";
    public static final String UPDATE = "UPDATE odontologos SET nombre = ?, apellido = ?, matricula = ? WHERE id = ?";
    public static final String DELETE = "DELETE FROM odontologos WHERE id = ?";
    private Connection connection;
    private Logger logger = Logger.getLogger(odontologoDAO.class);

    {
        try {
            connection = h2hot.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public odontologo save(odontologo odontologo) {
        odontologo odontologoToReturn = null;
        try {
            connection.setAutoCommit(false);
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(INSERT, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, odontologo.getNombre());
            preparedStatement.setString(2, odontologo.getApellido());
            preparedStatement.setString(3, odontologo.getMatricula());
            preparedStatement.executeUpdate();
            connection.commit();
            java.sql.ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                odontologoToReturn = new odontologo(id, odontologo.getNombre(), odontologo.getApellido(), odontologo.getMatricula());
            }
            logger.info("odontologo saved");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("odontologo not saved");
            try {
                connection.rollback();
                logger.error("odontologo not saved");
            } catch (SQLException ex) {
                ex.printStackTrace();
                logger.error("odontologo not saved");
            } finally {
                try {
                    connection.setAutoCommit(true);
                    logger.error("odontologo not saved");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    logger.error("odontologo not saved");
                }
            }
        }
        return odontologoToReturn;
    }

    @Override
    public void update(odontologo odontologo, int id) {
        odontologo odontologoToReturn = null;
        try {
            connection.setAutoCommit(false);
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, odontologo.getNombre());
            preparedStatement.setString(2, odontologo.getApellido());
            preparedStatement.setString(3, odontologo.getMatricula());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
            connection.commit();
            logger.info("odontologo updated");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("odontologo not updated");
            try {
                connection.rollback();
                logger.error("odontologo not updated");
            } catch (SQLException ex) {
                ex.printStackTrace();
                logger.error("odontologo not updated");
            } finally {
                try {
                    connection.setAutoCommit(true);
                    logger.error("odontologo not updated");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    logger.error("odontologo not updated");
                }
            }
        }
    }

    @Override
    public void delete(int id) {
        try {
            connection.setAutoCommit(false);
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
            logger.info("odontologo deleted");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("odontologo not deleted");
            try {
                connection.rollback();
                logger.error("odontologo not deleted");
            } catch (SQLException ex) {
                ex.printStackTrace();
                logger.error("odontologo not deleted");
            } finally {
                try {
                    connection.setAutoCommit(true);
                    logger.error("odontologo not deleted");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    logger.error("odontologo not deleted");
                }
            }
        }

    }

    @Override
    public odontologo get(int id) {
        odontologo odontologoToReturn = null;
        try {
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID);
            preparedStatement.setInt(1, id);
            java.sql.ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                odontologoToReturn = new odontologo(resultSet.getInt("id"), resultSet.getString("nombre"), resultSet.getString("apellido"), resultSet.getString("matricula"));
            }
            logger.info("odontologo get");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("odontologo not get");
        }
        return odontologoToReturn;
    }

    @Override
    public List<odontologo> getAll() {
        List<odontologo> odontologos = new java.util.ArrayList<>();
        try {
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            java.sql.ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                odontologo odontologo = new odontologo(resultSet.getInt("id"), resultSet.getString("nombre"), resultSet.getString("apellido"), resultSet.getString("matricula"));
                odontologos.add(odontologo);
            }
            logger.info("odontologo get all");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("odontologo not get all");
        }
        return odontologos;
    }
}
