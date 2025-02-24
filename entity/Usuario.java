package entity;

/**
 * Classe que representa a entidade de usuário (TB_Usuario)
 */
public class Usuario {

	private Integer id;
	private String cpf;
	private String nome;

	public Usuario() {}
	
	public Usuario(Integer id, String nome, String cpf) {
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String imprimir() {
		return String.format("[Usuário #%d, CPF: %s, Nome: %s]", this.id, this.cpf, this.nome);
	}

}
