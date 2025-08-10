package ada.tech.lms.service;
import ada.tech.lms.domain.BankAccount;
import ada.tech.lms.domain.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe pertencente ao pacote service, representando a camada Controller/Serviço do projeto.
 * Responsável pelo gerenciamento das operações bancárias relacionadas às contas e clientes.
 * Atua como intermediária entre a camada Model (domain) e a camada View (screen),
 * realizando operações como adicionar contas, depósitos, saques e consultas de saldo.
 *
 * Fornece métodos para buscar contas e usuários de acordo com "número de conta" ou "documentos".
 *
 * @author Matheus Alves
 * @version 1.0
 * @see ada.tech.lms.domain.BankAccount
 * @see ada.tech.lms.domain.User
 */
public class BankService {
    private List<BankAccount> accounts = new ArrayList<>();

    /**
     * Adiciona uma nova conta à lista de contas gerenciadas pelo serviço.
     *
     * @param account conta bancária a ser adicionada
     */
    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    /**
     * Realiza um depósito em uma conta específica identificada pelo número da conta.
     *
     * @param accountNumber número da conta onde será depositado o valor
     * @param amount valor a ser depositado
     */
    public void deposit(String accountNumber, double amount) {
        findAccount(accountNumber).deposit(amount);
    }

    /**
     * Realiza um saque em uma conta específica identificada pelo número da conta.
     *
     * @param accountNumber número da conta de onde será retirado o valor
     * @param amount valor a ser retirado
     */
    public void withdraw(String accountNumber, double amount) {
        findAccount(accountNumber).withdraw(amount);
    }

    /**
     * Consulta o saldo disponível em uma conta específica.
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
     * @return conta bancária encontrada
     * @throws IllegalArgumentException se a conta não for encontrada
     */
    public BankAccount findAccount(String accountNumber) {
        return accounts.stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }

    /**
     * Encontra um cliente pelo número do documento (CPF).
     *
     * @param documentNumber CPF do cliente buscado
     * @return objeto User correspondente
     * @throws IllegalArgumentException se o cliente não for encontrado
     */
    public User findUser(String documentNumber){
        for (BankAccount account : accounts){
            if(account.getOwner().getCpf().equals(documentNumber)){
                return account.getOwner();
            }
        }
        throw new IllegalArgumentException("There is no owner");
    }

    /**
     * Encontra uma conta bancária associada a um determinado cliente.
     *
     * @param user cliente do banco buscado
     * @return conta bancaria do cliente
     * @throws IllegalArgumentException se nenhuma conta for encontrada para o cliente
     */
    public BankAccount findAccountByUser(User user) {
        for (BankAccount account : accounts){
            if(account.getOwner().getCpf().equals(user.getCpf())){
                return account;
            }
        }
        throw new IllegalArgumentException("There is no owner");
    }
}
