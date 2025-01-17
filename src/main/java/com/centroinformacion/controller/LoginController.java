package com.centroinformacion.controller;

import java.util.Date;
import java.util.List;

import com.centroinformacion.util.FunctionUtil;
import net.sf.jasperreports.functions.FunctionsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.centroinformacion.entity.Opcion;
import com.centroinformacion.entity.Rol;
import com.centroinformacion.entity.Usuario;
import com.centroinformacion.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService servicio;

    @PostMapping("/login")
    public String login(Usuario user, HttpSession session, HttpServletRequest request) {
        Usuario usuario = servicio.login(user);
        if (usuario == null) {
            request.setAttribute("mensaje", "El usuario no existe");
            return "intranetLogin";
        } else if (usuario.getDni().equalsIgnoreCase("87654321")) {
            request.setAttribute("mensaje", "Un administrador debe activar tu usuario");
            return "intranetLogin";
        } else {
            List<Rol> roles = servicio.traerRolesDeUsuario(usuario.getIdUsuario());
            List<Opcion> menus = servicio.traerEnlacesDeUsuario(usuario.getIdUsuario());
            List<Opcion> menusTipo1 = menus.stream().filter(p -> p.getTipo() == 1).toList();
            List<Opcion> menusTipo2 = menus.stream().filter(p -> p.getTipo() == 2).toList();
            List<Opcion> menusTipo3 = menus.stream().filter(p -> p.getTipo() == 3).toList();
            List<Opcion> menusTipo4 = menus.stream().filter(p -> p.getTipo() == 4).toList();
            List<Opcion> menusTipo5 = menus.stream().filter(p -> p.getTipo() == 5).toList();

            /* barra de navegacion */
            session.setAttribute("objUsuario", usuario); //donde se alamcena todo del usuario logueado
            session.setAttribute("objMenusTipo1", menusTipo1);
            session.setAttribute("objMenusTipo2", menusTipo2);
            session.setAttribute("objMenusTipo3", menusTipo3);
            session.setAttribute("objMenusTipo4", menusTipo4);
            session.setAttribute("objMenusTipo5", menusTipo5);
            session.setAttribute("objRoles", roles);

            return "intranetHome";
        }
    }


    @PostMapping("/registerUser")
    public String register(Usuario user, HttpSession session, HttpServletRequest request) {
        user.setDni("87654321");
        user.setDireccion("Por Defecto");
        user.setFechaRegistro(new Date());
        user.setFechaNacimiento(FunctionUtil.getFechaDate("2100-01-01"));

        List<Usuario> userExist = servicio.buscaPorLogin(user.getLogin());
        if (userExist != null) {
            request.setAttribute("mensaje", "El usuario ya existe");
            return "intranetLogin";
        } else {
            servicio.insertaActualizaUsuario(user);
            request.setAttribute("register", "Registro enviado. Un administrador debe activar tu usuario");
            return "intranetLogin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        session.invalidate();

        response.setHeader("Cache-control", "no-cache");
        response.setHeader("Expires", "0");
        response.setHeader("Pragma", "no-cache");

        request.setAttribute("mensaje", "El usuario salió de sesión");
        return "intranetLogin";

    }
}