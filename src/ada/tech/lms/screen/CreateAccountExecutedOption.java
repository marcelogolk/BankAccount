package ada.tech.lms.screen;
import ada.tech.lms.domain.SimpleAccount;
import ada.tech.lms.domain.User;
import ada.tech.lms.service.BankService;
import java.util.Random;
import java.util.Scanner;

/**
 * Classe pertencente ao pacote screen, representando a camada View do projeto.
 * Implementa a interface ExecutedOption para executar a ação de criação de uma nova conta bancária.
 * Solicita ao cliente a entrada de CPF e nome, gera um número de conta aleatório e
 * cria uma nova conta simples associada a um novo cliente.
 *
 * Responsável por interagir com o usuário e comunicar o serviço bancário (BankService) para adicionar a conta.
 *
 * @author Matheus Alves Sousa
 * @version 1.0
 * @see ada.tech.lms.screen.ExecutedOption
 * @see ada.tech.lms.service.BankService
 * @see ada.tech.lms.domain.SimpleAccount
 * @see ada.tech.lms.domain.User
 */
public class CreateAccountExecutedOption implements ExecutedOption {
	private BankService bankService;
	private Scanner scanner;

	/**
	 * Construtor que recebe as dependências necessárias para a execução da criação da conta:
	 * o serviço bancário responsável pelo gerenciamento das contas e o scanner para entrada do usuário.
	 *
	 * @param bankService serviço bancário para gerenciar contas
	 * @param scanner scanner para entrada de dados do usuário
	 */
	public CreateAccountExecutedOption(BankService bankService, Scanner scanner) {
		this.bankService = bankService;
		this.scanner = scanner;
	}

	/**
	 * Executa a criação de uma nova conta simples.
	 * Solicita CPF e nome do cliente, gera um número de conta aleatório,
	 * cria o objeto User e SimpleAccount, adicionando a conta ao serviço bancário.
	 */
	@Override
	public void execute() {
		System.out.println("Informe o CPF");
		var cpf = scanner.next();
		System.out.println("Informe o Nome do usuário");
		var name = scanner.next();
		var generatedAccountNumber = generateAccountNumber();
		bankService.addAccount(new SimpleAccount(generatedAccountNumber, new User(cpf, name), 0.0));
		System.out.println("Conta criada com sucesso");
	}

	/**
	 * Gera um número de conta aleatório de 6 dígitos para a nova conta.
	 *
	 * @return número da conta gerado como String
	 */
	private String generateAccountNumber() {
		var random = new Random();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			stringBuilder.append(random.nextInt(0, 9));
		}
		return stringBuilder.toString();
	}
}
