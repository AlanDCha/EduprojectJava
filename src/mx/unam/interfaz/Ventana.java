package mx.unam.interfaz;

import javax.swing.*;
import java.awt.event.*;

public class Ventana extends JFrame implements ActionListener{

    private JTextField textField;
    private JLabel label;
    private JButton button;

    public Ventana() {

        // Configuracion del JFrame
        setLayout(null);
        setBounds(50, 50, 350, 250);
        setTitle("Interfaz: impar, par");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Etiqueta usuario
        label = new JLabel("Ingrese un numero:");
        label.setBounds(10, 10, 150, 30);
        add(label);

        // Campo texto
        textField = new JTextField();
        textField.setBounds(150, 10, 150, 20);
        add(textField);

        // Boton aceptar
        button = new JButton("Verficar");
        button.setBounds(10, 80, 100, 30);
        add(button);

        // Inicializa listener button
        button.addActionListener(this);

        // Muestra JFrame
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String response;
        int number;

        if (event.getSource() == button) {
            response = textField.getText();
            
            try {
                number = Integer.parseInt(response);
                String answer = number % 2 == 0 ? "Es par" : "Es impar";
                JOptionPane.showMessageDialog(null, answer);
    
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, 
                    "No ingresaste un numero o es demasiado largo");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
