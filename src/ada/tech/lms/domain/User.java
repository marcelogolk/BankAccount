package ada.tech.lms.domain;

/**
 * Classe pertencente ao pacote domain, que representa a camada Model do projeto.
 * Representa um cliente do banco com CPF, nome e suas contas associadas.
 *
 * @author Matheus Alves Sousa
 * @version 1.0
 */

public class User {
    /** CPF do usuário */
    private String cpf;

    /** Nome completo do cliente */
    private String name;

    /**
     * Construtor para criar um cliente com CPF e nome.
     * @param id CPF do usuário
     * @param name Nome completo do cliente
     */
    public User(String id, String name) {
        this.cpf = id;
        this.name = name;
    }

    /**
     * Retorna o CPF do cliente.
     * @return CPF como String
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Retorna o nome do cliente.
     * @return Nome como String
     */
    public String getName() {
        return name;
    }
}
