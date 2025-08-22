package ada.tech.lms.screen;

import ada.tech.lms.domain.SimpleAccount;
import ada.tech.lms.domain.User;
import ada.tech.lms.persistence.SimpleAccountPersistence;
import ada.tech.lms.persistence.UserPersistence;
import ada.tech.lms.service.BankService;
import java.util.Random;
import java.util.Scanner;

/**
 * Classe da camada View responsável pela criação de uma nova conta bancária simples.
 * Solicita CPF e nome do cliente, gera número de conta aleatório e cria a conta associada.
 *
 * Interage com o BankService para adicionar a conta criada.
 *
 * @author Matheus Alves Sousa
 * @version 1.1
 * @see ada.tech.lms.screen.ExecutedOption
 * @see ada.tech.lms.service.BankService
 * @see ada.tech.lms.domain.SimpleAccount
 * @see ada.tech.lms.domain.User
 */
public class CreateAccountExecutedOption implements ExecutedOption {

		private BankService bankService;
		private Scanner scanner;

		private UserPersistence userPersistence;
		private SimpleAccountPersistence accountPersistence;

		public CreateAccountExecutedOption(BankService bankService, Scanner scanner) {
			this.bankService = bankService;
			this.scanner = scanner;

			// Inicializa as classes de persistência
			this.userPersistence = new UserPersistence();
			this.accountPersistence = new SimpleAccountPersistence();
		}

	/**
	 * Executa a criação da conta:
	 * - Solicita CPF e nome ao usuário
	 * - Gera número aleatório de conta
	 * - Cria um User e SimpleAccount
	 * - Adiciona a conta ao serviço bancário
	 */

	@Override
	public void execute() {
		String cpf = promptForInput("Informe o CPF", "CPF não pode estar em branco, digite apenas números!");
		String name = promptForInput("Informe o Nome do usuário", "Nome não pode estar em branco, digite o nome.");

		try {
			var generatedAccountNumber = generateAccountNumber();
			// Cria usuário e conta
			var user = new User(cpf, name);
			var account = new SimpleAccount(generatedAccountNumber, user, 0.0);
			// Adiciona aos objetos em memória
			bankService.addAccount(account);
			// **Grava no arquivo usuarios.txt**
			userPersistence.add(user);
			// **Grava no arquivo contas.txt**
			accountPersistence.add(account);
			System.out.println("Conta criada com sucesso");
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
		}
	}

		/**
		 * Gera número aleatório de 6 dígitos para a conta.
		 *
		 * @return número gerado como String
		 */
		private String generateAccountNumber () {
			var random = new Random();
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < 6; i++) {
				stringBuilder.append(random.nextInt(0, 9));
			}
			return stringBuilder.toString();
		}
	/**
	 * Solicita uma entrada do usuário e valida se não está em branco.
	 *
	 * @param prompt Mensagem de solicitação.
	 * @param errorMessage Mensagem de erro se a entrada estiver em branco.
	 * @return Entrada válida do usuário.
	 */

	private String promptForInput(String prompt, String errorMessage) {
		while (true) {
			System.out.println(prompt);
			var input = scanner.nextLine().trim();
			if (!input.isEmpty()) {
				return input;
			}
			System.out.println(errorMessage);
		}
	}
	}