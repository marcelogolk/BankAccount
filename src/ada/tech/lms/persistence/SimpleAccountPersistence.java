package ada.tech.lms.persistence;

import ada.tech.lms.domain.SimpleAccount;
import ada.tech.lms.domain.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SimpleAccountPersistence {

    private final Path caminhoArquivo;

    public SimpleAccountPersistence() {
        try {
            caminhoArquivo = getPath();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao acessar arquivo contas.txt", e);
        }
    }

    public void add(SimpleAccount account) {
        String contaFormatada = getAccountFormatted(account);
        try (BufferedWriter writer =
                     Files.newBufferedWriter(caminhoArquivo, StandardOpenOption.APPEND)) {
            writer.write(contaFormatada);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar conta no arquivo", e);
        }
    }

    public List<SimpleAccount> getAll() {
        List<SimpleAccount> contas = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(caminhoArquivo)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                contas.add(converter(linha));
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler contas do arquivo", e);
        }
        return contas;
    }

    private SimpleAccount converter(String linha) {
        List<String> campos = List.of(linha.split(","));
        if (campos.size() < 3) {
            throw new IllegalArgumentException("Linha inválida no arquivo contas.txt: " + linha);
        }

        Iterator<String> iterator = campos.iterator();

        String cpf = iterator.next().trim();
        String numeroConta = iterator.next().trim();
        String saldoStr = iterator.next().trim();

        double saldo;
        try {
            saldo = Double.parseDouble(saldoStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Saldo inválido na linha: " + linha);
        }

        User user = new User();
        user.setCpf(cpf);

        return new SimpleAccount(numeroConta, user, saldo);
    }

    private Path getPath() throws IOException {
        Path caminho = Paths.get("src","resources", "contas.txt");
        if (!caminho.toFile().exists()) {
            caminho.toFile().createNewFile();
        }
        return caminho;
    }

    private String getAccountFormatted(SimpleAccount account) {
        return String.format("%s,%s,%.2f",
                account.getOwner().getCpf(),
                account.getAccountNumber(),
                account.getBalance());
    }
}