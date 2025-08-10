package ada.tech.lms;
import ada.tech.lms.screen.OptionService;
import ada.tech.lms.screen.ScreenOptions;
import ada.tech.lms.service.BankService;
import java.util.Scanner;

/**
 * Classe pertencente ao pacote principal do projeto.
 * Responsável por iniciar a aplicação bancária, exibindo opções ao usuário
 * e controlando o fluxo da interação via terminal.
 * Atua como ponto de entrada da aplicação, utilizando as classes das camadas
 * service e screen para gerenciar as operações e exibição.
 *
 * @author Matheus Alves Sousa
 * @version 1.0
 * @see ada.tech.lms.service.BankService
 * @see ada.tech.lms.screen.OptionService
 * @see ada.tech.lms.screen.ScreenOptions
 */
public class Main {
	/**
	 * Método principal que inicia a aplicação.
	 * Cria um Scanner para leitura de entradas do usuário,
	 * exibe mensagem de boas-vindas e chama o método para mostrar as opções.
	 *
	 * @param args argumentos de linha de comando (não usados)
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Bem vindo ao banco");
		showDisplayOptions(scanner);
	}

	/**
	 * Método privado que exibe o menu de opções para o usuário em loop.
	 * Cria as instâncias dos serviços necessários e processa as escolhas usando a OptionService.
	 * O loop continua até que o usuário escolha a opção de sair (opção 0).
	 *
	 * @param scanner Scanner para leitura de entrada do usuário
	 */
	private static void showDisplayOptions(Scanner scanner) {
		int option = 0;
		BankService bankService = new BankService();
		OptionService optionService = new OptionService(bankService, scanner);

		do {
			for (ScreenOptions screenOption : ScreenOptions.values()) {
				System.out.println(String.format("%d - %s",
						screenOption.getOption(), screenOption.getOptionDescription()));
			}
			option = scanner.nextInt();
			optionService.chooseOption(ScreenOptions.getScreenOption(option));
		} while (option != 0);
	}
}

