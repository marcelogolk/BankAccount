package ada.tech.lms.screen;
import ada.tech.lms.domain.User;
import ada.tech.lms.service.BankService;
import java.util.Scanner;

/**
 * Classe pertencente ao pacote screen, representando a camada View do projeto.
 * Implementa a interface ExecutedOption para executar a ação de depósito em uma conta bancária.
 * Solicita ao cliente o valor a ser depositado e realiza o depósito na conta associada ao cliente informado.
 *
 * Utiliza o serviço bancário (BankService) para localizar a conta do cliente e atualizar o saldo.
 *
 * @author Matheus Alves Sousa
 * @version 1.0
 * @see ada.tech.lms.screen.ExecutedOption
 * @see ada.tech.lms.service.BankService
 * @see ada.tech.lms.domain.User
 */
public class DepositExecutedOption implements ExecutedOption {
	private final BankService bankService;
	private final Scanner scanner;
	private final User user;

	/**
	 * Construtor que recebe as dependências necessárias para a execução do depósito:
	 * o serviço bancário, o scanner para entrada de dados e o usuário cliente da conta.
	 *
	 * @param bankService serviço bancário para operações
	 * @param scanner scanner para entrada
	 * @param user cliente do banco que realizará o depósito
	 */
	public DepositExecutedOption(BankService bankService, Scanner scanner, User user) {
		this.bankService = bankService;
		this.scanner = scanner;
		this.user = user;
	}

	/**
	 * Executa a ação de depósito solicitando ao cliente o valor e adicionando à conta associada ao usuário.
	 */
	@Override
	public void execute() {
		System.out.println("Qual o valor que deseja depositar?");
		var value = scanner.nextDouble();
		bankService.findAccountByUser(user).deposit(value);
		System.out.println("deposito realizado com sucesso");
	}
}

