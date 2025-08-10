package ada.tech.lms.screen;
import ada.tech.lms.domain.BankAccount;
import ada.tech.lms.domain.User;
import ada.tech.lms.service.BankService;
import java.util.Scanner;

/**
 * Classe pertencente ao pacote screen, representando a camada View do projeto.
 * Implementa a interface ExecutedOption para executar a ação de saque em uma conta bancária.
 * Solicita ao cliente o valor para saque e realiza a operação na conta associada ao cliente informado.
 *
 * Utiliza o serviço bancário (BankService) para localizar a conta do cliente e atualizar o saldo.
 *
 * @author Matheus Alves Sousa
 * @version 1.0
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
	 * Construtor que recebe as dependências necessárias para a execução do saque:
	 * o serviço bancário responsável por operações, scanner para entrada e o cliente da conta.
	 *
	 * @param bankService serviço bancário para operações
	 * @param scanner scanner para entrada de dados
	 * @param userAccount cliente do banco proprietário da conta
	 */
	public WithdrawExecutedOption(BankService bankService, Scanner scanner, User userAccount) {
		this.bankService = bankService;
		this.userAccount = userAccount;
		this.scanner = scanner;
	}

	/**
	 * Executa a operação de saque.
	 * Solicita o valor do saque ao cliente e realiza a retirada na conta associada ao cliente.
	 */
	@Override
	public void execute() {
		System.out.println("Valor informado para o saque?");
		var value = scanner.nextDouble();
		BankAccount account = bankService.findAccountByUser(userAccount);
		account.getBalance();
		account.withdraw(value);
	}
}