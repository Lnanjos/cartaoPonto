package br.org.cartaoponto.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import org.richfaces.model.Filter;
import br.org.cartaoponto.dao.FuncaoDAO;
import br.org.cartaoponto.domain.Funcao;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FuncaoBean implements Serializable{
	private Funcao funcao = new Funcao();
	
	private List<Funcao> funcoes;
	
	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public List<Funcao> getFuncoes() {
		return funcoes;
	}

	public void setFuncoes(List<Funcao> funcoes) {
		this.funcoes = funcoes;
	}

	@PostConstruct
	public void listar() {
		try {
			FuncaoDAO funcaoDAO = new FuncaoDAO();
			funcoes = funcaoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as funcoes");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			// metodo que gera um novo objeto
			System.out.println("novo?");
			funcao = new Funcao();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma nova funcao");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			System.out.println(funcao.getNomeFuncao());
			FuncaoDAO funcaoDAO = new FuncaoDAO();
			funcaoDAO.salvar(funcao);

			funcao = new Funcao();

			funcoes = funcaoDAO.listar();

			Messages.addGlobalInfo("Funcao salva com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar uma nova funcao");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			System.out.println("excluir: "+funcao.getNomeFuncao()+" - "+funcao.getCodigo());
			//funcao = (Funcao) evento.getComponent().getAttributes()
				//	.get("funcaoSelecionada");
			
			System.out.println("excluir: "+funcao.getNomeFuncao()+" - "+funcao.getCodigo());
			
			FuncaoDAO funcaoDAO = new FuncaoDAO();
			funcaoDAO.excluir(funcao);

			funcoes = funcaoDAO.listar();

			Messages.addGlobalInfo("Funcao excluido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {

		try {
			System.out.println("editar: "+funcao.getNomeFuncao()+" - "+funcao.getCodigo());
			//funcao = (Funcao) evento.getComponent().getAttributes()
				//	.get("funcaoSelecionada");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar selecionar uma funcao");
			erro.printStackTrace();

		}
	}
	
	public Filter<?> getNomeFuncaoFilterImpl() {
        return new Filter<Funcao>() {
            public boolean accept(Funcao funcao) {
            	String nomeFuncao = getFuncao().nomeFuncao;
                if (nomeFuncao == null || nomeFuncao.length() == 0 || nomeFuncao.equals(funcao.getNomeFuncao())) {
                    return true;
                }
                return false;
            }
			
        };
    }

}
