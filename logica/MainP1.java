import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MainP1 {

    public static void main(String[] args) {
        Path filePath = (args.length > 0) ? Paths.get(args[0]) : Paths.get("./cards_input.txt");
        if (!Files.isRegularFile(filePath)) {
            System.out.println("El archivo especificado no existe o no es un archivo válido.");
            return;
        }

        try {
            int totalPoints = calculateTotalPoints(filePath);
            System.out.println("El total de puntos es: " + totalPoints);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static int calculateTotalPoints(Path filePath) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        return lines.stream()
                    .mapToInt(MainP1::calculateCardPoints)
                    .sum();
    }

    public static int calculateCardPoints(String cardLine) {
        String[] parts = cardLine.split("\\|");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Formato de tarjeta inválido.");
        }

        Set<String> winningNumbersSet = new HashSet<>(List.of(parts[0].trim().split("\\s+")));
        String[] userNumbers = parts[1].trim().split("\\s+");

        int points = 0;
        boolean firstMatch = false;

        for (String number : userNumbers) {
            if (winningNumbersSet.contains(number)) {
                points = firstMatch ? points * 2 : 1;
                firstMatch = true;
            }
        }

        return points;
    }
}