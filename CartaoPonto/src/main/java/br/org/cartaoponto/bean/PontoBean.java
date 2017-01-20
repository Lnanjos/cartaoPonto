package br.org.cartaoponto.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.org.cartaoponto.dao.FuncionarioDAO;
import br.org.cartaoponto.dao.PontoDAO;
import br.org.cartaoponto.domain.Funcionario;
import br.org.cartaoponto.domain.Ponto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PontoBean implements Serializable{
	
	private Ponto ponto = new Ponto();

	private List<Ponto> pontos;
	
	private List<Funcionario> funcionarios;
	
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Ponto getPonto() {
		return ponto;
	}

	public void setPonto(Ponto ponto) {
		this.ponto = ponto;
	}

	public List<Ponto> getPontos() {
		return pontos;
	}

	public void setPontos(List<Ponto> pontos) {
		this.pontos = pontos;
	}

	@PostConstruct
	public void listar() {
		try {
			PontoDAO pontoDAO = new PontoDAO();
			pontos = pontoDAO.listar();
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as pontos");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			// metodo que gera um novo objeto
			ponto = new Ponto();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma nova ponto");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			System.out.println(ponto.getData());
			PontoDAO pontoDAO = new PontoDAO();
			pontoDAO.salvar(ponto);

			Messages.addGlobalInfo("Ponto salvo com sucesso"
					+ ponto.getFuncionario().getNome()+" - "+ ponto.getData());
			
			
			ponto = new Ponto();

			pontos = pontoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar uma nova ponto");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			//ponto = (Ponto) evento.getComponent().getAttributes()
				//	.get("pontoSelecionada");

			PontoDAO pontoDAO = new PontoDAO();
			pontoDAO.excluir(ponto);

			pontos = pontoDAO.listar();

			Messages.addGlobalInfo("Ponto excluido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {

		try {
			//ponto = (Ponto) evento.getComponent().getAttributes()
				//	.get("pontoSelecionada");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar selecionar uma ponto");
			erro.printStackTrace();

		}
	}

}
