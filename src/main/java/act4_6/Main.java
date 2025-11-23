package act4_6;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        try (Connection conexion = DriverManager.getConnection(url, user, password)) {

            //Crear tabla
            CompanyTabla.crearTablaCompanies(conexion);

            //Crear lista de compañías
            List<Company> lista = Arrays.asList(
                    new Company("12345678A", "TechNova", "Tecnología"),
                    new Company("23456789B", "AgroPlus", "Agricultura"),
                    new Company("34567890C", "Financorp", "Finanzas")
            );

            //Insertar lista en batch
            CompanyTabla.insertarCompanies(conexion, lista);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
