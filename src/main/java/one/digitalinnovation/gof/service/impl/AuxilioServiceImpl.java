package one.digitalinnovation.gof.service.impl;

import one.digitalinnovation.gof.model.Aluno;
import one.digitalinnovation.gof.service.MatriculaHandler;
import org.springframework.stereotype.Component;

@Component
public class AuxilioServiceImpl implements MatriculaHandler {

    @Override
    public Integer getPriority() {
        return 2;
    }

    @Override
    public void chain(Aluno aluno) {

        String perfil = aluno.getMatricula().getPerfil();
        String ensino = aluno.getEnsino();

        if (ensino.equals("Escola publica")) {
            if (perfil.equals("Baixa renda")) {
                aluno.getMatricula().setAuxilioMoradia(true);
                aluno.getMatricula().setAuxilioRefeicao(true);
                aluno.getMatricula().setAuxilioTransporte(true);
            } else if (perfil.equals("Media renda")) {
                aluno.getMatricula().setAuxilioRefeicao(true);
                aluno.getMatricula().setAuxilioTransporte(true);
            } else {
                aluno.getMatricula().setAuxilioRefeicao(true);
            }
        }
    }
}

