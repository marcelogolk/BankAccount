package ada.tech.lms.domain;

/**
 * Representa um cliente do banco, identificado pelo CPF e nome.
 * Pode estar associado a uma ou mais contas bancárias no sistema.
 *
 * @author Matheus Alves Sousa
 * @version 1.0
 */
public class User {
    /** CPF do cliente, usado como identificador único */
    private String cpf;

    /** Nome completo do cliente */
    private String name;

    /**
     * Constrói um cliente com CPF e nome.
     *
     * @param id CPF do cliente, deve ser único e válido
     * @param name Nome completo do cliente
     */
    public User(String id, String name) {
        this.cpf = id;
        this.name = name;
    }

    /**
     * Retorna o CPF do cliente.
     *
     * @return CPF como String
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Retorna o nome completo do cliente.
     *
     * @return Nome como String
     */
    public String getName() {
        return name;
    }
}
