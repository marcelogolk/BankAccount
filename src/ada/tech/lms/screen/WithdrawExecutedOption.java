package ada.tech.lms.screen;

import ada.tech.lms.domain.BankAccount;
import ada.tech.lms.domain.User;
import ada.tech.lms.service.BankService;
import java.util.Scanner;

/**
 * Classe da camada View responsável por executar a operação de saque em uma conta bancária.
 * Solicita ao cliente o valor a ser sacado e realiza a retirada na conta associada.
 *
 * Utiliza o serviço bancário para localizar a conta do cliente e atualizar o saldo.
 *
 * @author Matheus Alves Sousa
 * @version 1.1
 * @see ada.tech.lms.screen.ExecutedOption
 * @see ada.tech.lms.service.BankService
 * @see ada.tech.lms.domain.User
 * @see ada.tech.lms.domain.BankAccount
 */
public class WithdrawExecutedOption implements ExecutedOption {

	private BankService bankService;
	private Scanner scanner;
	private User userAccount;

	/**
	 * Construtor que recebe as dependências necessárias para execução do saque.
	 *
	 * @param bankService serviço bancário para operações
	 * @param scanner scanner para leitura dos dados do usuário
	 * @param userAccount cliente proprietário da conta
	 */
	public WithdrawExecutedOption(BankService bankService, Scanner scanner, User userAccount) {
		this.bankService = bankService;
		this.userAccount = userAccount;
		this.scanner = scanner;
	}

	/**
	 * Executa a operação de saque:
	 * solicita o valor ao usuário, localiza a conta e realiza o saque.
	 */
	@Override
	public void execute() {
		System.out.println("Valor informado para o saque?");
		var value = scanner.nextDouble();
		scanner.nextLine();
		BankAccount account = bankService.findAccountByUser(userAccount);
		account.withdraw(value);
	}
}
