package org.basedatos.exercises.act4_4;

import org.basedatos.dao.UtilsDAO;
import org.basedatos.util.ConexionBD;

import java.sql.Connection;

public class Ejercicio4_4 {
    public static void main(String[] args) {

        try (Connection con = ConexionBD.getConexion()) {

            UtilsDAO dao = new UtilsDAO();
            String consulta = "SELECT * FROM CLIENTES";

            int filas = dao.contarFilasScrollable(con, consulta);

            System.out.println("Número de filas: " + filas);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

