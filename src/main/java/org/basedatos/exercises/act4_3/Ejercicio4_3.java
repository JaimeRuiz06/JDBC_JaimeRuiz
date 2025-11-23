package org.basedatos.exercises.act4_3;

import org.basedatos.dao.EmpleadoDAO;
import org.basedatos.util.ConexionBD;

import java.sql.Connection;

public class Ejercicio4_3 {
    public static void main(String[] args) {

        try (Connection con = ConexionBD.getConexion()) {

            EmpleadoDAO dao = new EmpleadoDAO();
            dao.imprimirNombresInverso(con);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

