package ada.tech.lms.domain;
/**
 * Classe abstrata pertencente ao pacote domain, que representa a camada Model do projeto.
 * Representa a conta bancária do cliente, com número, proprietário (cliente) e saldo,
 * definindo operações básicas como depósito e saque (abstrato).
 *
 * @author Matheus Alves Sousa
 * @version 1.0
 * @see ada.tech.lms.domain.SimpleAccount
 * @see ada.tech.lms.domain.SpecialAccount
 */

public abstract class BankAccount {
    /** Número que identifica unicamente a conta bancária */
    protected String accountNumber;

    /** Cliente proprietário da conta */
    protected User owner;

    /** Saldo disponível na conta */
    protected double balance;

    /**
     * Construtor que inicializa a conta bancária com número, cliente e saldo inicial.
     *
     * @param accountNumber Número da conta
     * @param owner Cliente proprietário da conta
     * @param balance Saldo inicial
     */
    public BankAccount(String accountNumber, User owner, double balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    /**
     * Método abstrato que realiza a retirada de um valor da conta.
     * Deve ser implementado nas subclasses para definir a lógica de saque.
     *
     * @param amount Quantia a ser retirada
     */
    public abstract void withdraw(double amount);

    /**
     * Realiza depósito de um valor na conta, aumentando o saldo.
     *
     * @param amount Quantia a ser depositada
     */
    public void deposit(double amount) {
        this.balance += amount;
    }

    /**
     * Obtém o saldo atual da conta.
     *
     * @return Saldo disponível (double)
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Retorna o número da conta bancária.
     *
     * @return Número da conta como String
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Retorna o proprietário (Cliente) da conta.
     *
     * @return Objeto User associado à conta
     */
    public User getOwner() {
        return owner;
    }
}
