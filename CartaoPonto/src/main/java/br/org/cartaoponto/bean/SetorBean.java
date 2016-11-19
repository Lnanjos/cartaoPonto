package br.org.cartaoponto.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.org.cartaoponto.dao.SetorDAO;
import br.org.cartaoponto.domain.Setor;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class SetorBean implements Serializable {
	private Setor setor;

	private List<Setor> setors;

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public List<Setor> getSetors() {
		return setors;
	}

	public void setSetors(List<Setor> setors) {
		this.setors = setors;
	}

	@PostConstruct
	public void listar() {
		try {
			SetorDAO setorDAO = new SetorDAO();
			setors = setorDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as setors");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			// metodo que gera um novo objeto
			setor = new Setor();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma nova setor");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			SetorDAO setorDAO = new SetorDAO();
			setorDAO.salvar(setor);

			setor = new Setor();

			setors = setorDAO.listar();

			Messages.addGlobalInfo("Setor salva com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar uma nova setor");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			setor = (Setor) evento.getComponent().getAttributes()
					.get("setorSelecionada");

			SetorDAO setorDAO = new SetorDAO();
			setorDAO.excluir(setor);

			setors = setorDAO.listar();

			Messages.addGlobalInfo("Setor excluido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {

		try {
			setor = (Setor) evento.getComponent().getAttributes()
					.get("setorSelecionada");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar selecionar uma setor");
			erro.printStackTrace();

		}
	}
}
