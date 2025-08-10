package ada.tech.lms.domain;

/**
 * Classe concreta pertencente ao pacote domain, que representa a camada Model do projeto.
 * Define uma conta bancária especial com limite adicional que permite saques além do saldo.
 *
 * @author Matheus Alves Sousa
 * @version 1.0
 * @see ada.tech.lms.domain.BankAccount
 */

public class SpecialAccount extends BankAccount {

    /** Limite especial que permite saque além do saldo */
    private double limit;

    /**
     * Construtor que inicializa uma conta especial com número, cliente, saldo e limite.
     *
     * @param accountNumber Número da conta
     * @param owner Cliente do banco proprietário da conta
     * @param balance Saldo inicial da conta
     * @param limit Limite especial disponível para saque
     */
    public SpecialAccount(String accountNumber, User owner, double balance, double limit) {
        super(accountNumber, owner, balance);
        this.limit = limit;
    }

    /**
     * Realiza saque verificando se o valor está dentro do saldo disponível mais o limite.
     * Lança exceção se o valor ultrapassar esse limite.
     *
     * @param amount Valor a ser sacado
     * @throws IllegalArgumentException se o valor exceder saldo + limite
     */
    @Override
    public void withdraw(double amount) {
        if (amount <= balance + limit) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Valor ultrapassa o saldo e limite da conta.");
        }
    }

    /**
     * Retorna o limite especial disponível nesta conta.
     *
     * @return Limite especial como double
     */
    public double getLimit() {
        return limit;
    }
}
