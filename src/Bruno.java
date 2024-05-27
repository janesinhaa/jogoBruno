
import java.util.Random;
import java.util.Scanner;

public class Bruno { 
	
	static final int Posicão_máxima = 50;
	
	static Random rand = new Random(); 
	static Scanner scan = new Scanner(System.in); 
	
	static int jogador1posicão= 0; 
	static int jogador2posicão = 0; 
	
	static String jogador1nome = ""; 
	static String jogador2nome = ""; 
	
	
			
	public static void main(String[] args) { 
			menu(); 
		} 
	
	public static void menu() { 
		System.out.println("\n   ___               _       _   _            __             _        \r\n"
				+ "  / __\\ (_)_ __ ___ | |__   | |_| |__   ___  / _\\_ __   __ _| | _____ \r\n"
				+ " / /  | | | '_ ` _ \\| '_ \\  | __| '_ \\ / _ \\ \\ \\| '_ \\ / _` | |/ / _ \\\r\n"
				+ "/ /___| | | | | | | | |_) | | |_| | | |  __/ _\\ \\ | | | (_| |   <  __/\r\n"
				+ "\\____/|_|_|_| |_| |_|_.__/   \\__|_| |_|\\___| \\__/_| |_|\\__,_|_|\\_\\___| ");
		
		System.out.println("\n\nOlá jogadores! "); 
		System.out.println("Estão prontos para jogar Climb The Snake? "); 
		
		System.out.println("1. Jogar"); 
		System.out.println("2. Instruções"); 
		System.out.println("3. Sair"); 
		System.out.print("Escolha sua opção: "); 
		
		int escolhaUsuario = scan.nextInt(); 
		
		switch (escolhaUsuario) { 
		case 1-> iniciarJogo(); 
		case 2 -> mostrarInstrucoes(); 
		case 3 -> {System.out.println("Obrigado por jogar até a próxima!"); 
		break;
		}
		default -> System.out.println("Opção inválida. Tente novamente.");
		}
		menu();
		}
		
	
	
		public static void mostrarInstrucoes() {
			
			System.out.println("\nInstruções do Jogo: "); 
			System.out.println("\nNo jogo Climb the Snake, o objetivo é que um dos jogadores"); 
			System.out.println("chegue na posição 50 do tabuleiro, durante o percurso, "); 
			System.out.println("existem escadas que te ajudam e cobras que te atrapalham. "); 
			System.out.println("Uma escada sobe 10 casas e uma cobra te faz cair 5 casas. "); 
			System.out.println("O jogador que chegar primeiro vence o jogo! ");
			System.out.println("\nPressione qualquer tecla para voltar ao menu!"); 
			
			
			menu(); 
			} 
		
		public static void iniciarJogo() { 
			System.out.println("Jogo iniciado!"); 
			jogar(); 
			} 
		
		public static void jogar() { 
			System.out.println("Informe o nome dos dois jogadores"); 
			jogador1nome = scan.next(); 
			jogador2nome= scan.next(); 
			
			while (jogador1posicão < Posicão_máxima && jogador2posicão < Posicão_máxima) { 
				int rodar_dado1 = JogueDado(); 
				jogador1posicão= atualizar_posição(jogador1posicão, rodar_dado1, jogador1nome); 
				int rodar_dado2 = JogueDado();
				
				jogador2posicão = atualizar_posição(jogador2posicão, rodar_dado2, jogador2nome); 
				exibirTabuleiro(); 
				} 
			if (jogador1posicão >= Posicão_máxima) {
				System.out.println(jogador1nome+ " você venceu!"); 
				} 
			else { 
				System.out.println(jogador2nome + " você venceu!"); 
				} 
			} 
		
		public static int JogueDado() { 
			scan.nextLine(); 	
			return rand.nextInt(1, 6); 
			} 
		
		private static int atualizar_posição(int atualPosição, int rolarDado, String nomeJogador) {
			int nova_posição = atualPosição + rolarDado; 
			if (nova_posição >= Posicão_máxima) { 
				return nova_posição; 
				} 
			else { 
				nova_posição = checarAsCobras_Escadas(nova_posição, nomeJogador, atualPosição); 
				} 
			System.out.println(nomeJogador + " está na posição " + nova_posição); 
			System.out.println(nomeJogador + ", ande " + rolarDado + " casas.");
			return nova_posição; 
			    } 
		
		private static int checarAsCobras_Escadas(int nova_posição, String nome_jogador, int atualPosição) { 
			int[][] escadas = { { 5, 15 }, { 15, 25 }, { 25, 35 }, { 35, 45 } }; 
			int[][] cobras = { { 10, 5 }, { 20, 10 }, { 30, 20 }, { 40, 30 } }; 
			
			for (int[] escada : escadas) { 
				if (escada[0] == nova_posição) { 
					nova_posição += 10;
					System.out.print(nome_jogador + " Você subiu em uma escada, avance dez casas!"); 
					System.out.println(); 
					break; 
					} 
				} 
			for (int[] cobra : cobras) { 
				if (cobra[0] == nova_posição) { 
					nova_posição -= 5;
					System.out.print(nome_jogador + " Você encontrou uma cobra!, desça cinco casas!"); 
					System.out.println(); 
					break; 
					} 
				} 
			return nova_posição; 
			} 
		
		public static void exibirTabuleiro() { 
			System.out.printf(""+
					"	        Y    			\n" +
					"	     __ | __			\n" +
					"	    |       |			\n" +
					"	   |         |			\n" +
					"	  |           |			\n" +
					"	 | []       [] |		\n" +
					"	 |             | 		\n" +
					"	  |           |			\n" +
					"	   |         |   		\n" +
					"	   |--------|			\n" +
					"	   |    %s   |			\n" +
					"	   |--------|			\n" +
					"	   |    %s   |			\n" +
					"	   |---------|			\n" +
					"	  |     %s    |    		\n" +
					"	 |-------------|   		\n" +
					"	|       %s      |		\n" +
					"	|-----------------|		\n" +
					"	|       %s         |	\n" +
					"	|--------------------|_____________________________________________________________________________		\n" +
					"	|       %s                                                                                          |  	\n" +
					"	|                       |      |      |      |      |      |      |      |      |      |           |    \n" +
					"	|       %s            %s  |   %s  |   %s |  %s  |  %s  |   %s |  %s  |   %s |   %s |  %s  |	   |    \n" +
					"	|             -------------------------------------------------------------------------------------|	\n" +
					"	|                       |       |      |      |      |      |      |      |      |      |	   |    \n" +
					"	|       %s          %s  |   %s  |   %s |  %s  |  %s  |   %s |  %s  |   %s |   %s |  %s  |	   |    \n" +
					"	|             -------------------------------------------------------------------------------------|	\n" +
					"	|                       |       |      |      |      |      |      |      |      |      |  	   |    \n" +
					"	|       %s          %s  |   %s  |   %s |  %s  |  %s  |   %s |  %s  |   %s |   %s |  %s  |          |    \n" +
					"	|             -------------------------------------------------------------------------------------|-------------	    \n" +
					"	|                       |       |      |      |      |      |      |      |      |      |               ----------------		\n" +
					"	|       %s          %s  |   %s  |   %s |  %s  |  %s  |   %s |  %s  |   %s |   %s |  %s  |           "                    
					                                                       + "    ----------------  \n" +
					"	|__________________________________________________________________________________________________|--------------    		\n",verPosicao());
			} 
		
		public static Object[] verPosicao() { 
			String[] valores = new String[50]; 
			
			for (int i = 0; i < valores.length; i++) { 
				String c = String.valueOf(i+1); 
				if (jogador1posicão == i+1 && jogador2posicão == i+1) c = "\033[031m" + jogador1nome.substring(0, 1) + "\033[0m" + "\033[031m" + jogador2nome.substring(0, 1) + "\033[0m"; 
				else if (jogador1posicão == i+1) c = "\033[031m" + jogador1nome.substring(0, 1) + "\033[0m"; 
				else if (jogador2posicão == i+1) c = "\033[031m" + jogador2nome.substring(0, 1) + "\033[0m"; 
				valores[i] = c; 
				} 
			return valores; 
			} 
		
 
	
	}