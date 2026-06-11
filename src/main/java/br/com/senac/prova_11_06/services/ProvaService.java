package br.com.senac.prova_11_06.services;


import br.com.senac.prova_11_06.dtos.ProvaFiltroDto;
import br.com.senac.prova_11_06.dtos.ProvaRequestDto;
import br.com.senac.prova_11_06.entidades.Prova;
import br.com.senac.prova_11_06.repositorios.ProvaRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class ProvaService {

    private ProvaRepositorio provaRepositorio;

    public ProvaService(ProvaRepositorio provaRepositorio) {
        this.provaRepositorio = provaRepositorio;
    }

    @GetMapping("/listar")
    public List<Prova> listar(ProvaFiltroDto filtro) {
        if (filtro.getTitulo() != null) {
            return provaRepositorio.findByTitulo(filtro.getTitulo());
        }
        return provaRepositorio.findAll();
    }

    public Prova criar(ProvaRequestDto prova) {

        Prova provaPersist = this.provaDtoParaProva(prova);

        return provaRepositorio.save(provaPersist);

    }

    public Prova atualizar(Long id, ProvaRequestDto prova) {

            if(provaRepositorio.existsById(id)) {
            Prova provaPersist = this.provaDtoParaProva(prova);
            provaPersist.setId(id);

            return provaRepositorio.save(provaPersist);
        }

        throw new RuntimeException("Prova não encontrada!");
    }

    public void deletar(Long id) {
        if(provaRepositorio.existsById(id)) {
            provaRepositorio.deleteById(id);
        }

        throw new RuntimeException("Prova não encontrada!");
    }

    private Prova provaDtoParaProva(ProvaRequestDto entrada) {
        Prova saida = new Prova();
        saida.setTitulo(entrada.getTitulo());
        saida.setMateria(entrada.getMateria());
        saida.setData(entrada.getData());

        return saida;
    }
}
