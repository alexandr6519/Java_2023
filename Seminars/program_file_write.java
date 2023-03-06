import java.io.FileWriter;
import java.io.IOException;

public class program_file_write {
    public static void main(String[] args) {
        try (FileWriter fw = new FileWriter("file_2.txt", false)) {
            fw.write("3 * x * x + 12 * x - 2 = 0");
            fw.flush();
            } catch (IOException ex) {
            System.out.println(ex.getMessage());
            }           
    }
}
