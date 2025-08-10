package ada.tech.lms.screen;

/**
 * Enum pertencente ao pacote screen, que representa a camada View do projeto.
 * Define as opções do menu exibidas ao cliente para operações no sistema bancário,
 * como criar conta, realizar saque, depósito e consulta de saldo.
 *
 * @author Matheus Alves Sousa
 * @version 1.0
 */
public enum ScreenOptions {

	CREATE_ACCOUNT(1, "Criar uma nova conta"),
	WITHDRAW(2, "Realizar saque na conta"),
	DEPOSIT(3, "Realizar deposito na conta"),
	GET_BALANCE(4,"Visualizar saldo"),
	NO_OPTION(0,"Sair da aplicação");

	private int option;
	private String optionDescription;

	private ScreenOptions(int option, String optionDescription){
		this.option = option;
		this.optionDescription = optionDescription;
	}

	/**
	 * Retorna o código da opção para uso no menu.
	 * @return número da opção
	 */
	public int getOption() {
		return option;
	}

	/**
	 * Retorna a descrição textual da opção exibida ao cliente.
	 * @return descrição da opção
	 */
	public String getOptionDescription() {
		return optionDescription;
	}

	/**
	 * Retorna a enum ScreenOptions correspondente ao código informado.
	 * Utilizado para mapear a seleção do cliente para a ação correta.
	 * @param option código da opção selecionada
	 * @return ScreenOptions correspondente
	 * @throws RuntimeException se não for uma opção válida
	 */
	public static ScreenOptions getScreenOption(int option) {
		for (ScreenOptions screenOption : ScreenOptions.values()){
			if(screenOption.getOption() == option)
				return screenOption;
		}
		throw new RuntimeException("There is no selected option");
	}
}
