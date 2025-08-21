package ada.tech.lms.screen;

import ada.tech.lms.domain.User;
import ada.tech.lms.service.BankService;
import java.util.Scanner;

/**
 * Classe da camada View responsável por executar a operação de depósito em conta bancária.
 * Solicita ao cliente o valor do depósito e realiza a atualização do saldo na conta associada.
 *
 * Utiliza o serviço bancário (BankService) para localizar a conta correta e executar o depósito.
 *
 * @author Matheus Alves Sousa
 * @version 1.1
 * @see ada.tech.lms.screen.ExecutedOption
 * @see ada.tech.lms.service.BankService
 * @see ada.tech.lms.domain.User
 */
public class DepositExecutedOption implements ExecutedOption {
	private final BankService bankService;
	private final Scanner scanner;
	private final User user;

	/**
	 * Construtor que recebe as dependências necessárias para processar o depósito.
	 *
	 * @param bankService serviço bancário responsável pelas operações
	 * @param scanner scanner para leitura dos dados do usuário
	 * @param user cliente que realizará o depósito
	 */
	public DepositExecutedOption(BankService bankService, Scanner scanner, User user) {
		this.bankService = bankService;
		this.scanner = scanner;
		this.user = user;
	}

	/**
	 * Executa a operação de depósito:
	 * - Solicita o valor do depósito ao usuário
	 * - Realiza o depósito na conta associada ao usuário informado
	 */
	@Override
	public void execute() {
		System.out.println("Qual o valor que deseja depositar?");
		var value = scanner.nextDouble();
		scanner.nextLine();
		bankService.findAccountByUser(user).deposit(value);
		System.out.println("Depósito realizado com sucesso");
	}
}
