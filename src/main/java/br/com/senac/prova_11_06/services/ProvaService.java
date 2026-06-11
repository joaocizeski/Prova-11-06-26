package br.com.senac.prova_11_06.services;


import br.com.senac.prova_11_06.dtos.ProvaRequestDto;
import br.com.senac.prova_11_06.entidades.Prova;
import br.com.senac.prova_11_06.repositorios.ProvaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvaService {

    private ProvaRepositorio provaRepositorio;

    public ProvaService(ProvaRepositorio provaRepositorio) {
        this.provaRepositorio = provaRepositorio;
    }

    public Prova criar(ProvaRequestDto pessoa) {

        Prova pessoaFisicaPersist = this.provaDtoParaProva(pessoa);

        return provaRepositorio.save(pessoaFisicaPersist);

    }

    public Prova atualizar(Long id, ProvaRequestDto pessoa) {

            if(provaRepositorio.existsById(id)) {
            Prova pessoaFisicaPersist = this.provaDtoParaProva(pessoa);
            pessoaFisicaPersist.setId(id);

            return provaRepositorio.save(pessoaFisicaPersist);
        }

        throw new RuntimeException("Prova não encontrada!");
    }

    public void deletar(Long id) {
        if(provaRepositorio.existsById(id)) {
            provaRepositorio.deleteById(id);
        }

        throw new RuntimeException("Prova não encontrada!");
    }

    public List<Prova> listar(ProvaRequestDto filtro){
        return provaRepositorio.findAll();
    }

    public Prova listarById(Long id){
        if (provaRepositorio.existsById(id)){
            return provaRepositorio.findById(id).get();
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
