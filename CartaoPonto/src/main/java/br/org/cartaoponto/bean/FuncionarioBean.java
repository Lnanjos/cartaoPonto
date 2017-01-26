package br.org.cartaoponto.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.org.cartaoponto.dao.FuncaoDAO;
import br.org.cartaoponto.dao.FuncionarioDAO;
import br.org.cartaoponto.dao.SetorDAO;
import br.org.cartaoponto.domain.Funcao;
import br.org.cartaoponto.domain.Funcionario;
import br.org.cartaoponto.domain.Setor;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FuncionarioBean implements Serializable {
	private Funcionario funcionario = new Funcionario();

	private List<Funcionario> funcionarios;
	private List<Funcao> funcoes;
	private List<Setor> setores;
	
	public List<Funcao> getFuncoes() {
		return funcoes;
	}

	public void setFuncoes(List<Funcao> funcoes) {
		this.funcoes = funcoes;
	}

	public List<Setor> getSetores() {
		return setores;
	}

	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Funcao> getFuncaos() {
		return funcoes;
	}

	public void setFuncaos(List<Funcao> funcoes) {
		this.funcoes = funcoes;
	}

	@PostConstruct
	public void listar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();
			
			SetorDAO setorDAO = new SetorDAO();
			setores = setorDAO.listar();
			
			FuncaoDAO funcaoDAO = new FuncaoDAO();
			funcoes = funcaoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as funcionarios");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			funcionario = new Funcionario();

			FuncaoDAO funcaoDAO = new FuncaoDAO();
			funcoes = funcaoDAO.listar();
			
			SetorDAO setorDAO = new SetorDAO();
			setores = setorDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma nova funcionario");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.salvar(funcionario);
			funcionario = new Funcionario();

			FuncaoDAO funcaoDAO = new FuncaoDAO();
			funcoes = funcaoDAO.listar();
			
			SetorDAO setorDAO = new SetorDAO();
			setores = setorDAO.listar();

			funcionarios = funcionarioDAO.listar();

			Messages.addGlobalInfo("Funcionario salva com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar uma nova funcionario");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			//funcionario = (Funcionario) evento.getComponent().getAttributes()
				//	.get("funcionarioSelecionada");

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.excluir(funcionario);

			funcionarios = funcionarioDAO.listar();

			Messages.addGlobalInfo("Funcionario excluido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {

		try {
			//funcionario = (Funcionario) evento.getComponent().getAttributes()
				//	.get("funcionarioSelecionada");

			FuncaoDAO funcaoDAO = new FuncaoDAO();
			funcoes = funcaoDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar selecionar uma funcionario");
			erro.printStackTrace();

		}
	}
}