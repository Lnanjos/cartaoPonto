package br.org.cartaoponto.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.org.cartaoponto.domain.Administrador;
import br.org.cartaoponto.util.HibernateUtil;

public class AdministradorDAO extends GenericDAO<Administrador>{

	public Administrador autenticar(String login, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Administrador.class);
			consulta.add(Restrictions.eq("login", login));
			consulta.add(Restrictions.eq("senha", senha));
			
			Administrador resultado = (Administrador) consulta.uniqueResult();
			
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	
	@Override
	public void salvar(Administrador administrador){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		try {
			Criteria consulta = sessao.createCriteria(Administrador.class);
			consulta.add(Restrictions.eq("login", administrador.getLogin()));
			
			Administrador resultado = (Administrador) consulta.uniqueResult();
			
			if (resultado == null) {
				
				// inicia a transacao
				transacao = sessao.beginTransaction();
				// salva o registro no banco
				//sessao.save(entidade);
				// função do merge: se o objeto nao existe no banco ele vai salvar um novo, se ele ja existe ele vai atualizar
				sessao.merge(administrador);
				// commit encerra a transacao
				transacao.commit();
			}else {
				throw new RuntimeException();
			}
			
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
