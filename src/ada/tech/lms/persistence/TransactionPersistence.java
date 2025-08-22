package ada.tech.lms.persistence;
import ada.tech.lms.domain.Transaction;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TransactionPersistence {

    private final Path caminhoArquivo;

    public TransactionPersistence() {
        try {
            this.caminhoArquivo = getPath();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao acessar arquivo de transações", e);
        }
    }

    public void add(Transaction transaction, String cpf) {
        String linhaFormatada = transactionFormatted(cpf, transaction);
        try (BufferedWriter writer = Files.newBufferedWriter(caminhoArquivo, StandardOpenOption.APPEND)) {
            writer.write(linhaFormatada);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar transação no arquivo", e);
        }
    }

    public List<Transaction> getAll() {
        List<Transaction> transacoes = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(caminhoArquivo)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                transacoes.add(converter(linha));
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler transações do arquivo", e);
        }
        return transacoes;
    }

    public List<Transaction> getTransactionsByUserCpf(String cpf) {
        List<Transaction> transacoes = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(caminhoArquivo)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(",");
                if (campos.length >= 4 && campos[0].equals(cpf)) {
                    transacoes.add(converter(linha));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler transações do arquivo", e);
        }
        return transacoes;
    }

    private Transaction converter(String linha) {
        List<String> campos = List.of(linha.split(","));
        if (campos.size() < 4) {
            throw new IllegalArgumentException("Linha inválida no arquivo de transações: " + linha);
        }

        Iterator<String> iterator = campos.iterator();

        iterator.next(); // pula o CPF, não usado aqui

        String tipoStr = iterator.next().trim();
        String valorStr = iterator.next().trim();
        String dataHoraStr = iterator.next().trim();

        Transaction.TransactionType tipo;
        try {
            tipo = Transaction.TransactionType.valueOf(tipoStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de transação inválido na linha: " + linha);
        }

        double valor;
        try {
            valor = Double.parseDouble(valorStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Valor inválido na linha: " + linha);
        }

        LocalDateTime dataHora;
        try {
            dataHora = LocalDateTime.parse(dataHoraStr);
        } catch (Exception e) {
            throw new IllegalArgumentException("Data/hora inválida na linha: " + linha);
        }

        return new Transaction(tipo, valor, dataHora);
    }

    private String transactionFormatted(String cpf, Transaction transaction) {
        return String.format("%s,%s,%.2f,%s",
                cpf,
                transaction.getType().name(),
                transaction.getAmount(),
                transaction.getDateTime().toString());
    }

    private Path getPath() throws IOException {
        Path caminho = Paths.get("src","resources", "transacoes.txt");
        if (!caminho.toFile().exists()) {
            caminho.toFile().createNewFile();
        }
        return caminho;
    }
}
