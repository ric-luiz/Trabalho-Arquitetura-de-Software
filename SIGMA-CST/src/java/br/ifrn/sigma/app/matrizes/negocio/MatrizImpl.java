package br.ifrn.sigma.app.matrizes.negocio;

import java.util.List;

import br.ifrn.sigma.app.componentes.persistencia.ComponenteDAO;
import br.ifrn.sigma.app.matrizes.persistencia.MatrizDAO;
import br.ifrn.sigma.dominio.Componente;
import br.ifrn.sigma.dominio.Matriz;

public class MatrizImpl {

    public Matriz getMatriz(int idCurso) {

        // Instancia as classes de acesso a dados 
        MatrizDAO matrizDao = new MatrizDAO();
        ComponenteDAO prsComponente = new ComponenteDAO();

        // Recupera a matriz curricular
        Matriz matriz = matrizDao.getMatriz(idCurso);

        // Recupera, na sequência, a lista de componentes curriculares associados
        List<Componente> componentes = prsComponente.getComponentes(matriz.getId());
        matriz.setComponentesCurriculares(componentes);

        // Retorna a matriz curricular
        return matriz;
    }
    
    public Componente getEmenta(int idDisciplina) {

        // Instancia as classes de acesso a dados 
        MatrizDAO matrizDao = new MatrizDAO();
        Componente componente = new Componente();
        ComponenteDAO prsComponente = new ComponenteDAO();

        // Recupera a matriz curricular
        Matriz matriz = matrizDao.getMatriz(idDisciplina);

        // Recupera, na sequência, a lista de componentes curriculares associados
        String ementa = prsComponente.getEmenta(idDisciplina);
        componente.setEmenta(ementa);
        if (ementa == null){
            return null;
        }
        String nome = prsComponente.getNome(idDisciplina);
        componente.setNome(nome);
        // Retorna o componente curricular
        return componente;
    }
}
