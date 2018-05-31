package com.rest.spring.front.controlador;

import com.rest.spring.core.basededatos.ReadRestaurant;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * com.learningjava.rest.spring.front
 * Class
 * By berto. 12/02/2018
 */
@Controller
public class HomeController {
    //anotacion que indica la ruta del archivo donde se va a pasar la informacion
    @RequestMapping(value="/", method = RequestMethod.GET)
    //metodo que se renderizara en index.ftl
    public String index(Model model) {
        //se crea una clase no estatica para la incorporacion de sus metodos
        ReadRestaurant dbQuery = new ReadRestaurant();
        // se le añade el metodo de la clase anterior para hacer la query de los restaurantes, y se añade a un atributo
       // model.addAttribute("restaurantes",dbQuery.readRestaurantAPI());
        model.addAttribute("restaurantes",ReadRestaurant.getRestaurants(""));
        // devuelve el metodo index y sus datos
        return "index";
    }
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}