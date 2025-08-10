package ada.tech.lms.screen;

import ada.tech.lms.domain.BankAccount;
import ada.tech.lms.domain.User;
import ada.tech.lms.service.BankService;

import java.util.Scanner;

/**
 * Classe pertencente ao pacote screen, representando a camada View do projeto.
 * Responsável pela interação inicial com o cliente para identificação da conta bancária.
 * Solicita o CPF do cliente e utiliza o serviço bancário para localizar o usuário associado.
 *
 * Atua como uma tela de entrada para autenticação ou seleção de cliente durante operações.
 *
 * @author Matheus Alves Sousa
 * @version 1.0
 * @see ada.tech.lms.service.BankService
 * @see ada.tech.lms.domain.User
 */
public class IdentifyAccountScreen {
	private BankService bankService;

	/**
	 * Construtor que recebe o serviço bancário necessário para buscar usuários.
	 *
	 * @param bankService serviço bancário para operações relacionadas ao usuário
	 */
	public IdentifyAccountScreen(BankService bankService){
		this.bankService = bankService;
	}

	/**
	 * Inicializa a identificação do cliente solicitando CPF via entrada padrão.
	 * Retorna o objeto User associado ao CPF informado.
	 *
	 * @param scanner Scanner para leitura de entrada do cliente
	 * @return usuário associado ao CPF informado
	 */
	public User init(Scanner scanner){
		System.out.println("Informe o cpf da sua conta");
		return bankService.findUser(scanner.next());
	}

	public BankAccount AccountInit(Scanner scanner){
		System.out.println("Informe o cpf da sua conta");
		return bankService.findAccount(scanner.next());
	}
}

