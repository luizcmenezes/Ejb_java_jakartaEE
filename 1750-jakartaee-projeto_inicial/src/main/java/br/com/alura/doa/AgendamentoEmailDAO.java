package br.com.alura.doa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.alura.entidade.AgendamentoEmail;

@Stateless
public class AgendamentoEmailDAO {

    @PersistenceContext
    private EntityManager em;

    public List<AgendamentoEmail> listar() {
        return em.createQuery("SELECT ae FROM AgendamentoEmail ae", AgendamentoEmail.class).getResultList();
    }

    public void inserir(AgendamentoEmail agendamentoEmail) {
        em.persist(agendamentoEmail);
    }

    public List<AgendamentoEmail> listarPorNaoAgendado() {
        return em.createQuery("SELECT ae FROM AgendamentoEmail ae WHERE ae.agendado = false", AgendamentoEmail.class)
                .getResultList();
    }
    
    public void alterar(AgendamentoEmail agendamentoEmail) {
        em.merge(agendamentoEmail);
    }
}
