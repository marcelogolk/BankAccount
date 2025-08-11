package ada.tech.lms.service;

import ada.tech.lms.domain.BankAccount;
import ada.tech.lms.domain.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe da camada Service responsável pelo gerenciamento das operações bancárias.
 * Atua como intermediária entre a camada de domínio (Model) e a interface (View),
 * realizando operações como adicionar contas, depósitos, saques e consultas de saldo.
 * Fornece métodos para localizar contas ou usuários por número da conta ou CPF.
 *
 * @author Matheus Alves
 * @version 1.0
 * @see ada.tech.lms.domain.BankAccount
 * @see ada.tech.lms.domain.User
 */
public class BankService {

    private List<BankAccount> accounts = new ArrayList<>();

    /**
     * Adiciona uma nova conta bancária ao sistema.
     *
     * @param account conta bancária a ser adicionada
     */
    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    /**
     * Realiza depósito em conta identificada pelo número da conta.
     *
     * @param accountNumber número da conta a receber o depósito
     * @param amount valor a ser depositado
     */
    public void deposit(String accountNumber, double amount) {
        findAccount(accountNumber).deposit(amount);
    }

    /**
     * Realiza saque em conta identificada pelo número da conta.
     *
     * @param accountNumber número da conta de onde será sacado o valor
     * @param amount valor a ser sacado
     */
    public void withdraw(String accountNumber, double amount) {
        findAccount(accountNumber).withdraw(amount);
    }

    /**
     * Consulta o saldo atual da conta específica.
     *
     * @param accountNumber número da conta consultada
     * @return saldo atual da conta
     */
    public double checkBalance(String accountNumber) {
        return findAccount(accountNumber).getBalance();
    }

    /**
     * Encontra uma conta bancária pelo número da conta.
     *
     * @param accountNumber número da conta buscada
     * @return conta bancária correspondente
     * @throws IllegalArgumentException caso a conta não seja encontrada
     */
    public BankAccount findAccount(String accountNumber) {
        return accounts.stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));
    }

    /**
     * Encontra um usuário pelo CPF.
     *
     * @param documentNumber CPF do cliente buscado
     * @return usuário correspondente ao CPF informado
     * @throws IllegalArgumentException caso o usuário não seja encontrado
     */
    public User findUser(String documentNumber) {
        for (BankAccount account : accounts) {
            if (account.getOwner().getCpf().equals(documentNumber)) {
                return account.getOwner();
            }
        }
        throw new IllegalArgumentException("Cliente não encontrado");
    }

    /**
     * Encontra a conta bancária associada a um usuário específico.
     *
     * @param user usuário buscado
     * @return conta bancária do usuário
     * @throws IllegalArgumentException caso nenhuma conta seja encontrada para o usuário
     */
    public BankAccount findAccountByUser(User user) {
        for (BankAccount account : accounts) {
            if (account.getOwner().getCpf().equals(user.getCpf())) {
                return account;
            }
        }
        throw new IllegalArgumentException("Conta não encontrada para o cliente informado");
    }
}
