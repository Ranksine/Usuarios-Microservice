package com.c17290822.Usuario.controller;

import com.c17290822.Usuario.entities.Usuario;
import com.c17290822.Usuario.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping( path = "/usuarios")
public class UsuariosRestController {
    @Autowired
    private UsuariosRepository usuariosRepository;

    @GetMapping
    public  Iterable<Usuario> getAllUsuarios(){return usuariosRepository.findAll();}

    @GetMapping( path = "/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable long id){
        Optional<Usuario> usuarioIndb = usuariosRepository.findById(id);

        if(usuarioIndb.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(usuarioIndb.get());
    }

    @PostMapping
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario, @PathVariable long id){
        Optional <Usuario> usuarioIndb = usuariosRepository.findById(id);

        if(usuarioIndb.isEmpty())
            return ResponseEntity.badRequest().build();

        usuario.setIdUsuario(usuarioIndb.get().getIdUsuario());

        boolean isOk = true;
        if (usuario == null)
            return ResponseEntity.badRequest().build();

        if (usuario.getNombre() == null || usuario.getCorreo() == null ||
            usuario.getNombre().isEmpty()  || usuario.getCorreo().isEmpty() ||
            usuario.getPassword() == null || usuario.getPassword().isEmpty()){
            isOk = false;
        }

        if(isOk){
            Usuario saved = usuariosRepository.save(usuario);
            return  ResponseEntity.ok(saved);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping( path = "/{id}")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario, @PathVariable long id){
        Optional <Usuario> usuarioIndb = usuariosRepository.findById(id);

        if(usuarioIndb.isEmpty())
            return ResponseEntity.badRequest().build();

        usuario.setIdUsuario(usuarioIndb.get().getIdUsuario());

        boolean isOk = true;
        if (usuario == null)
            return ResponseEntity.badRequest().build();

        if (usuario.getNombre() == null || usuario.getCorreo() == null ||
            usuario.getNombre().isEmpty()  || usuario.getCorreo().isEmpty() ||
            usuario.getPassword() == null || usuario.getPassword().isEmpty()){
            isOk = false;
        }

        if (isOk){
            Usuario saved = usuariosRepository.save(usuario);
            return  ResponseEntity.ok(saved);
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable long id){
        Optional<Usuario> usuarioIndb = usuariosRepository.findById(id);

        if(usuarioIndb.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        usuariosRepository.deleteById(id);
        return ResponseEntity.ok(usuarioIndb.get());
    }

}
