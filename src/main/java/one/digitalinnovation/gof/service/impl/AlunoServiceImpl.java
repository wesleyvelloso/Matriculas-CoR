package one.digitalinnovation.gof.service.impl;

import java.util.List;
import java.util.Optional;

import one.digitalinnovation.gof.model.*;
import one.digitalinnovation.gof.service.AlunoService;
import one.digitalinnovation.gof.service.ViaCepService;
import one.digitalinnovation.gof.service.MatriculaHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementação da <b>Strategy</b> {@link AlunoService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 * 
 *
 */
@Service
public class AlunoServiceImpl implements AlunoService {

	// Singleton: Injetar os componentes do Spring com @Autowired.
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private MatriculaRepository matriculaRepository;
	@Autowired
	private ViaCepService viaCepService;

	@Autowired
	public List<MatriculaHandler> handlers;
	
	// Strategy: Implementar os métodos definidos na interface.
	// Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

	@Override
	public Iterable<Aluno> buscarTodos() {
		// Buscar todos os Clientes.
		return alunoRepository.findAll();
	}

	@Override
	public Aluno buscarPorId(Long id) {
		// Buscar Aluno por ID.
		Optional<Aluno> aluno = alunoRepository.findById(id);
		return aluno.get();
	}

	@Override
	public void inserir(Aluno aluno) {
		salvarAluno(aluno);
	}

	@Override
	public void atualizar(Long id, Aluno aluno) {
		// Buscar Cliente por ID, caso exista:
		Optional<Aluno> clienteBd = alunoRepository.findById(id);
		if (clienteBd.isPresent()) {
			salvarAluno(aluno);
		}
	}

	@Override
	public void deletar(Long id) {
		// Deletar Aluno por ID.
		alunoRepository.deleteById(id);
	}

	private void salvarAluno(Aluno aluno) {
		// Verificar se o Endereco do Aluno já existe (pelo CEP).
		String cep = aluno.getEndereco().getCep();
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
			// Caso não exista, integrar com o ViaCEP e persistir o retorno.
			Endereco novoEndereco = viaCepService.consultarCep(cep);
			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});
		aluno.setEndereco(endereco);

		Long numeroMatricula = aluno.getMatricula().getNumero();
		Matricula matricula = matriculaRepository.findById(String.valueOf(numeroMatricula)).orElseGet(() -> {

            Matricula novaMatricula = new Matricula();
			aluno.setMatricula(novaMatricula);
			CoR(aluno);
			matriculaRepository.save(novaMatricula);
			return novaMatricula;

		});
		aluno.setMatricula(matricula);

		// Inserir Aluno, vinculando o Endereco (novo ou existente).
		alunoRepository.save(aluno);
	}

	private void CoR(Aluno aluno) {

		// Todas as classes que extendem de MatriculaHandler injetadas
		handlers
				// Transformadas em stream
				.stream()
				// Ordenadas pelo método getPriority() (opcional)
				.sorted((a, b) -> a.getPriority().compareTo(b.getPriority()))
				// Executadas cada uma das regras.
				.forEach(next -> next.chain(aluno));
	}


}
