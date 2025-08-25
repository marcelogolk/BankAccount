package ada.tech.lms.screen;

import ada.tech.lms.domain.BankAccount;
import ada.tech.lms.domain.User;
import ada.tech.lms.service.BankService;

/**
 * Classe da camada View responsável por executar a consulta de saldo
 * em uma conta bancária associada a um cliente.
 * Utiliza o serviço bancário para localizar a conta do cliente e exibir o saldo atual.
 *
 * @author Matheus Alves Sousa
 * @version 1.1
 * @see ada.tech.lms.screen.ExecutedOption
 * @see ada.tech.lms.service.BankService
 * @see ada.tech.lms.domain.User
 * @see ada.tech.lms.domain.BankAccount
 */
public class GetBalanceExecutedOption implements ExecutedOption {
	private final BankService bankService;
	private final User user;

	/**
	 * Construtor que recebe as dependências necessárias para execução da consulta.
	 *
	 * @param bankService serviço bancário para operações
	 * @param user cliente cuja conta será consultada
	 */
	public GetBalanceExecutedOption(BankService bankService, User user) {
		this.bankService = bankService;
		this.user = user;
	}

	/**
	 * Executa a consulta de saldo da conta associada ao cliente.
	 * Exibe o número da conta e o saldo formatado com duas casas decimais.
	 */
	@Override
	public void execute() {
		bankService.findAccountByUser(user)
				.ifPresentOrElse(
						account -> {
							System.out.println("Nome: " + user.getName() + "    |  CPF: " + user.getCpf());
							System.out.printf("Saldo disponível da conta %s: R$ %.2f %n",
									account.getAccountNumber(),
									account.getBalance());
						},
						() -> System.out.println("Conta não encontrada para o cliente informado.")
				);
	}
}
