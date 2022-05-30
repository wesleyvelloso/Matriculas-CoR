package one.digitalinnovation.gof.service.impl;

import one.digitalinnovation.gof.model.Aluno;
import one.digitalinnovation.gof.service.MatriculaHandler;
import org.springframework.stereotype.Component;

@Component
public class BibliotecaServiceImpl implements MatriculaHandler {

    @Override
    public Integer getPriority() {
        return 3;
    }

    @Override
    public void chain(Aluno aluno) {
        System.out.println("Adicionando cadastro biblioteca");

    }
}
