package br.ifrn.sigma.app.componentes.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ifrn.sigma.dominio.Componente;
import br.ifrn.sigma.util.persistencia.DAO;

public class ComponenteDAO extends DAO{   

    public List<Componente> getComponentes(int id) {
        if (con != null) {
            try {
                PreparedStatement stGetComponentes = con.prepareStatement("SELECT * FROM componente_curricular WHERE id_matriz = ? ORDER BY semestre");
                stGetComponentes.setInt(1, id);
                ResultSet rsComponentes = stGetComponentes.executeQuery();
                List<Componente> lista = new ArrayList<>();
                while (rsComponentes.next()) {
                    Componente cmp = new Componente();
                    cmp.setId(rsComponentes.getInt("id"));
                    cmp.setNome(rsComponentes.getString("nome"));
                    cmp.setCreditos(rsComponentes.getInt("creditos"));
                    cmp.setEmenta(rsComponentes.getString("ementa"));
                    cmp.setSemestre(rsComponentes.getInt("semestre"));
                    lista.add(cmp);
                }
                return lista;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public String getEmenta(int id) {
        if (con != null) {
            try {
                PreparedStatement stGetComponentes = con.prepareStatement("SELECT ementa FROM componente_curricular WHERE id = ? ORDER BY semestre");
                stGetComponentes.setInt(1, id);
                ResultSet rsComponentes = stGetComponentes.executeQuery();                
                while (rsComponentes.next()) {
                    Componente cmp = new Componente();                                                            
                    cmp.setEmenta(rsComponentes.getString("ementa"));                                        
                    return cmp.getEmenta();
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public String getNome(int id) {
        if (con != null) {
            try {
                PreparedStatement stGetComponentes = con.prepareStatement("SELECT nome FROM componente_curricular WHERE id = ? ORDER BY semestre");
                stGetComponentes.setInt(1, id);
                ResultSet rsComponentes = stGetComponentes.executeQuery();                
                while (rsComponentes.next()) {
                    Componente cmp = new Componente();                                                            
                    cmp.setEmenta(rsComponentes.getString("nome"));                                        
                    return cmp.getEmenta();
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
