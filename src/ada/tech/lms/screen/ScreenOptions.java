package ada.tech.lms.screen;

/**
 * Enumeração que define as opções do menu principal exibido ao cliente para operações bancárias.
 * Cada opção contém o código numérico usado na seleção e a descrição para exibição.
 *
 * As opções contemplam criação de conta, saque, depósito, consulta de saldo, extrato e saída do sistema.
 *
 * @author Matheus Alves Sousa
 * @version 1.1
 */
public enum ScreenOptions {

    CREATE_ACCOUNT(1, "Criar uma nova conta"),
    WITHDRAW(2, "Realizar saque na conta"),
    DEPOSIT(3, "Realizar depósito na conta"),
    GET_BALANCE(4, "Visualizar saldo"),
    GET_STATEMENT(5, "Visualizar extrato"),
    NO_OPITION(0, "Sair da aplicação");

    private final int option;
    private final String optionDescription;

    private ScreenOptions(int option, String optionDescription) {
        this.option = option;
        this.optionDescription = optionDescription;
    }

    /**
     * Retorna o código numérico da opção.
     *
     * @return número da opção para seleção no menu
     */
    public int getOption() {
        return option;
    }

    /**
     * Retorna a descrição textual exibida ao cliente no menu.
     *
     * @return descrição da opção
     */
    public String getOptionDescription() {
        return optionDescription;
    }

    /**
     * Retorna a instância de ScreenOptions correspondente ao código informado.
     * Utilizado para mapear a entrada do usuário a uma opção válida.
     *
     * @param option código numérico da opção selecionada
     * @return ScreenOptions correspondente ao código
     * @throws RuntimeException se a opção não existir
     */
    public static ScreenOptions getScreenOption(int option) {
        for (ScreenOptions screenOption : ScreenOptions.values()) {
            if (screenOption.getOption() == option) {
                return screenOption;
            }
        }
        throw new IllegalArgumentException("Opção inválida: " + option);
    }
}
