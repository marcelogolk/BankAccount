package ada.tech.lms.screen;
import ada.tech.lms.service.BankService;
import java.util.Scanner;

/**
 * Classe pertencente ao pacote screen, representando a camada View do projeto.
 * Responsável por gerenciar a escolha de opções do usuário no menu do sistema bancário.
 * Ela interpreta a opção selecionada e executa a ação correspondente,
 * coordenando as interações com outras classes da camada view e camada service.
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
	 * Construtor que recebe as dependências necessárias para execução das opções:
	 * o serviço bancário que gerencia operações e o scanner para entrada do usuário.
	 *
	 * @param bankService serviço responsável pelas operações bancárias
	 * @param scanner objeto para leitura de entradas do usuário
	 */
	public OptionService(BankService bankService, Scanner scanner){
		this.bankService = bankService;
		this.scanner = scanner;
	}

	/**
	 * Interpreta a opção selecionada pelo cliente e executa a ação correspondente.
	 * Usa a enum ScreenOptions para determinar qual implementação de ExecutedOption criar,
	 * e então chama seu método execute.
	 *
	 * @param screenOptions a opção selecionada na interface pelo cliente
	 */
	public void chooseOption(ScreenOptions screenOptions){
		ExecutedOption executedOption = null;
		var identifyAccountScreen = new IdentifyAccountScreen(bankService);

		switch (screenOptions){
			case WITHDRAW -> executedOption = new WithdrawExecutedOption(bankService, scanner, identifyAccountScreen.init(scanner));
			case CREATE_ACCOUNT -> executedOption = new CreateAccountExecutedOption(bankService, scanner);
			case DEPOSIT -> executedOption = new DepositExecutedOption(bankService, scanner, identifyAccountScreen.init(scanner));
			case GET_BALANCE -> executedOption = new GetBalanceExecutedOption(bankService, identifyAccountScreen.init(scanner));
			default -> System.exit(0);
		}

		executedOption.execute();
	}
}

