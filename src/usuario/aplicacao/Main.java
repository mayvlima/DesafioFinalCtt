package usuario.aplicacao;

import java.sql.SQLException;
import java.util.Scanner;

import usuario.metodos.UsuarioMetodos;
import usuario.model.Usuario;

public class Main {
	public static Scanner sc = new Scanner(System.in);
	public static Scanner scInt = new Scanner(System.in);
	public static Usuario usuario = new Usuario();
	public static UsuarioMetodos usuarioMetodos = new UsuarioMetodos();
	
	public static void main(String[] args) throws SQLException {						
		
		int continuar = 1;		
		
		System.out.println("\nBEM VINDO!");
		do {			
			int r = menu();			
			
			switch (r) {
			case 1:				
				setUsuario();
				usuarioMetodos.save(usuario);
				usuario.setId(usuarioMetodos.getLastId());
				verCadastro();
				
				continuar = continuar("\nDeseja retornar ao menu?", "1 - SIM", "2 - N�O");
				
				break;
				
			case 2:
				int a = continuar("\nO que voc� deseja fazer?","1 - Procurar usu�rio por ID ", "2 - Ver todos os cadastros");
								
				if(a == 1) {
					do {
					System.out.println("\nDigite o ID do registro que deseja visualizar:");
					usuario.setId(scInt.nextInt());
					
					if(usuarioMetodos.getUsuarioWithId(usuario)) {
						System.out.println("\n--------------------------------");
						System.out.println(usuario.toString());
						System.out.println("\n--------------------------------");
					}else {
						System.out.println("\nESCOLHA UM ID V�LIDO!");
					}
					}while(!usuarioMetodos.getUsuarioWithId(usuario));
					
				}else if(a == 2) {
					verTodosCadastros();
				}else {
					System.out.println("Escolha uma op��o dispon�vel!");
				}
				
				continuar = continuar("\nDeseja retornar ao menu?", "1 - SIM", "2 - N�O");
				
				break;
				
			case 3:
				int b = continuar("\nDeseja ver a lista de cadastros antes?", "1 - SIM", "2 - N�O");
				
				if(b == 1){
					verTodosCadastros();
					int c = setIdDesejado();
					
					if(c== 1) {
						setUsuario();						
						usuarioMetodos.update(usuario);			
						verCadastro();
					}else {
						System.out.println("\nAtuliza��o de cadastro cancelada!");
					}
				}else {
					int c = setIdDesejado();					
					if(c== 1) {
						setUsuario();						
						usuarioMetodos.update(usuario);			
						verCadastro();					
					}
				}						
				
				continuar = continuar("\nDeseja retornar ao menu?", "1-SIM", "2-N�O");
				
				break;
				
			case 4:
				
				int d = continuar("\nDeseja ver a lista de cadastro antes?", "1-SIM", "2-N�O");
				
				if(d == 1){
					verTodosCadastros();
					int e  = setIdDesejado();					
						if(e == 1) {							
							usuarioMetodos.delete(usuario);	
						}else {
							System.out.println("\nDeletar cadastro cancelado!");
						}
					
				}else {
					int e  = setIdDesejado();					
					if(e == 1) {					
						usuarioMetodos.delete(usuario);					
					}else {
						System.out.println("\nDeletar cadastro cancelado!");
					}
				}	
				
				continuar = continuar("\nDeseja retornar ao menu?", "1 - SIM", "2 - N�O");
				break;
				
			case 5:
				continuar = 2;
				break;		
			
			}	
			
		}while(continuar == 1);		
	
		sc.close();
		scInt.close();
		
		System.out.println("\nPrograma Finalizado!");		
	
	}
	
	public static int menu() {
		int i;
		do {
			
			System.out.println("\nO que voc� deseja fazer?");
			System.out.println("1 - Cadastrar novo usu�rio");
			System.out.println("2 - Procurar cadastro");
			System.out.println("3 - Atualizar um cadastro");
			System.out.println("4 - Deletar um cadastro");
			System.out.println("5 - Sair");
			i = scInt.nextInt();
			
			if(i!=1 && i!=2 && i!=3 && i!=4 && i!=5) {
				System.out.println("\nESCOLHA UMA OP��O V�LIDA!");
			}
		}while(i!=1 && i!=2 && i!=3 && i!=4 && i!=5);
		
		return i;
	}
	
	public static int continuar(String frase1, String frase2, String frase3) {		
		int i;
		do {
			System.out.println(frase1);
			System.out.println(frase2);
			System.out.println(frase3);
			i = scInt.nextInt();
			if(i!=1 && i!=2) {
				System.out.println("\nESCOLHA UMA OP��O V�LIDA!\n");
			}
		}while(i!=1 && i!=2);
		
		return i;
	}
	
	public static void setUsuario() {
		String nome = "";
		String email = "";
		String senha = "";
		
		do {
		System.out.println("\nDigite o nome: ");
		nome = sc.nextLine();		
		if(nome.trim().equals("")) {
			System.out.println("Digite um nome v�lido!\n");
		}else {
			usuario.setNome(nome);
		}	
		
		}while(nome.trim().equals(""));
		
		
		do {
			System.out.println("\nDigite o email: ");
			email = sc.nextLine();		
			if(email.trim().equals("")) {
				System.out.println("Digite um email v�lido!\n");
			}else {
				usuario.setEmail(email);
			}	
			
			}while(email.trim().equals(""));
		
		
		do {
			System.out.println("\nDigite a senha: ");
			senha = sc.nextLine();		
			if(senha.trim().equals("")) {
				System.out.println("Digite uma senha v�lida!\n");
			}else {
				usuario.setSenha(senha);
			}	
			
			}while(senha.trim().equals(""));		
		
		
		usuario.setDataCadastro(new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));
	}
	
	public static int setIdDesejado() {
		do {
			System.out.println("\nDigite o ID do registro desejado:");
			usuario.setId(scInt.nextInt());
			
			if(usuarioMetodos.getUsuarioWithId(usuario)) {			
			System.out.println("\nEsse � o cadastro desejado: ");
			verCadastro();			
			}else {
				System.out.println("\nESCOLHA UM ID V�LIDO!");
			}
		}while(!usuarioMetodos.getUsuarioWithId(usuario));
		int r = continuar("\nDigite:", "1-SIM", "2-N�O");
		return r;
	}
	
	public static void verTodosCadastros() {
		usuarioMetodos.getUsuarios();
		for(Usuario u : usuarioMetodos.getUsuarios()) {						
			System.out.println(u.toString());
			System.out.println("\n--------------------------------");
		}
	}
	
	public static void verCadastro() {
		usuarioMetodos.getUsuarioWithId(usuario);
		System.out.println("\n--------------------------------");
		System.out.println(usuario.toString());
		System.out.println("\n--------------------------------");
	}
	
}