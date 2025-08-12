package ada.tech.lms.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe abstrata pertencente ao pacote domain, que representa a camada Model do projeto.
 * Representa a conta bancária do cliente, com número, proprietário (cliente) e saldo,
 * definindo operações básicas como depósito e saque (abstrato).
 *
 * @author Matheus Alves Sousa
 * @version 1.1
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

    protected List<Transaction> transactions = new ArrayList<>();

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
     * Retorna uma lista imutável de transações realizadas na conta.
     *
     * @return lista das transações da conta
     */
    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    /**
     * Método abstrato para realizar saque da conta.
     * Deve ser implementado nas subclasses para definir a lógica específica de saque.
     *
     * @param amount Valor a ser sacado
     */
    public abstract void withdraw(double amount);

    /**
     * Realiza depósito de um valor na conta.
     *
     * @param amount Valor a ser depositado
     * @throws IllegalArgumentException se o valor depositado for igual ou menor que zero
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("O valor do depósito deve ser positivo.");
        }
        this.balance += amount;
        transactions.add(new Transaction(Transaction.TransactionType.DEPOSIT, amount));
    }

    /**
     * Retorna o saldo atual da conta.
     *
     * @return saldo disponível
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Retorna o número da conta bancária.
     *
     * @return número da conta
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Retorna o cliente proprietário da conta.
     *
     * @return objeto User proprietário
     */
    public User getOwner() {
        return owner;
    }
}
