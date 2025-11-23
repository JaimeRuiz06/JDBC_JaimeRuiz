package org.basedatos.exercises.act4_1;

import org.basedatos.dao.ClienteDAO;
import org.basedatos.util.ConexionBD;

import java.sql.Connection;

public class Ejercicio4_1 {
    public static void main(String[] args) {

        try (Connection con = ConexionBD.getConexion()) {

            ClienteDAO dao = new ClienteDAO();
            dao.actualizarClientes(con);

            System.out.println("Cambios aplicados correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

