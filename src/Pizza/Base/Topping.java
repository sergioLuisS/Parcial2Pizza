package Pizza.Base;

public class Topping {
    private String nombre;
    private double precio;
    public static final Topping Piña = new Topping("Piña", 20);
    public static final Topping Mozarella = new Topping("Mozzarella", 15);
    public static final Topping Jamon = new Topping("Jamon", 25);
    public static final Topping Polllo = new Topping("Pollo", 10);
    public static final Topping SalsaBarbacoa = new Topping("Salsa Barbacoa", 20);
    public static final Topping Cebolla = new Topping("Cebolla", 12);
    public static final Topping Tomate = new Topping("Tomate", 10);
    public static final Topping Albahaca = new Topping("Albahaca", 5);
    public static final Topping Chile = new Topping("Chile", 20);
    public static final Topping carne = new Topping("Carne", 30);
    public static final Topping Champiñines = new Topping("Champiniones", 18);
    public static final Topping Aceitunas = new Topping("Aceitunas", 15);
    public static final Topping Papas = new Topping("Papas", 10);
    public static final Topping Pepperoni = new Topping("Pepperoni", 25);
    public static final Topping Frijoles = new Topping("Frijoles", 25);
    public static  final Topping Queso = new Topping("Queso", 20);

    public Topping(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public static void remove(Topping topping) {
        if (topping != null) {
            topping.remove(topping);
        }

    }

    @Override
    public String toString() {
        return nombre + " Q" + precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}