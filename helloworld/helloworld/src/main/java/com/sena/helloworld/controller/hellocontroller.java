package com.sena.helloworld.controller;

import org.springframework.web.bind.annotation.RestController;

import com.sena.helloworld.Dto.PersonDto;
import com.sena.helloworld.Dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class hellocontroller {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }


    @GetMapping("/hello2")
    public ResponseEntity<response> hello2() {
        response messagResponse = new response(null);
        messagResponse.setMessage("Hello from hello2 endpoint!");
        return new ResponseEntity<>(messagResponse,HttpStatus.OK);
    }

    @GetMapping("saludo/{nombre}")
    public ResponseEntity<response> saludo (@PathVariable String nombre) {
        response saludoResponse = new response(null);
        saludoResponse.setMessage("Hola " + nombre + ", bienvenido a Nuestro servicio!");
        return new ResponseEntity<>(saludoResponse, HttpStatus.OK);
    }

   @PostMapping("saludo2")
    public ResponseEntity<response> saludo2 (@RequestBody PersonDto perso) {
       
        response resp = new response(null);
        resp.setMessage("Cordial Saludo");
        String details = "Nombre: " + perso.getNombre() + 
                         ", Apellido: " + perso.getApellido() + 
                         ", Edad: " + perso.getEdad();
    
        resp.setMessage(resp.getMessage() + " - " + details);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

}


