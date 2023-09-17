package Formulario;

import Pizza.Base.Topping;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;


public class frmPizza {
    private JPanel jPanelPrincipal;
    private JComboBox<String> comboBoxEspecialidades;
    private JTextField txtPizza;
    private JButton btnAgregarIngrediente;
    private JButton btnPrepararPizza;
    private JList<String> listaIngredientes;
    private JList<String> listaEstado;
    private JRadioButton radioPequeña;
    private JRadioButton radioMediana;
    private JRadioButton radioGrande;
    private JLabel lblTotal;
    private JComboBox<Topping> comboBoxIngredientes; // Usar JComboBox de tipo Topping
    private JButton BotonCerrar;
    private ButtonGroup buttonGroup;

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }

    private List<Topping> ingredientesSeleccionados = new ArrayList<>(); // Usar una lista de Topping
    private DefaultListModel<String> modeloIngredientes = new DefaultListModel<>();
    private DefaultListModel<String> modeloEstado = new DefaultListModel<>();

    private double precioBase = 0.0;
    private Map<Topping, Double> preciosIngredientes = new HashMap<>(); // Usar un mapa de Topping y Double

    public frmPizza() {
        CargarEspecialidades();
        CargarIngredientes();
        listaIngredientes.setModel(modeloIngredientes);
        listaEstado.setModel(modeloEstado);
        inicializarPreciosIngredientes();

        radioPequeña.addActionListener(e -> actualizarPrecioPizza());
        radioMediana.addActionListener(e -> actualizarPrecioPizza());
        radioGrande.addActionListener(e -> actualizarPrecioPizza());
        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioPequeña);
        buttonGroup.add(radioMediana);
        buttonGroup.add(radioGrande);

        btnAgregarIngrediente.addActionListener(e -> {
            Topping selectedItem = (Topping) comboBoxIngredientes.getSelectedItem();
            if (selectedItem != null) {
                ingredientesSeleccionados.add(selectedItem);
                modeloIngredientes.addElement(selectedItem.getNombre()); // Agregar el nombre del ingrediente a la lista
                precioBase += obtenerPrecioIngrediente(selectedItem);
                actualizarPrecioPizza();
            } else {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado un ingrediente.");
            }
        });

        btnPrepararPizza.addActionListener(e -> {
            String nombrePizza = txtPizza.getText().trim();
            String tamañoPizza = obtenerTamañoSeleccionado();
            if (validarPizza(nombrePizza, tamañoPizza, ingredientesSeleccionados)) {
                modeloEstado.addElement("Preparando pizza: " + nombrePizza);
                modeloEstado.addElement("Tamaño: " + tamañoPizza);
                List<String> nombresIngredientes = new ArrayList<>();
                for (Topping ingrediente : ingredientesSeleccionados) {
                    nombresIngredientes.add(ingrediente.getNombre());
                }
                modeloEstado.addElement("Ingredientes: " + nombresIngredientes);
                double precio = calcularPrecio();
                modeloEstado.addElement("Precio: Q" + precio);
                modeloEstado.addElement("Pizza preparada");
            } else {
                JOptionPane.showMessageDialog(null, "Error al preparar la pizza");
            }
        });

        listaIngredientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int indiceSeleccionado = listaIngredientes.getSelectedIndex();
                    if (indiceSeleccionado != -1) {
                        Topping ingredienteSeleccionado = ingredientesSeleccionados.get(indiceSeleccionado);
                        modeloIngredientes.removeElementAt(indiceSeleccionado);
                        ingredientesSeleccionados.remove(ingredienteSeleccionado);
                        precioBase -= obtenerPrecioIngrediente(ingredienteSeleccionado);
                        actualizarPrecioPizza();
                    }
                }
            }
        });
        BotonCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private double calcularPrecio() {
        String tamañoSeleccionado = obtenerTamañoSeleccionado();
        if (tamañoSeleccionado != null) {
            double precio = precioBase;
            if (tamañoSeleccionado.equals("Pequeña")) {
                precio *= 0.9;
            } else if (tamañoSeleccionado.equals("Grande")) {
                precio *= 1.2;
            }
            return precio;
        }
        return 0.0;
    }

    private void actualizarPrecioPizza() {
        double precio = calcularPrecio();
        lblTotal.setText(String.format("Q%.2f", precio));
    }

    private void CargarEspecialidades() {
        comboBoxEspecialidades.addItem("Yo la armo");
        comboBoxEspecialidades.addItem("Pizza Italiana");
        comboBoxEspecialidades.addItem("Pizza PolloBarbacoa");
        comboBoxEspecialidades.addItem("Pizza Loca");
        comboBoxEspecialidades.addItem("Pizza Campestre");

        comboBoxEspecialidades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String especialidadSeleccionada = comboBoxEspecialidades.getSelectedItem().toString();
                if (especialidadSeleccionada.equals("Pizza Italiana")) {
                    modeloIngredientes.clear();
                    ingredientesSeleccionados.clear();
                    Topping[] ingredientesPizzaItaliana = {Topping.Mozarella, Topping.Aceitunas, Topping.Jamon};

                    for (Topping ingrediente : ingredientesPizzaItaliana) {
                        modeloIngredientes.addElement(ingrediente.getNombre());
                        ingredientesSeleccionados.add(ingrediente);
                        precioBase += obtenerPrecioIngrediente(ingrediente);
                    }
                    actualizarPrecioPizza();
                } else if (especialidadSeleccionada.equals("Pizza PolloBarbacoa")) {
                    modeloIngredientes.clear();
                    ingredientesSeleccionados.clear();
                    Topping[] ingredientesPizzapolloBarbacoa = {Topping.Polllo, Topping.SalsaBarbacoa, Topping.Cebolla};

                    for (Topping ingrediente : ingredientesPizzapolloBarbacoa) {
                        modeloIngredientes.addElement(ingrediente.getNombre());
                        ingredientesSeleccionados.add(ingrediente);
                        precioBase += obtenerPrecioIngrediente(ingrediente);
                    }
                    actualizarPrecioPizza();
                } else if (especialidadSeleccionada.equals("Pizza Loca")) {
                    modeloIngredientes.clear();
                    ingredientesSeleccionados.clear();
                    Topping[] ingredientesPizzaLoca = {Topping.Frijoles, Topping.Chile, Topping.Papas, Topping.carne};

                    for (Topping ingrediente : ingredientesPizzaLoca) {
                        modeloIngredientes.addElement(ingrediente.getNombre());
                        ingredientesSeleccionados.add(ingrediente);
                        precioBase += obtenerPrecioIngrediente(ingrediente);
                    }
                 actualizarPrecioPizza();
                }
                    else if (especialidadSeleccionada.equals("Pizza Campestre")) {
                    modeloIngredientes.clear();
                    ingredientesSeleccionados.clear();
                    Topping[] ingredientesPizzaCampestre = {Topping.Frijoles, Topping.Champiñines, Topping.Jamon, Topping.Cebolla};

                    for (Topping ingrediente : ingredientesPizzaCampestre) {
                        modeloIngredientes.addElement(ingrediente.getNombre());
                        ingredientesSeleccionados.add(ingrediente);
                        precioBase += obtenerPrecioIngrediente(ingrediente);
                    }
                    actualizarPrecioPizza();
                }
                    else {

                    modeloIngredientes.clear();
                    ingredientesSeleccionados.clear();
                    precioBase = calcularPrecioBase();
                    actualizarPrecioPizza();
                }
            }
        });
    }
//

    private double calcularPrecioBase() {
                double precioBase = 0.0;
                String tamañoSeleccionado = obtenerTamañoSeleccionado();
                if (tamañoSeleccionado != null) {
                    if (tamañoSeleccionado.equals("Pequeña")) {
                        precioBase += 5.0;
                    } else if (tamañoSeleccionado.equals("Mediana")) {
                        precioBase += 7.0;
                    } else if (tamañoSeleccionado.equals("Grande")) {
                        precioBase += 9.0;
                    }
                }
                for (Topping ingrediente : ingredientesSeleccionados) {
                    precioBase += obtenerPrecioIngrediente(ingrediente);
                }
                return precioBase;
            }

            private void inicializarPreciosIngredientes() {

                preciosIngredientes.put(Topping.Piña, 20.0);
                preciosIngredientes.put(Topping.Mozarella, 15.0);
                preciosIngredientes.put(Topping.Jamon, 25.0);
                preciosIngredientes.put(Topping.Polllo, 10.0);
                preciosIngredientes.put(Topping.SalsaBarbacoa, 20.0);
                preciosIngredientes.put(Topping.Cebolla, 12.0);
                preciosIngredientes.put(Topping.Tomate, 10.0);
                preciosIngredientes.put(Topping.Albahaca, 5.0);
                preciosIngredientes.put(Topping.Chile, 20.0);
                preciosIngredientes.put(Topping.carne, 30.0);
                preciosIngredientes.put(Topping.Champiñines, 18.0);
                preciosIngredientes.put(Topping.Aceitunas, 15.0);
                preciosIngredientes.put(Topping.Papas, 10.0);
                preciosIngredientes.put(Topping.Pepperoni, 25.0);
                preciosIngredientes.put(Topping.Frijoles, 25.0);
                preciosIngredientes.put(Topping.Queso, 20.0);


                // Agrega otros ingredientes y sus precios
            }

            private void CargarIngredientes() {
                Topping[] ingredientesTopping = {Topping.Piña, Topping.Mozarella,
                        Topping.Jamon, Topping.Polllo, Topping.SalsaBarbacoa, Topping.Cebolla, Topping.Tomate,
                        Topping.Albahaca, Topping.Chile, Topping.carne, Topping.Champiñines, Topping.Aceitunas,
                        Topping.Papas, Topping.Pepperoni, Topping.Frijoles, Topping.Queso};

                for (Topping ingrediente : ingredientesTopping) {
                    comboBoxIngredientes.addItem(ingrediente);
                }
            }

            private String obtenerTamañoSeleccionado() {
                if (radioPequeña.isSelected()) {
                    return "Pequeña";
                } else if (radioMediana.isSelected()) {
                    return "Mediana";
                } else if (radioGrande.isSelected()) {
                    return "Grande";
                } else {
                    return null;
                }
            }

            private boolean validarPizza(String nombrePizza, String tamañoPizza, List<Topping> ingredientes) {
                return !nombrePizza.isEmpty() && tamañoPizza != null && !ingredientes.isEmpty();
            }

            private double obtenerPrecioIngrediente(Topping ingrediente) {
                if (preciosIngredientes.containsKey(ingrediente)) {
                    return preciosIngredientes.get(ingrediente);
                }
                return 0.0;
            }

        }
