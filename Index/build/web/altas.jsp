<%-- 
    Document   : altas
    Created on : Apr 10, 2025, 5:28:45 PM
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
        String contrasenia = request.getParameter("contrasenia");
        String nombre = request.getParameter("nombre");
        String estado = request.getParameter("estado");
        String sexo = request.getParameter("sexo");

        Connection con = null;
        PreparedStatement pst = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emps", "migue", "n0m3l0");

            String query = "INSERT INTO usuarios (contrasenia, nombre, estado, sexo) VALUES (?, ?, ?, ?)";
            pst = con.prepareStatement(query);
            pst.setString(1, contrasenia);
            pst.setString(2, nombre);
            pst.setString(3, estado);
            pst.setString(4, sexo);
            pst.executeUpdate();
            
            out.print("<p>Usuario registrado exitosamente.</p>");
        } catch (Exception e) {
            out.print("<p>Error: " + e.getMessage() + "</p>");
        } finally {
            if (pst != null) pst.close();
            if (con != null) con.close();
        }
    %>
    </body>
</html>
