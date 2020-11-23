package br.com.alura.job;

import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

import br.com.alura.entidade.AgendamentoEmail;
import br.com.alura.servico.AgendamentoEmailServico;

@Singleton
public class AgendamentoEmailJob {

    @Inject
    private AgendamentoEmailServico agendamentoEmailServico;

    @Schedule(hour = "*", minute = "*", second = "*/10")
    public void enviarEmail() {
        List<AgendamentoEmail> list = agendamentoEmailServico.listarPorNaoAgendado();

        list.forEach(emailNaoAgendado -> {
            agendamentoEmailServico.enviar(emailNaoAgendado);
            agendamentoEmailServico.alterar(emailNaoAgendado);
        });
    }
}
