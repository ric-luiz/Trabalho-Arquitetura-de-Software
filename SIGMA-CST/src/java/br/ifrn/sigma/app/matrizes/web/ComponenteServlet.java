package br.ifrn.sigma.app.matrizes.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ifrn.sigma.app.matrizes.negocio.MatrizImpl;
import br.ifrn.sigma.dominio.Componente;
import br.ifrn.sigma.dominio.Matriz;

@WebServlet("/componenteServlet")
public class ComponenteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ComponenteServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperar o ID do curso
        String id = request.getParameter("idDisciplina");
        int idDisciplina;

        // Verificar se foi informado um identificador de curso e este não é vazio
        if (id != null && !id.isEmpty()) {

            // Instancia a classe de negócio
            MatrizImpl negocio = new MatrizImpl();
            try {
                idDisciplina = Integer.parseInt(id);
            } catch (NumberFormatException nfex) {
                request.setAttribute("erro", "Identificação do curso inválida: " + id);
                request.getRequestDispatcher("/trataErro.jsp").forward(request, response);
                return;
            }

            try {
                Componente retorno = negocio.getEmenta(idDisciplina);
                System.out.println(retorno.toString());
                // Armazena o resultado como atributo da requisição
                request.setAttribute("componente", retorno);

                // Encaminha para a JSP encarregada de exibir os resultados
                request.getRequestDispatcher("/showEmenta.jsp").forward(request, response);
                return;
            } catch (NullPointerException npe) {
                request.setAttribute("erro", "Não há ementra disponível para este curso!");
                request.getRequestDispatcher("/trataErroEmenta.jsp").forward(request, response);
            }
            

        }
        request.setAttribute("erro", "Não há ementra disponível para este curso!");
        request.getRequestDispatcher("/trataErroEmenta.jsp").forward(request, response);
    }

}
