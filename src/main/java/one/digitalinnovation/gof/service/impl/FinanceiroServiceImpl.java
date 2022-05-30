package one.digitalinnovation.gof.service.impl;

import one.digitalinnovation.gof.model.Aluno;
import one.digitalinnovation.gof.service.MatriculaHandler;
import org.springframework.stereotype.Component;

@Component
public class FinanceiroServiceImpl implements MatriculaHandler {

    @Override
    public Integer getPriority() {
        return 1;
    }

    @Override
    public void chain(Aluno aluno) {
       int rendaAluno = aluno.getRendaMensal();

       if(rendaAluno >= 2000)
           aluno.getMatricula().setPerfil("Alta renda");
       else if (rendaAluno >= 1500)
           aluno.getMatricula().setPerfil("Media renda");
       else
           aluno.getMatricula().setPerfil("Baixa renda");

    }
}
