package org.basedatos.dao;

import java.sql.*;

public class UtilsDAO {

    public int contarFilasScrollable(Connection con, String sql) throws SQLException {

        try (PreparedStatement ps = con.prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,   // permite mover el cursor libremente
                ResultSet.CONCUR_READ_ONLY
        )) {
            try (ResultSet rs = ps.executeQuery()) {

                if (!rs.last()) {
                    return 0;        // no hay filas
                }
                return rs.getRow();   // número de la última fila = total de filas
            }
        }
    }
}

