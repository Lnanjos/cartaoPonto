package br.org.cartaoponto.dao;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.omnifaces.util.Messages;

import br.org.cartaoponto.domain.Funcionario;
import br.org.cartaoponto.domain.Ponto;
import br.org.cartaoponto.util.HibernateUtil;

public class FuncionarioDAO extends GenericDAO<Funcionario>{
	public Funcionario autenticar(String cpf, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Funcionario.class);
			consulta.add(Restrictions.eq("cpf", cpf));
			consulta.add(Restrictions.eq("codigoFuncionario", senha));
			
			Funcionario resultado = (Funcionario) consulta.uniqueResult();
			
			if(resultado != null){
				Ponto ponto = new Ponto();
				PontoDAO pontoDAO = new PontoDAO();
				
				ponto.setFuncionario(resultado);
				ponto.setData(new Date());
				
				pontoDAO.salvar(ponto);
				Messages.addGlobalInfo("Ponto salvo com sucesso<br>"
						+ ponto.getFuncionario().getNome()+" - "+ ponto.getData());
			}
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}	
}
