<%@ page import="java.sql.*" %>

<%
String accion = request.getParameter("accion");
String idStr = request.getParameter("usuario_id");
String nombre = request.getParameter("nombre");

if (accion != null && idStr != null && !idStr.isEmpty()) {
    Connection conexion = null;
    PreparedStatement ps = null;
    int usuarioId = Integer.parseInt(idStr);

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection(
            "jdbc:mysql://localhost:8084/usuariosdb",
            "root", "n0m3l0"
        );

        if (accion.equals("alta") && nombre != null && !nombre.isEmpty()) {
            ps = conexion.prepareStatement("INSERT INTO datos (id, nombre) VALUES (?, ?)");
            ps.setInt(1, usuarioId);
            ps.setString(2, nombre);
            ps.executeUpdate();
            out.println("<script>alert('Usuario dado de alta correctamente.');</script>");
        } else if (accion.equals("baja")) {
            ps = conexion.prepareStatement("DELETE FROM datos WHERE id = ?");
            ps.setInt(1, usuarioId);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                out.println("<script>alert('Usuario dado de baja correctamente.');</script>");
            } else {
                out.println("<script>alert('El usuario no existe.');</script>");
            }
        }

    } catch (Exception e) {
        out.println("Error: " + e.getMessage());
    } finally {
        if (ps != null) try { ps.close(); } catch (Exception e) {}
        if (conexion != null) try { conexion.close(); } catch (Exception e) {}
    }
}
%>


