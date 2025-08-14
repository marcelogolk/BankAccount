package ada.tech.lms.screen;

import ada.tech.lms.domain.BankAccount;
import ada.tech.lms.domain.Transaction;
import ada.tech.lms.domain.User;
import ada.tech.lms.service.BankService;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe da camada View que implementa a operação de consulta e exibição do extrato bancário.
 * Exibe as transações da conta ordenadas por data, incluindo saldo inicial,
 * movimentações e saldo atualizado após cada transação.
 * Utiliza o serviço bancário para buscar a conta e obter suas transações.
 *
 * @author Marcelo Guimarães Carvalho
 * @version 1.1
 * @see ada.tech.lms.screen.ExecutedOption
 * @see ada.tech.lms.service.BankService
 * @see ada.tech.lms.domain.BankAccount
 * @see ada.tech.lms.domain.Transaction
 * @see ada.tech.lms.domain.User
 */
public class GetStatementExecutedOption implements ExecutedOption {

    private final BankService bankService;
    private final User user;

    /**
     * Construtor que recebe as dependências necessárias para execução da operação.
     *
     * @param bankService serviço responsável pelas operações bancárias
     * @param user usuário proprietário da conta que terá extrato exibido
     */
    public GetStatementExecutedOption(BankService bankService, User user) {
        this.bankService = bankService;
        this.user = user;
    }

    /**
     * Executa a exibição do extrato bancário.
     * Este método realiza as seguintes ações:
     * <ul>
     *   <li>Obtém a conta bancária associada ao usuário informado.</li>
     *   <li>Recupera a lista de transações da conta.</li>
     *   <li>Cria uma cópia da lista de transações e ordena pelas datas em ordem crescente.</li>
     *   <li>Calcula o saldo inicial, retrocedendo as transações a partir do saldo atual.</li>
     *   <li>Exibe o saldo inicial antes das movimentações.</li>
     *   <li>Itera sobre as transações ordenadas, atualizando e exibindo o saldo após cada operação (depósito ou saque) acompanhando data, tipo, valor e saldo atualizados.</li>
     *   <li>Por fim, exibe o saldo final da conta.</li>
     * </ul>
     */
    @Override
    public void execute() {
        BankAccount account = bankService.findAccountByUser(user);
        List<Transaction> transactions = account.getTransactions();

        List<Transaction> sortedTransactions = new ArrayList<>(transactions);
        Collections.sort(sortedTransactions);
        System.out.println("Nome: " + user.getName() + "      | CPF: " + user.getCpf());
        System.out.println("Extrato da conta " + account.getAccountNumber());
        System.out.println("Data/Hora           | Tipo       | Valor              | Saldo Atual");
        System.out.println("==========================================================================");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        double saldoAtual;

        if (sortedTransactions.isEmpty()) {
            saldoAtual = account.getBalance();
        } else {
            saldoAtual = account.getBalance();
            for (int i = sortedTransactions.size() - 1; i >= 0; i--) {
                Transaction tx = sortedTransactions.get(i);
                if (tx.getType() == Transaction.TransactionType.DEPOSIT) {
                    saldoAtual -= tx.getAmount();
                } else {
                    saldoAtual += tx.getAmount();
                }
            }
        }

        System.out.printf("Saldo inicial:                                        | R$ %15.2f%n", saldoAtual);

        for (Transaction transaction : sortedTransactions) {
            if (transaction.getType() == Transaction.TransactionType.DEPOSIT) {
                saldoAtual += transaction.getAmount();
            } else {
                saldoAtual -= transaction.getAmount();
            }

            System.out.printf("%s | %-10s | R$ %15.2f | R$ %15.2f%n",
                    transaction.getDateTime().format(formatter),
                    transaction.getType().name(),
                    transaction.getAmount(),
                    saldoAtual);
        }

        System.out.println("==========================================================================");
        System.out.printf("Saldo final:                                            R$ %15.2f%n", account.getBalance());
        System.out.println("==========================================================================");
    }
}
