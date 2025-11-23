package org.basedatos.dao;

import org.basedatos.model.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CompanyDAO {

    // Metodo para crear la tabla COMPANIES
    public void createTable(Connection connection) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS COMPANIES (" +
                "CIF VARCHAR(9) PRIMARY KEY," +
                "NOMBRE VARCHAR(100) NOT NULL," +
                "SECTOR VARCHAR(50) NOT NULL" +
                ")";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabla COMPANIES creada correctamente.");
        }
    }

    // Metodo para insertar una lista de compañías en batch y con transacción
    public void insertCompanies(Connection connection, List<Company> companies) throws SQLException {
        String sql = "INSERT INTO COMPANIES (CIF, NOMBRE, SECTOR) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false); // Desactivamos autocommit para controlar la transacción

            for (Company company : companies) {
                pstmt.setString(1, company.getCif());
                pstmt.setString(2, company.getNombre());
                pstmt.setString(3, company.getSector());
                pstmt.addBatch();
            }

            try {
                pstmt.executeBatch();
                connection.commit();
                System.out.println("Compañías insertadas correctamente.");
            } catch (SQLException e) {
                connection.rollback();
                System.err.println("Error al insertar compañías, rollback ejecutado.");
                throw e;
            } finally {
                connection.setAutoCommit(true); // Volvemos a activar autocommit
            }
        }
    }
}
