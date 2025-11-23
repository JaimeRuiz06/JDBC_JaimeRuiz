package org.basedatos.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    public void imprimirNombresInverso(Connection con) throws SQLException {

        String sql = "SELECT CONCAT(first_name, ' ', last_name) AS name FROM employees";

        List<String> nombres = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                nombres.add(rs.getString("name"));
            }
        }

        // Imprimir al revés
        for (int i = nombres.size() - 1; i >= 0; i--) {
            System.out.println(nombres.get(i));
        }
    }
}
