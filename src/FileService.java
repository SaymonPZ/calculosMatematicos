import java.io.*;

public final class FileService {

    private static final String FILE_NAME = "usuario.txt";

    private FileService() {}

    public static void salvarUsuario(String usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(usuario);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String lerUsuario() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            return reader.readLine();
        } catch (IOException e) {
            return "";
        }
    }
}