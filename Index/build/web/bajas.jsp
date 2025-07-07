<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Baja de Usuario</title>
</head>
<body>
    <h1>Baja de Usuario</h1>
    <%
    String usuarioId = request.getParameter("usuario_id");
    if (usuarioId != null && !usuarioId.isEmpty()) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/usuariosdb", "root", "n0m3l0"); 

            ps = conexion.prepareStatement("SELECT * FROM datos WHERE id = ?");
            ps.setInt(1, Integer.parseInt(usuarioId));
            rs = ps.executeQuery();

            if (!rs.next()) {
                out.println("<script>alert('El usuario no existe.');</script>");
            } else {
                ps = conexion.prepareStatement("DELETE FROM datos WHERE id = ?");
                ps.setInt(1, Integer.parseInt(usuarioId));
                ps.executeUpdate();
                out.println("<script>alert('Usuario dado de baja correctamente.');</script>");
            }

        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        } finally {
            if (rs != null) try { rs.close(); } catch (Exception e) {}
            if (ps != null) try { ps.close(); } catch (Exception e) {}
            if (conexion != null) try { conexion.close(); } catch (Exception e) {}
        }
    } else {
        out.println("<script>alert('No se recibió el ID del usuario.');</script>");
    }
    %>
    <br><br>
    <a href="index.html">Regresar</a>
</body>
</html>

