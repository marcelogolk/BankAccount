package ada.tech.lms.screen;

import ada.tech.lms.service.BankService;
import java.util.Scanner;

/**
 * Classe da camada View responsável por gerenciar a escolha de opções do usuário
 * no menu do sistema bancário. Interpreta a opção selecionada e executa a ação correspondente,
 * coordenando as interações com outras classes da camada View e da camada Service.
 *
 * @author Matheus Alves Sousa
 * @version 1.0
 * @see ada.tech.lms.screen.ExecutedOption
 * @see ada.tech.lms.screen.ScreenOptions
 * @see ada.tech.lms.service.BankService
 */
public class OptionService {

	private BankService bankService;
	private Scanner scanner;

	/**
	 * Construtor que recebe as dependências necessárias para a execução das opções:
	 * o serviço bancário que gerencia as operações e o scanner para entrada do usuário.
	 *
	 * @param bankService serviço responsável pelas operações bancárias
	 * @param scanner objeto para leitura de entrada do usuário
	 */
	public OptionService(BankService bankService, Scanner scanner) {
		this.bankService = bankService;
		this.scanner = scanner;
	}

	/**
	 * Interpreta a opção selecionada pelo usuário e cria a instância da ação adequada
	 * para então executar o método correspondente.
	 *
	 * @param screenOptions opção selecionada pelo usuário no menu
	 */
	public void chooseOption(ScreenOptions screenOptions) {
		ExecutedOption executedOption = null;
		var identifyAccountScreen = new IdentifyAccountScreen(bankService);

		switch (screenOptions) {
			case WITHDRAW -> executedOption = new WithdrawExecutedOption(bankService, scanner, identifyAccountScreen.init(scanner));
			case CREATE_ACCOUNT -> executedOption = new CreateAccountExecutedOption(bankService, scanner);
			case DEPOSIT -> executedOption = new DepositExecutedOption(bankService, scanner, identifyAccountScreen.init(scanner));
			case GET_BALANCE -> executedOption = new GetBalanceExecutedOption(bankService, identifyAccountScreen.init(scanner));
			case GET_STATEMENT -> executedOption = new GetStatementExecutedOption(bankService, identifyAccountScreen.init(scanner));
			default -> System.exit(0);
		}

		executedOption.execute();
	}
}
