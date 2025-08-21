package ada.tech.lms.persistence;

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

public class UserPersistence {
    private Path caminhoArquivo;

    public UserPersistence() {
        try {
            caminhoArquivo = getPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(User cliente) {
        String clienteFormatted = getClienteFormatted(cliente);
        try (BufferedWriter writer =
                     Files.newBufferedWriter(caminhoArquivo, StandardOpenOption.APPEND)) {
            writer.write(clienteFormatted);
            writer.newLine();
        } catch (IOException ioException) {
            throw new RuntimeException();
        }
    }

    public List<User> getAll() {
        List<User> clientes = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(caminhoArquivo)) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                clientes.add(converter(linha));

            }
        } catch (IOException ioException) {
            throw new RuntimeException();
        }
        return clientes;
    }

    private User converter(String linha) {
        var cliente = new User();
        List<String> strings = List.of(linha.split(","));
        Iterator<String> iterator = strings.iterator();
        cliente.setCpf(iterator.next());
        cliente.setName(iterator.next());
        return cliente;
    }

    private Path getPath() throws IOException {
        Path caminho = Paths.get("src", "ada", "tech", "lms", "resources"
                , "usuarios.txt");
        if (!caminho.toFile().exists()) {
            caminho.toFile().createNewFile();
        }
        return caminho;
    }

    private String getClienteFormatted(User cliente) {
        return String.format("%s,%s", cliente.getCpf(), cliente.getName());
    }
}