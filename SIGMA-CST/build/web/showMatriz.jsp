<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="br.ifrn.sigma.dominio.Matriz, br.ifrn.sigma.dominio.Componente"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SIGMA</title>
    </head>
    <body>
        <%
            Matriz matriz = (Matriz) request.getAttribute("matriz");
        %>
        <h2><%= matriz.getDescricao()%></h2>
        (matriz curricular <%= (matriz.isAtiva() ? "ativa" : "não ativa")%>)

        <h3>Componentes Curriculares por Semestres</h3>
        <table border="1">
            <tr>
                <th>Nome do componente curricular</th>
                <th>Créditos</th>
                <th>Hora-aula</th>
                <th>Hora</th>
            </tr>
            <%	int semestreAtual = -1;
                for (Componente cmp : matriz.getComponentesCurriculares()) {
                    if (cmp.getSemestre() > semestreAtual) {
                        out.println("<th colspan=\"4\">" + cmp.getSemestre() + "º SEMESTRE</th>");
                        semestreAtual = cmp.getSemestre();
                    }
            %>
            <tr>
                <td><%= cmp.getNome()%></td>
                <td><%= cmp.getCreditos()%></td>
                <td><%= cmp.getHoraAula()%></td>
                <td><%= cmp.getHora()%></td>
                <td><a href="componenteServlet?idDisciplina=<%= cmp.getId()%>">Ementa</a></td>
            </tr>
            <%	}%>
        </table>
    </body>
</html>