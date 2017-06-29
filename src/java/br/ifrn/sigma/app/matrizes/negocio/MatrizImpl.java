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

}
