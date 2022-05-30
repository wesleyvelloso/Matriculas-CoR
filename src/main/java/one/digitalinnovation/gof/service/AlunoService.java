package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.model.Aluno;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio de cliente. Com
 * isso, se necessário, podemos ter multiplas implementações dessa mesma
 * interface.
 * 
 *
 */
public interface AlunoService {

	Iterable<Aluno> buscarTodos();

	Aluno buscarPorId(Long id);

	void inserir(Aluno aluno);

	void atualizar(Long id, Aluno aluno);

	void deletar(Long id);

}
