<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consultar</title>
</head>
<body>
<%
String accion = request.getParameter("accion");
String idStr = request.getParameter("usuario_id");
String nombre = request.getParameter("nombre");

if (accion != null) {
    Connection conexion = null;
    PreparedStatement ps = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection(
            "jdbc:mysql://localhost:8084/usuariosdb",
            "root", "n0m3l0"
        );

        if (accion.equals("alta") && idStr != null && !idStr.isEmpty() && nombre != null && !nombre.isEmpty()) {
            int usuarioId = Integer.parseInt(idStr);
            ps = conexion.prepareStatement("INSERT INTO datos (id, nombre) VALUES (?, ?)");
            ps.setInt(1, usuarioId);
            ps.setString(2, nombre);
            ps.executeUpdate();
            out.println("<script>alert('Usuario dado de alta correctamente.');</script>");

        } else if (accion.equals("baja") && idStr != null && !idStr.isEmpty()) {
            int usuarioId = Integer.parseInt(idStr);
            ps = conexion.prepareStatement("DELETE FROM datos WHERE id = ?");
            ps.setInt(1, usuarioId);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                out.println("<script>alert('Usuario dado de baja correctamente.');</script>");
            } else {
                out.println("<script>alert('El usuario no existe.');</script>");
            }

        } else if (accion.equals("cambiar") && idStr != null && !idStr.isEmpty() && nombre != null && !nombre.isEmpty()) {
            int usuarioId = Integer.parseInt(idStr);
            ps = conexion.prepareStatement("UPDATE datos SET nombre = ? WHERE id = ?");
            ps.setString(1, nombre);
            ps.setInt(2, usuarioId);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                out.println("<script>alert('Nombre actualizado correctamente.');</script>");
            } else {
                out.println("<script>alert('El usuario no existe.');</script>");
            }
        }

    } catch (Exception e) {
        out.println("<p>Error: " + e.getMessage() + "</p>");
    } finally {
        if (ps != null) try { ps.close(); } catch (Exception e) {}
        if (conexion != null) try { conexion.close(); } catch (Exception e) {}
    }
}

if ("consultar".equals(accion)) {
%>
    <h2>Usuarios Registrados</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
        </tr>
<%
    Connection conexion = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://localhost:8084/usuariosdb", "root", "n0m3l0");
        stmt = conexion.createStatement();
        rs = stmt.executeQuery("SELECT id, nombre FROM datos");

        while (rs.next()) {
%>
        <tr>
            <td><%= rs.getInt("id") %></td>
            <td><%= rs.getString("nombre") %></td>
        </tr>
<%
        }
    } catch (Exception e) {
        out.println("<tr><td colspan='2'>Error: " + e.getMessage() + "</td></tr>");
    } finally {
        if (rs != null) try { rs.close(); } catch (Exception e) {}
        if (stmt != null) try { stmt.close(); } catch (Exception e) {}
        if (conexion != null) try { conexion.close(); } catch (Exception e) {}
    }
%>
    </table>
<%
}
%>
</body>
</html>
