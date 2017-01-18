package br.org.cartaoponto.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;
import br.org.cartaoponto.dao.FuncionarioDAO;
import br.org.cartaoponto.domain.Funcionario;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PontoFuncionarioBean implements Serializable{
	//Esta classe é dedicada a salvar o ponto realizado pelo funcionario 
	//atraves de uso do cpf e senha na tela pontoFuncionario.xhtml
	
	
	private Funcionario funcionario = new Funcionario();
	private Funcionario funcionarioLogado = new Funcionario();

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Funcionario getFuncionarioLogado() {
		return funcionarioLogado;
	}

	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}

	public void autenticar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioLogado = funcionarioDAO.autenticar(funcionario.getCpf(),
					funcionario.getCodigoFuncionario());

			if (funcionarioLogado == null) {
				Messages.addGlobalError("CPF e/ou senha incorretos");
				return;
			}
				
		} catch (Exception erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
	}
	
}
