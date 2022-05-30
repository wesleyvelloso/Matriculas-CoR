package one.digitalinnovation.gof.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;

	private int rendaMensal;

	private String ensino;

	@ManyToOne
	private Endereco endereco;

	@OneToOne
	private Matricula matricula;
}
