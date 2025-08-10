package ada.tech.lms.domain;

/**
 * Classe concreta pertencente ao pacote domain, que representa a camada Model do projeto.
 * Define uma conta bancária simples, com regras para saques respeitando o saldo disponível.
 *
 * @author Matheus Alves Sousa
 * @version 1.0
 * @see ada.tech.lms.domain.BankAccount
 */

public class SimpleAccount extends BankAccount {

    /**
     * Construtor que inicializa uma conta simples com número, cliente e saldo.
     *
     * @param accountNumber Número da conta
     * @param owner Cliente do banco proprietário da conta
     * @param balance Saldo inicial da conta
     */
    public SimpleAccount(String accountNumber, User owner, double balance) {
        super(accountNumber, owner, balance);
    }

    /**
     * Realiza o saque na conta se o valor for menor ou igual ao saldo disponível.
     * Lança exceção caso o saldo seja insuficiente.
     *
     * @param amount Valor a ser sacado
     * @throws IllegalArgumentException se o saldo for insuficiente
     */
    @Override
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
    }
}
