package aula;

import java.util.List;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ControladorPrevisao control = new ControladorPrevisao();
		while(true) {
			String opcao;
			System.out.println("+------------------------------+");
			System.out.println("|Weather                       |");
			System.out.println("+------------------------------+");
			System.out.println("|1 - Previsão dos próximos dias|");
			System.out.println("|2 - Sair                      |");
			System.out.println("+------------------------------+");
			System.out.print("Opção: ");
			opcao = input.nextLine();
			System.out.println("--------------------------------");
			System.out.println();
			if (opcao.equals("1")){
				System.out.print("Digite o nome da cidade: ");
				String strCidade = input.nextLine();
				try {
					System.out.println();
					Cidade cidade = control.getCidade(strCidade);
					if (cidade!=null) {
						System.out.println("Previsão de "+cidade.getNome());
						List<Previsao> previsoes = control.getPrevisao(cidade);
						for (Previsao p : previsoes) 
							System.out.println(p.toString());
					}
					else
						System.out.println("Não existe essa cidade!");
					System.out.println();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if (opcao.equals("2"))	break;
		}
	}

}
