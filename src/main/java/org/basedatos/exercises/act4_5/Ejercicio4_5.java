package org.basedatos.exercises.act4_5;

import org.basedatos.dao.ClienteDAO;
import org.basedatos.util.ConexionBD;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

public class Ejercicio4_5 {
    public static void main(String[] args) {

        try (Connection con = ConexionBD.getConexion()) {

            ClienteDAO dao = new ClienteDAO();

            List<String> dnis = Arrays.asList(
                    "78901234X",
                    "89012345E",
                    "56789012B",
                    "00000000Z"   // uno inventado para ver que no existe
            );

            dao.mostrarClientesPorDni(con, dnis);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
