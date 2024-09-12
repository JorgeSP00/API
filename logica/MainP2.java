import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class MainP2 {

    public static void main(String[] args) {
        // Ruta del archivo
        Path filePath = (args.length > 0) ? Paths.get(args[0]) : Paths.get("./cards_input.txt");
        if (!Files.isRegularFile(filePath)) {
            System.out.println("El archivo especificado no existe o no es un archivo válido.");
            return;
        }

        String archivo = filePath.normalize().toString();
        // Lista para almacenar las parejas de listas
        List<Integer[][]> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            // Leer el archivo línea por línea
            while ((linea = br.readLine()) != null) {
                // Procesar la línea y eliminar el prefijo "Card N:"
                linea = linea.contains(":") ? linea.split(":", 2)[1].trim() : linea.trim();

                // Dividir la línea por el delimitador "|"
                String[] partes = linea.split("\\|");

                if (partes.length == 2) {
                    try {
                        Integer[] numerosIzquierdaArray = parseNumbers(partes[0].trim());
                        Integer[] numerosDerechaArray = parseNumbers(partes[1].trim());

                        // Guardar el par de arrays en la lista
                        lines.add(new Integer[][]{numerosIzquierdaArray, numerosDerechaArray});
                    } catch (NumberFormatException e) {
                        System.out.println("Error de formato en la línea: " + linea);
                    }
                } else {
                    System.out.println("Línea no válida: " + linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + archivo);
            e.printStackTrace();
        }

        // Procesar todas las tarjetas y sumar las ganadas
        int cuentaTotal = lines.stream()
                .mapToInt(line -> calculaTarjeta(lines, lines.indexOf(line)))
                .sum();

        System.out.println("Total de tarjetas ganadas: " + cuentaTotal);
    }

    // Método para convertir una cadena de números en un array de enteros
    private static Integer[] parseNumbers(String input) throws NumberFormatException {
        return Arrays.stream(input.split("\\s+"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
    }

    // Método para calcular el número de tarjetas ganadas por la tarjeta inicial
    public static int calculaTarjeta(List<Integer[][]> tarjetas, int tarjetaInicial) {
        int cantidadGanada = 1;  // Contamos la tarjeta original

        // Números ganadores y jugados de la tarjeta actual
        Set<Integer> numerosJugados = new HashSet<>(Arrays.asList(tarjetas.get(tarjetaInicial)[1]));
        long cantidadTarjetas = Arrays.stream(tarjetas.get(tarjetaInicial)[0])
                                      .filter(numerosJugados::contains)
                                      .count();

        // Si ganamos más tarjetas, procesar recursivamente
        for (int i = 1; i <= cantidadTarjetas; i++) {
            if (tarjetaInicial + i < tarjetas.size()) {
                cantidadGanada += calculaTarjeta(tarjetas, tarjetaInicial + i);
            }
        }

        return cantidadGanada;
    }
}