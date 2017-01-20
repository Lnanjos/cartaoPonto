package br.org.cartaoponto.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.org.cartaoponto.dao.AdministradorDAO;
import br.org.cartaoponto.dao.FuncionarioDAO;
import br.org.cartaoponto.domain.Administrador;
import br.org.cartaoponto.domain.Funcionario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AdministradorBean implements Serializable {

	private Administrador administrador;

	private Administrador administradorLogado;

	private List<Administrador> administradores;

	private List<Funcionario> funcionarios;

	private String link = "";

	public Administrador getAdministradorLogado() {
		return administradorLogado;
	}

	public void setAdministradorLogado(Administrador administradorLogado) {
		this.administradorLogado = administradorLogado;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public List<Administrador> getAdministradores() {
		return administradores;
	}

	public void setAdministradores(List<Administrador> administradores) {
		this.administradores = administradores;
	}

	@PostConstruct
	public void listar() {
		try {
			administrador = new Administrador();

			AdministradorDAO administradorDAO = new AdministradorDAO();
			administradores = administradorDAO.listar();

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as administradores");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			// metodo que gera um novo objeto
			administrador = new Administrador();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma nova administrador");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			AdministradorDAO administradorDAO = new AdministradorDAO();
			administradorDAO.salvar(administrador);

			administrador = new Administrador();

			administradores = administradorDAO.listar();

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();

			Messages.addGlobalInfo("Administrador salva com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar uma nova administrador obs: não é possivel dois adm com mesmo login");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			// administrador = (Administrador)
			// evento.getComponent().getAttributes()
			// .get("administradorSelecionada");

			AdministradorDAO administradorDAO = new AdministradorDAO();
			administradorDAO.excluir(administrador);

			administradores = administradorDAO.listar();

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();

			Messages.addGlobalInfo("Administrador excluido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {

		try {
			// administrador = (Administrador)
			// evento.getComponent().getAttributes()
			// .get("administradorSelecionada");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar selecionar uma administrador");
			erro.printStackTrace();

		}
	}

	public void autenticar(ActionEvent evento) {
		try {
			AdministradorDAO administradorDAO = new AdministradorDAO();
			// administradorLogado recebe o resultado do metodo que esta no
			// administrador
			// DAO
			administradorLogado = administradorDAO.autenticar(
					administrador.getLogin(), administrador.getSenha());

			if (administradorLogado == null) {
				Messages.addGlobalError("login e/ou senha incorretos");
				return;
			}

			System.out.println("link: " + link);

			Faces.redirect("./pages/" + link + "/administrador.xhtml");
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void autenticarPrime(ActionEvent event) {
		link = (String) event.getComponent().getAttributes().get("link");
		autenticar(event);
	}

}
