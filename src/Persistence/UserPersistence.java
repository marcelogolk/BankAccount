package Persistence;

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
    private final Path caminhoArquivo;

    public UserPersistence() {
        try {
            caminhoArquivo = getPath();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void add(User client) {
        String clientFormatted = getClientFormatted(client);
        try (BufferedWriter writer =
                     Files.newBufferedWriter(caminhoArquivo, StandardOpenOption.APPEND)) {
            writer.write(clientFormatted);
            writer.newLine();
        } catch (IOException ioException) {
            throw new RuntimeException();
        }
    }

    public List<User> getAll() {
        List<User> clientsList = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(caminhoArquivo)) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                clientsList.add(converter(linha));
            }
        } catch (IOException ioException) {
            throw new RuntimeException();
        }
        return clientsList;
    }

    private User converter(String linha) {
        var client = new User();
        List<String> strings = Arrays.asList(linha.split(","));
        Iterator<String> iterator = strings.iterator();
        client.setCpf(iterator.next());
        client.setName(iterator.next());
        return client;
    }

    private Path getPath() throws IOException {
        Path caminho = Paths.get("src", "main", "resources"
                , "User.txt");
        if (!caminho.toFile().exists()) {
            caminho.toFile().createNewFile();
        }
        return caminho;
    }

    private String getClientFormatted(User client) {
        return String.format("%s,%s", client.getCpf(), client.getName());
    }
}
