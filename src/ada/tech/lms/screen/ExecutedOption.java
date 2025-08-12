package ada.tech.lms.screen;

/**
 * Interface pertencente ao pacote screen, representando a camada View do projeto.
 * Define o contrato para as opções executáveis no sistema bancário,
 * garantindo que todas as opções implementem o método execute para realizar sua ação.
 *
 * @author Matheus Alves Sousa
 * @version 1.1
 */
public interface ExecutedOption {

	/**
	 * Executa a ação referente à opção escolhida pelo cliente.
	 * Implementações específicas definirão o comportamento detalhado.
	 */
	void execute();
}

