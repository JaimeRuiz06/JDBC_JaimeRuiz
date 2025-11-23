package act4_6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CompanyTabla {

    //Metodo que crea la tabla COMPANIES
    public static void crearTablaCompanies(Connection conexion) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS COMPANIES (
                    CIF CHAR(9) PRIMARY KEY,
                    NOMBRE VARCHAR(100) NOT NULL,
                    SECTOR VARCHAR(100) NOT NULL
                )
                """;

        try (Statement stmt = conexion.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabla COMPANIES creada correctamente (si no existía).");
        }
    }

    //Metodo que inserta una lista de compañías en modo batch y con control de transacción
    public static void insertarCompanies(Connection conexion, List<Company> lista) throws SQLException {
        String sql = "INSERT INTO COMPANIES (CIF, NOMBRE, SECTOR) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            conexion.setAutoCommit(false); // Desactivar autocommit

            for (Company c : lista) {
                ps.setString(1, c.getCif());
                ps.setString(2, c.getNombre());
                ps.setString(3, c.getSector());
                ps.addBatch(); // Añade al batch
            }

            ps.executeBatch(); // Ejecuta el batch
            conexion.commit(); // Confirma transacción
            System.out.println("Transacción completada correctamente.");

        } catch (SQLException e) {
            conexion.rollback(); // Revierte la transacción si hay error
            System.err.println("Error en la transacción. Se ha hecho rollback.");
            throw e;
        } finally {
            conexion.setAutoCommit(true); // Restaura el modo autocommit
        }
    }
}

