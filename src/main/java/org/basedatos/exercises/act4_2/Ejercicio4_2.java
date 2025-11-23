package org.basedatos.exercises.act4_2;

import org.basedatos.dao.ClienteDAO;
import org.basedatos.util.ConexionBD;

import java.sql.Connection;

public class Ejercicio4_2 {
    public static void main(String[] args) {

        try (Connection con = ConexionBD.getConexion()) {

            ClienteDAO dao = new ClienteDAO();
            dao.imprimirRegistros2(con);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
