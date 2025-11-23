package act4_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteUtils {

    public static void imprimirRegistros2(Connection conexion) throws SQLException {
        String sql = "SELECT DNI, NOMBRE, CODIGO_POSTAL FROM CLIENTES";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.printf("%-12s %-20s %-12s %-12s%n", "DNI", "NOMBRE", "CP (String)", "CP (int)");
            System.out.println("-------------------------------------------------------------");

            while (rs.next()) {
                String dni = rs.getString("DNI");
                String nombre = rs.getString("NOMBRE");

                // Obtener como String (preserva NULL y ceros a la izquierda)
                String cpString = rs.getString("CODIGO_POSTAL");

                // Obtener como int (convierte, y devuelve 0 si SQL NULL; usar wasNull para distinguir)
                int cpInt = rs.getInt("CODIGO_POSTAL");
                boolean cpIntWasNull = rs.wasNull(); // true si la columna era SQL NULL

                String cpIntDisplay = cpIntWasNull ? "NULL" : Integer.toString(cpInt);

                System.out.printf("%-12s %-20s %-12s %-12s%n",
                        dni,
                        nombre,
                        cpString == null ? "NULL" : cpString,
                        cpIntDisplay);
            }
        }
    }
}
