/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template

package com.GRH.myapp.GRH_myapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void probarConexion() {
        Usuario usuario = new Usuario("nombre", "correo@ejemplo.com");
        usuarioRepository.save(usuario);
        System.out.println("Usuario guardado: " + usuario);
    }
}
 */