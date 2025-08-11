package ada.tech.lms.domain;

/**
 * Representa uma conta bancária simples do sistema,
 * que permite saques dentro do limite do saldo disponível,
 * sem limites especiais.
 *
 * Estende a classe abstrata BankAccount e implementa a lógica específica de saque.
 *
 * @author Matheus Alves Sousa
 * @version 1.0
 * @see ada.tech.lms.domain.BankAccount
 */
public class SimpleAccount extends BankAccount {

    /**
     * Constrói uma conta simples com o número da conta, proprietário e saldo inicial.
     *
     * @param accountNumber Número único da conta bancária
     * @param owner Cliente proprietário da conta
     * @param balance Saldo inicial da conta, valor pode ser zero ou positivo
     */
    public SimpleAccount(String accountNumber, User owner, double balance) {
        super(accountNumber, owner, balance);
    }

    /**
     * Realiza o saque na conta simples.
     *
     * O valor do saque deve ser positivo e menor ou igual ao saldo disponível.
     * Caso o saldo seja insuficiente, lança exceção.
     *
     * Também registra a transação em caso de sucesso.
     *
     * @param amount Valor a ser sacado, maior que zero
     * @throws IllegalArgumentException se o valor for inválido ou saldo insuficiente
     */
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser positivo.");
        }
        if (amount <= balance) {
            balance -= amount;
            transactions.add(new Transaction(Transaction.TransactionType.WITHDRAW, amount));
        } else {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
    }
}
