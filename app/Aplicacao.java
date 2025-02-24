package app;

import entity.Usuario;
import java.util.List;
import repository.UsuarioRepository;

public class Aplicacao {

	public static void main(String[] args) {
		
		Usuario usuario1 = new Usuario(1, "Rafael", "00000000000");
		Usuario usuario2 = new Usuario(2, "Roberto", "11111111111");
		Usuario usuario3 = new Usuario(3, "Ana", "22222222222");
		Usuario usuario4 = new Usuario(4, "Maria", "33333333333");
		
		UsuarioRepository repository = new UsuarioRepository();
		// Insere usuários
		repository.inserir(usuario1);
		repository.inserir(usuario2);
		repository.inserir(usuario3);
		repository.inserir(usuario4);

		// Atualiza os dados do usuário com id_usuario = 4
		//usuario4.setNome("Mariana");
		//usuario4.setCpf("44444444444");
		//repository.atualizar(usuario4);
		
		// Lista todos os usuários cadastrados no banco
		System.out.println("Listando todos os usuários cadastrados no BD");
		List<Usuario> usuarios = repository.pesquisarTodos();
		for (Usuario usuario : usuarios) {
			System.out.println(usuario.imprimir());
		}
		
		// Lista o usuário pelo id
		
	}
	
}
