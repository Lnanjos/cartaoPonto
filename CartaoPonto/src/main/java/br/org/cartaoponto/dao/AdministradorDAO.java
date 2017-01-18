package br.org.cartaoponto.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import br.org.cartaoponto.domain.Administrador;
import br.org.cartaoponto.util.HibernateUtil;

public class AdministradorDAO extends GenericDAO<Administrador>{

	public Administrador autenticar(String login, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Administrador.class);
			consulta.add(Restrictions.eq("login", login));
			consulta.add(Restrictions.eq("codigoAdministrador", senha));
			
			Administrador resultado = (Administrador) consulta.uniqueResult();
			
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
