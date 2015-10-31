package Interfaz;


import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Ejemplo de JTextArea metido en un JScrollPane.
 * 
 * @author Chuidiang
 * 
 */
public class EjemploScrollPaneTextArea {

    private static final int NUMERO_COLUMNAS = 80;
    private static final int NUMERO_FILAS = 25;

    public static void main(String[] args) {
        new EjemploScrollPaneTextArea();
    }

    private final JFrame ventana;
    private final JTextArea textArea;
    private final JScrollPane scrollPane;

    /** Crea y visualiza la ventana de ejemplo */
    public EjemploScrollPaneTextArea() {
        ventana = new JFrame("Ejemplo de JTextArea en JScrollPane");
        textArea = new JTextArea(NUMERO_FILAS, NUMERO_COLUMNAS);
        scrollPane = new JScrollPane(textArea);

        ventana.getContentPane().add(scrollPane);
        ventana.pack();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ventana.setVisible(true);
    }
}