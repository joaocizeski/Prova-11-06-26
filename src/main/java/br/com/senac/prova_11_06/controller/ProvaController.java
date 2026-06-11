package br.com.senac.prova_11_06.controller;

import br.com.senac.prova_11_06.dtos.ProvaRequestDto;
import br.com.senac.prova_11_06.entidades.Prova;
import br.com.senac.prova_11_06.services.ProvaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/prova")
public class ProvaController {

   private ProvaService provaService;

    public ProvaController(ProvaService provaService) {
        this.provaService = provaService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Prova>> listar(ProvaRequestDto filtro) {
        return ResponseEntity
                .ok(provaService.listar(filtro));
    }

    @PostMapping("/criar")
    public ResponseEntity<Prova> criar(@RequestBody ProvaRequestDto pessoa) {
        try {
            return ResponseEntity.ok(provaService.criar(pessoa));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Prova> atualizar(@PathVariable Long id, @RequestBody ProvaRequestDto pessoa) {
        try {
            return ResponseEntity.ok(provaService.atualizar(id, pessoa));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            provaService.deletar(id);
            return ResponseEntity.ok(null);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }
    }
 }
