package ada.tech.lms.domain;

/**
 * Representa uma conta bancária especial do sistema,
 * que permite saques além do saldo disponível até um limite adicional definido.
 *
 * Estende a classe abstrata BankAccount e implementa a lógica específica de saque considerando limite.
 *
 * @author Matheus Alves Sousa
 * @version 1.1
 * @see ada.tech.lms.domain.BankAccount
 */
public class SpecialAccount extends BankAccount {

    /** Limite especial que permite saque além do saldo */
    private double limit;

    /**
     * Constrói uma conta especial com número, proprietário, saldo inicial e limite de crédito.
     *
     * @param accountNumber Número único da conta
     * @param owner Cliente proprietário da conta
     * @param balance Saldo inicial da conta, valor pode ser zero ou positivo
     * @param limit Limite especial para saques além do saldo disponível, deve ser positivo
     */
    public SpecialAccount(String accountNumber, User owner, double balance, double limit) {
        super(accountNumber, owner, balance);
        this.limit = limit;
    }

    /**
     * Realiza o saque considerando saldo disponível e limite especial.
     *
     * O valor do saque deve ser positivo e não exceder saldo + limite.
     * Caso ultrapasse, lança exceção.
     * Também registra a transação em caso de sucesso.
     *
     * @param amount Valor a ser sacado, maior que zero
     * @throws IllegalArgumentException se valor inválido ou ultrapassar saldo + limite
     */
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser positivo.");
        }
        if (amount <= balance + limit) {
            balance -= amount;
            transactions.add(new Transaction(Transaction.TransactionType.WITHDRAW, amount));
        } else {
            throw new IllegalArgumentException("Valor ultrapassa o saldo e limite da conta.");
        }
    }

    /**
     * Retorna o limite especial de crédito disponível na conta.
     *
     * @return limite especial
     */
    public double getLimit() {
        return limit;
    }
}
