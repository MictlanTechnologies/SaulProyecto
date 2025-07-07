<%-- 
    Document   : formulario
    Created on : Apr 10, 2025, 4:46:35 PM
    Author     : migue
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
     
<%
String nombre = request.getParameter("nombre");
String numero = request.getParameter("numero");
String posicion = request.getParameter("posicion");
String liga = request.getParameter("liga");
String equipo = request.getParameter("equipo");
String[] habilidades = request.getParameterValues("habilidades");


out.println("<p>Nombre: " + nombre + "</p>");
out.println("<p>Número de camiseta: " + numero + "</p>");
out.println("<p>Posición: " + posicion + "</p>");
out.println("<p>Liga: " + liga + "</p>");
out.println("<p>Equipo: " + equipo + "</p>");
out.print("<p>Habilidades: ");
if (habilidades != null) {
    for (int i = 0; i < habilidades.length; i++) {
        out.print(habilidades[i] + " ");
    }
} else {
    out.print("Ninguna");
}

%>
    </body>
</html>
