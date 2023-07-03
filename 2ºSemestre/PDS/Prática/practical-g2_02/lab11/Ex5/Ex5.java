package lab11.Ex5;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class Ex5 {
    public static void main(String[] args) throws IOException {
        Path rootPath = Path.of("../"); // Caminho raiz da estrutura de diretórios

        FileVisitor<Path> visitor = new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                // Lógica para manipular o arquivo visitado
                System.out.println("A visitar o arquivo: " + file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                // Lógica antes de visitar um diretório
                System.out.println("A entrar no diretório: " + dir);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                // Lógica depois de visitar um diretório
                System.out.println("A sair do diretório: " + dir);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                // Lógica para tratar falhas ao visitar um arquivo
                System.err.println("Erro ao visitar arquivo: " + file);
                return FileVisitResult.CONTINUE;
            }
        };

        Files.walkFileTree(rootPath, visitor);
    }
}

