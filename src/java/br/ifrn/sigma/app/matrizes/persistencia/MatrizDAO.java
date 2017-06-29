package br.ifrn.sigma.app.matrizes.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ifrn.sigma.dominio.Matriz;
import br.ifrn.sigma.util.persistencia.DAO;

public class MatrizDAO extends DAO {  

    public Matriz getMatriz(int idCurso) {
        if (con != null) {
            try {
                PreparedStatement stGetMatriz = con.prepareStatement("SELECT * FROM matriz_curricular WHERE id_curso = ?");
                stGetMatriz.setInt(1, idCurso);
                ResultSet rsMatriz = stGetMatriz.executeQuery();
                if (rsMatriz.next()) {
                    Matriz matriz = new Matriz();
                    matriz.setId(rsMatriz.getInt("id"));
                    matriz.setDescricao(rsMatriz.getString("descricao"));
                    matriz.setAtiva(rsMatriz.getBoolean("ativa"));
                    return matriz;
                } else {
                    System.err.println("consulta não retornou informações");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
