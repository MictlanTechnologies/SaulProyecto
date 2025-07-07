package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import model.usuario.Usuario;
import sql.hibernateimpl.usuario.UsuarioHiberImpl;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");

        Usuario u = UsuarioHiberImpl.getInstance().findByUsuarioAndContraseña(usuario, contrasena);
        if (u != null) {
            response.sendRedirect("Opciones.html");
        } else {
            response.sendRedirect("Iniciar_Sesion.html?error=1");
        }
    }
}
