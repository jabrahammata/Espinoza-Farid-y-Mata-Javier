package test;

import dao.impl.odontologoDAO;
import model.odontologo;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.OdontoService;

import java.security.Provider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class odontoServiceTest {
    static Logger logger = Logger.getLogger(odontoServiceTest.class);
    OdontoService service = new OdontoService(new odontologoDAO());

    @BeforeAll
    static void loadTables() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./consultorio;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                logger.info("Tables loaded");
            }
        }
    }

    @Test
    void saveOdontologo() {
        odontologo one = new odontologo("Juan", "perez", "Mecanico de transformers");

        odontologo result = service.saveOdontologo(one);

        assert result.getId() != null;
    }

    @Test
    void getOdontologo() {
        odontologo one = new odontologo("Juan", "perez", "Mecanico de transformers");

        odontologo result = service.saveOdontologo(one);

        odontologo result2 = service.getOdontologo(result.getId());

        assertEquals(result.getId(), result2.getId());
    }

    @Test
    void deleteOdontologo() {
        odontologo one = new odontologo("Juan", "perez", "Mecanico de transformers");

        odontologo result = service.saveOdontologo(one);

        service.deleteOdontologo(result.getId());

        odontologo result2 = service.getOdontologo(result.getId());

        assertNull(result2);
    }

    @Test
    void updateOdontologo() {
        odontologo one = new odontologo("Juan", "perez", "Mecanico de transformers");

        odontologo result = service.saveOdontologo(one);

        odontologo result2 = new odontologo("Juan", "perez", "Mecanico de transformers");

        service.updateOdontologo(result2, result.getId());

        odontologo result3 = service.getOdontologo(result.getId());

        assertEquals(result2.getNombre(), result3.getNombre());
    }

    @Test
    void getAllOdontologo() {
        odontologo one = new odontologo("Juan", "perez", "Mecanico de transformers");

        odontologo result = service.saveOdontologo(one);

        odontologo one2 = new odontologo("Juan", "perez", "Mecanico de transformers");

        odontologo result2 = service.saveOdontologo(one2);

        assertEquals(3, service.getAllOdontologo().size());
    }
}