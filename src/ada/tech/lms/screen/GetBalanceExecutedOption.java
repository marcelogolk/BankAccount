package ada.tech.lms.screen;
import ada.tech.lms.domain.BankAccount;
import ada.tech.lms.domain.User;
import ada.tech.lms.service.BankService;

/**
 * Classe pertencente ao pacote screen, representando a camada View do projeto.
 * Implementa a interface ExecutedOption para executar a ação de consulta de saldo de uma conta bancária.
 * Busca a conta associada ao cliente informado e exibe o saldo disponível.
 *
 * Utiliza o serviço bancário (BankService) para localizar a conta do cliente.
 *
 * @author Matheus Alves Sousa
 * @version 1.0
 * @see ada.tech.lms.screen.ExecutedOption
 * @see ada.tech.lms.service.BankService
 * @see ada.tech.lms.domain.User
 * @see ada.tech.lms.domain.BankAccount
 */
public class GetBalanceExecutedOption implements ExecutedOption {
	private final BankService bankService;
	private final User user;

	/**
	 * Construtor que recebe as dependências necessárias para execução da consulta:
	 * o serviço bancário e o cliente cuja conta será consultada.
	 *
	 * @param bankService serviço bancário para operações
	 * @param user cliente do banco para consulta de saldo
	 */
	public GetBalanceExecutedOption(BankService bankService, User user) {
		this.bankService = bankService;
		this.user = user;
	}

	/**
	 * Executa a ação de consulta do saldo da conta associada ao cliente.
	 * Exibe o número da conta e o saldo formatado.
	 */
	@Override
	public void execute() {
		BankAccount account = bankService.findAccountByUser(user);
		System.out.printf("Saldo na conta %s é de %.2f %n", account.getAccountNumber(),
				account.getBalance());
	}
}

