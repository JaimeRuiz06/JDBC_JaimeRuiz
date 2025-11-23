package org.basedatos.exercises.act4_6;

import org.basedatos.dao.CompanyDAO;
import org.basedatos.model.Company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Ejercicio4_6 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/hr_database";
        String user = "root";
        String password = "SQL_20060810";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            CompanyDAO dao = new CompanyDAO();

            // Crear tabla
            dao.createTable(connection);

            // Crear lista de compañías
            List<Company> companies = Arrays.asList(
                    new Company("12345678A", "TechNova", "Tecnología"),
                    new Company("23456789B", "AgroPlus", "Agricultura"),
                    new Company("34567890C", "Financorp", "Finanzas")
            );

            // Insertar en batch
            dao.insertCompanies(connection, companies);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

