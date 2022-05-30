package one.digitalinnovation.gof.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long numero;

    private String perfil;
    private boolean auxilioRefeicao = false;
    private boolean auxilioMoradia = false;
    private boolean auxilioTransporte = false;

}
