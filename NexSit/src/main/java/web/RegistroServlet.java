package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import model.usuario.Usuario;
import sql.hibernateimpl.usuario.UsuarioHiberImpl;

@WebServlet("/Registro")
public class RegistroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter("nombre-usuario");
        String contrasena = request.getParameter("contrasena");
        String telefono = request.getParameter("telefono");
        String ubicacion = request.getParameter("ubicacion");
        String correo = request.getParameter("correo");

        Usuario u = new Usuario();
        u.setUsuario(usuario);
        u.setContraseña(contrasena);
        u.setTelefono(telefono);
        u.setUbicacion(ubicacion);
        u.setCorreo(correo);
        u.setRol("cliente");

        UsuarioHiberImpl.getInstance().save(u);
        response.sendRedirect("Iniciar_Sesion.html");
    }
}