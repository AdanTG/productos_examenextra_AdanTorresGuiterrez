package com.upiiz.productos.config;

import com.upiiz.productos.model.Rol;
import com.upiiz.productos.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = request.getSession(false);
        Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuario") : null;

        String cabeceraSolicitud = request.getHeader("X-Requested-With");
        boolean esAjax = "XMLHttpRequest".equals(cabeceraSolicitud);

        if (usuario == null) {
            if (esAjax) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Sesion no valida o expirada");
            } else {
                response.sendRedirect(request.getContextPath() + "/login");
            }
            return false;
        }

        String uri = request.getRequestURI();
        boolean rutaDeAdministrador =
                uri.contains("/productos/nuevo")
                        || uri.contains("/productos/guardar")
                        || uri.contains("/productos/editar")
                        || uri.contains("/productos/eliminar");

        if (rutaDeAdministrador && usuario.getRol() != Rol.ADMINISTRADOR) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "No tienes permisos para esta operacion");
            return false;
        }

        return true;
    }
}
