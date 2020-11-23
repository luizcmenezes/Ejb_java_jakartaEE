package br.com.alura.doa;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import br.com.alura.entidade.AgendamentoEmail;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class AgendamentoEmailDAO {

    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction userTransaction;

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
        try {
            userTransaction.begin();
            em.merge(agendamentoEmail);
            userTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
