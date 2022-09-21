package mx.unam.interfaz;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana extends JFrame implements ActionListener{
    private JButton saludar;

    public Ventana(){}

    public Ventana(String titulo) {
        setTitle(titulo);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        saludar = new JButton("saluda");
        saludar.addActionListener(this);
        add(saludar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "hola");
    }


}
