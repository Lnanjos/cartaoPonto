package br.org.cartaoponto.domain;

import javax.persistence.Column;
import javax.persistence.Entity;


@SuppressWarnings("serial")
@Entity
public class Setor extends GenericDomain {
	
	@Column(length= 20,nullable = false)
	public String nomeSetor;

	public String getNomeSetor() {
		return nomeSetor;
	}

	public void setNomeSetor(String nomeSetor) {
		this.nomeSetor = nomeSetor;
	}
	
}
