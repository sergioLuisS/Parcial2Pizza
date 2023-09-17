package Pizza.Especialidades;

import Pizza.Base.Pizza;

public class PizzaLoca extends Pizza {

    public PizzaLoca() {
        super("Pizza Loca", 25); // Nombre de la pizza
        agregarIngrediente("Frijoles");
        agregarIngrediente("Chile");
        agregarIngrediente("Papas");
        agregarIngrediente("carne");
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


