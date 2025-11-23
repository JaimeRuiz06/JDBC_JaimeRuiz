package org.basedatos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClienteDAO {

    public void actualizarClientes(Connection con) throws SQLException {

        String sqlDelete = """
            DELETE FROM CLIENTES 
            WHERE DNI NOT IN ('78901234X', '89012345E', '56789012B')
        """;

        try (PreparedStatement ps = con.prepareStatement(sqlDelete)) {
            ps.executeUpdate();
        }

        String sqlUpdate = """
            UPDATE CLIENTES 
            SET NOMBRE = ?, CODIGO_POSTAL = ?
            WHERE DNI = ?
        """;

        try (PreparedStatement ps = con.prepareStatement(sqlUpdate)) {

            ps.setString(1, "NADALES");
            ps.setString(2, "44126");
            ps.setString(3, "78901234X");
            ps.executeUpdate();

            ps.setString(1, "ROJAS");
            ps.setNull(2, java.sql.Types.CHAR);
            ps.setString(3, "89012345E");
            ps.executeUpdate();

            ps.setString(1, "SAMPER");
            ps.setString(2, "29730");
            ps.setString(3, "56789012B");
            ps.executeUpdate();
        }
    }
    public void imprimirRegistros2(Connection con) throws SQLException {
        String sql = "SELECT DNI, NOMBRE, CODIGO_POSTAL FROM CLIENTES";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String dni = rs.getString("DNI");
                String nombre = rs.getString("NOMBRE");

                int cpInt = rs.getInt("CODIGO_POSTAL");
                String cp;

                if (rs.wasNull()) {
                    cp = "NULL";
                } else {
                    cp = String.valueOf(cpInt);
                }

                System.out.println(dni + " | " + nombre + " | " + cp);
            }
        }
    }

    public void mostrarClientesPorDni(Connection con, List<String> dnis) throws SQLException {

        String sql = "SELECT DNI, NOMBRE, CODIGO_POSTAL FROM CLIENTES WHERE DNI = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            for (String dni : dnis) {

                ps.setString(1, dni);   // se especifica el DNI para esta consulta

                try (ResultSet rs = ps.executeQuery()) {

                    // Solo habrá una fila por ser clave primaria
                    if (rs.next()) {
                        // Obtenemos los datos del cliente
                        String nombre = rs.getString("NOMBRE");
                        String cp = rs.getString("CODIGO_POSTAL");

                        System.out.println(dni + " | " + nombre + " | " + cp);
                    } else {
                        System.out.println(dni + " | NO EXISTE");
                    }
                }
            }
        }
    }


}
