package ada.tech.lms.domain;

import java.time.LocalDateTime;

/**
 * Representa uma transação financeira associada a uma conta bancária.
 * Registra o tipo da transação (depósito ou saque), valor e timestamp da ocorrência.
 * Utilizada para manter histórico e possibilitar a geração de extrato.
 *
 * Implementa {@link Comparable} para permitir ordenação cronológica das transações.
 *
 * @author Marcelo Guimarães Carvalho
 * @author Ramon da Rocha Pereira
 * @author Jane de Sousa Sales
 * @version 1.0
 */
public class Transaction implements Comparable<Transaction> {

    /**
     * Enumerador que define os tipos possíveis de transação:
     * depósito ou saque.
     */
    public enum TransactionType {
        DEPOSIT, WITHDRAW
    }

    private final TransactionType type;
    private final double amount;
    private final LocalDateTime dateTime;

    /**
     * Cria uma transação com tipo e valor informados,
     * registrando o timestamp no momento da criação.
     *
     * @param type Tipo da transação (DEPOSIT ou WITHDRAW)
     * @param amount Valor da transação
     */
    public Transaction(TransactionType type, double amount) {
        this.type = type;
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
    }

    /**
     * Retorna o tipo da transação.
     *
     * @return tipo da transação
     */
    public TransactionType getType() {
        return type;
    }

    /**
     * Retorna o valor da transação.
     *
     * @return valor movimentado na transação
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Retorna a data e hora da transação.
     *
     * @return timestamp da transação
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Compara esta transação com outra de acordo com a data/hora.
     * Permite ordenação cronológica.
     *
     * @param other Outra transação a ser comparada
     * @return valor negativo, zero ou positivo se esta transação é anterior, igual ou posterior à outra, respectivamente
     */
    @Override
    public int compareTo(Transaction other) {
        return this.dateTime.compareTo(other.dateTime);
    }

    @Override
    public String toString() {
        return String.format("%s - %.2f - %s", dateTime, amount, type);
    }
}
