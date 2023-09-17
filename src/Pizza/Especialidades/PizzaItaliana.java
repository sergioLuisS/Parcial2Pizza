package Pizza.Especialidades;
import Pizza.Base.Pizza;
import Pizza.Base.Topping;

public class PizzaItaliana extends Pizza {

    public PizzaItaliana() {
        super("Pizza Italiana", 25); // Nombre de la pizza
        agregarIngrediente("Tomate");
        agregarIngrediente("Queso");
        agregarIngrediente("Albahaca");
        // Agregar más ingredientes predeterminados si es necesario
    }

    @Override
    public void agregarIngrediente(String ingrediente) {
        getIngredientes().add(ingrediente);
    }
    @Override
    public double obtenerPrecioEspecialidad() {
        // Calcula el precio en función de los ingredientes y el precio base
        double precio = getPrecioBase() + (getIngredientes().size() * 5.0); // Ejemplo de cálculo
        return precio;
    }

    @Override
    public double obtenerPrecioIngrediente(String ingrediente) {
        return 0;
    }
}


