package br.ifrn.sigma.app.matrizes.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ifrn.sigma.app.matrizes.negocio.MatrizImpl;
import br.ifrn.sigma.dominio.Matriz;

@WebServlet("/matrizServlet")
public class MatrizServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public MatrizServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperar o ID do curso
        String id = request.getParameter("idCurso");
        int idCurso;

        // Verificar se foi informado um identificador de curso e este não é vazio
        if (id != null && !id.isEmpty()) {

            // Instancia a classe de negócio
            MatrizImpl negocio = new MatrizImpl();
            try {
                idCurso = Integer.parseInt(id);
            } catch (NumberFormatException nfex) {
                request.setAttribute("erro", "Identificação do curso inválida: " + id);
                request.getRequestDispatcher("/trataErro.jsp").forward(request, response);
                return;
            }

            // Solicita ao negócio para recuperar a matriz curricular desejada
            Matriz retorno = negocio.getMatriz(idCurso);

            // Armazena o resultado como atributo da requisição
            request.setAttribute("matriz", retorno);

            // Encaminha para a JSP encarregada de exibir os resultados
            request.getRequestDispatcher("/showMatriz.jsp").forward(request, response);
            return;
        }
        request.setAttribute("erro", "Identificação do curso inválida");
        request.getRequestDispatcher("/trataErro.jsp").forward(request, response);
    }

}
