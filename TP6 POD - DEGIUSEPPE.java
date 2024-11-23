import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Pedido {
    private String vaso;
    private List<String> ingredientes;
    private static final Map<String, Integer> preciosVasos = new HashMap<>();
    private static final Map<String, Integer> preciosIngredientes = new HashMap<>();

    static {
        preciosVasos.put("chico", 5);
        preciosVasos.put("mediano", 7);
        preciosVasos.put("grande", 10);

        preciosIngredientes.put("chocolate", 7);
        preciosIngredientes.put("caramelo", 12);
        preciosIngredientes.put("crema", 10);
        preciosIngredientes.put("rocklets", 15);
        preciosIngredientes.put("azúcar", 1);
    }

    public Pedido(String vaso, List<String> ingredientes) {
        this.vaso = vaso;
        this.ingredientes = ingredientes;
    }

    public int calcularPrecio() {
        int precioTotal = 15; // Base price
        precioTotal += preciosVasos.getOrDefault(vaso, 0);
        for (String ingrediente : ingredientes) {
            precioTotal += preciosIngredientes.getOrDefault(ingrediente, 0);
        }
        return precioTotal;
    }

    public String resumen() {
        String ingredientesStr = String.join(", ", ingredientes);
        return String.format("Vaso: %s, Ingredientes: %s, Total: $%d", vaso, ingredientesStr, calcularPrecio());
    }

    public static void main(String[] args) {
        Pedido cafe1 = new Pedido("chico", List.of());
        assert cafe1.calcularPrecio() == 20 : "Test 1 falló";

        Pedido cafe2 = new Pedido("mediano", List.of("azúcar"));
        assert cafe2.calcularPrecio() == 23 : "Test 2 falló";

        Pedido cafe3 = new Pedido("grande", List.of("chocolate", "caramelo"));
        assert cafe3.calcularPrecio() == 44 : "Test 3 falló";

        Pedido cafe4 = new Pedido("chico", List.of("chocolate", "caramelo", "crema", "rocklets", "azúcar"));
        assert cafe4.calcularPrecio() == 65 : "Test 4 falló";

        Pedido cafe5 = new Pedido("mediano", List.of());
        assert cafe5.calcularPrecio() == 22 : "Test 5 falló";

        System.out.println("Todos los tests pasaron correctamente.");
    }
}
