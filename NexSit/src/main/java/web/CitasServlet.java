package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import model.usuario.Citas;
import sql.hibernateimpl.usuario.CitasHiberImpl;

@WebServlet("/Citas")
public class CitasServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        List<Citas> citasList = CitasHiberImpl.getInstance().findAll();

        StringBuilder json = new StringBuilder();
        json.append("[");
        for (int i = 0; i < citasList.size(); i++) {
            Citas c = citasList.get(i);
            if (i > 0) json.append(',');
            json.append('{')
                    .append("\"id\":").append(c.getId()).append(',')
                    .append("\"dia\":\"").append(c.getDia()).append("\",")
                    .append("\"hora\":\"").append(c.getHora()).append("\",")
                    .append("\"estado\":\"").append(c.getEstado()).append("\"");
            if (c.getUsuario() != null) {
                json.append(',').append("\"usuario\":{")
                        .append("\"id\":").append(c.getUsuario().getId()).append(',')
                        .append("\"nombre\":\"").append(c.getUsuario().getUsuario()).append("\",")
                        .append("\"telefono\":\"").append(c.getUsuario().getTelefono()).append("\"}");
            }
            json.append('}');
        }
        json.append(']');

        PrintWriter out = response.getWriter();
        out.print(json.toString());
        out.flush();
    }
}
