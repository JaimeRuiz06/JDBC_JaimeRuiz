package act4_1;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class ActualizarClientes {
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

            String sqlDelete = "DELETE FROM CLIENTES WHERE DNI NOT IN ('78901234X', '89012345E', '56789012B')";
            try (PreparedStatement psDelete = conexion.prepareStatement(sqlDelete)) {
                int eliminados = psDelete.executeUpdate();
                System.out.println("Registros eliminados: " + eliminados);
            }

            String sqlUpdate = "UPDATE CLIENTES SET NOMBRE = ?, CODIGO_POSTAL = ? WHERE DNI = ?";
            try (PreparedStatement psUpdate = conexion.prepareStatement(sqlUpdate)) {

                psUpdate.setString(1, "NADALES");
                psUpdate.setString(2, "44126");
                psUpdate.setString(3, "78901234X");
                psUpdate.executeUpdate();

                psUpdate.setString(1, "ROJAS");
                psUpdate.setNull(2, java.sql.Types.VARCHAR);
                psUpdate.setString(3, "89012345E");
                psUpdate.executeUpdate();

                psUpdate.setString(1, "SAMPER");
                psUpdate.setString(2, "29730");
                psUpdate.setString(3, "56789012B");
                psUpdate.executeUpdate();
            }

            System.out.println("Cambios aplicados correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

