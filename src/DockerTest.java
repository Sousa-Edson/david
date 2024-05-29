import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DockerTest {

    public static void main(String[] args) {
        // Verifica o caminho do Docker
        String dockerPath = findDockerPath();
        System.out.println("Caminho do Docker: " + dockerPath);

        // Executa um comando Docker para listar os contêineres em execução
        String dockerCommand = dockerPath + " ps";
        executeCommand(dockerCommand);
    }

    private static String findDockerPath() {
        try {
            // Executa o comando "which docker" no terminal
            Process process = new ProcessBuilder("which", "docker").start();

            // Aguarda o término do processo
            process.waitFor();

            // Lê a saída do processo para obter o caminho do Docker
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String dockerPath = reader.readLine();

            // Retorna o caminho do Docker (pode ser null se o comando falhar)
            return dockerPath;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void executeCommand(String command) {
        try {
            // Executa o comando Docker
            Process process = new ProcessBuilder("bash", "-c", command).start();

            // Aguarda o término do processo
            int exitCode = process.waitFor();

            // Lê e imprime a saída do processo
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Imprime o código de saída
            System.out.println("Código de Saída: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
