package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.model.Aluno;

public interface MatriculaHandler {

    Integer getPriority();
    void chain(Aluno aluno);
}
