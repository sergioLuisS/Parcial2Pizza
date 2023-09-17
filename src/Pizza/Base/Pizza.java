package Pizza.Base;
import java.util.ArrayList;
import java.util.List;

public abstract class Pizza {
    private String nombre;
    private List<String> ingredientes;
    private List<Topping> toppings;
    private double precioBase = 25;

    public Pizza(String nombre, double precioBase) {
        this.nombre = nombre;
        this.ingredientes = new ArrayList<>();
        this.toppings = new ArrayList<>();
        this.precioBase = precioBase;
    }
    public void agregarIngrediente(String ingrediente) {
        ingredientes.add(ingrediente);
    }

    public void agregarTopping(Topping topping) {
        toppings.add(topping);
    }

    public abstract double obtenerPrecioEspecialidad();

    public double calcularPrecioTotal() {
        double precioTotal = precioBase;

        // Sumar el precio de los ingredientes
        for (String ingrediente : ingredientes) {
            precioTotal += obtenerPrecioIngrediente(ingrediente);
        }

        // Sumar el precio de los toppings
        for (Topping topping : toppings) {
            precioTotal += topping.getPrecio();
        }

        return precioTotal;
    }

    public String getNombre() {
        return nombre;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    // Método abstracto para obtener el precio de un ingrediente específico
    public abstract double obtenerPrecioIngrediente(String ingrediente);

    public void removeTopping(Topping topping) {
    Topping.remove(topping);
    }
}


//   public void agregarIngrediente(String ingrediente) {
//        ingredientes.add(ingrediente);
//    }
//
//    public void agregarTopping(Topping topping) {
//        toppings.add(topping);
//    }
//
//    public abstract double obtenerPrecioEspecialidad();
//
//    public double calcularPrecioTotal() {
//        double precioTotal = precioBase;
//
//        // Sumar el precio de los ingredientes
//        for (String ingrediente : ingredientes) {
//            precioTotal += obtenerPrecioIngrediente(ingrediente);
//        }
//
//        // Sumar el precio de los toppings
//        for (Topping topping : toppings) {
//            precioTotal += topping.getPrecio();
//        }
//
//        return precioTotal;
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public List<String> getIngredientes() {
//        return ingredientes;
//    }
//
//    public List<Topping> getToppings() {
//        return toppings;
//    }
//
//    public double getPrecioBase() {
//        return precioBase;
//    }
//
//    // Método abstracto para obtener el precio de un ingrediente específico
//    public abstract double obtenerPrecioIngrediente(String ingrediente);
//}