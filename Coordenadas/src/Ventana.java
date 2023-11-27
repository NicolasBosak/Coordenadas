import javax.swing.*;
import java.awt.event.*;

public class Ventana {
    private JPanel Coordenadas;
    private JTextField txtpuntoX;
    private JTextField txtpuntoY;
    private JButton btningresar;
    private JList lstpuntos;
    private JTabbedPane tabbedPane1;
    private JButton btndistancia;
    private JTextArea txtdistancia;

    private DefaultListModel dlm = new DefaultListModel();
    private Ordenar ordenar;

    private void llenarJlist() {
        dlm.clear();
        for (Punto punto : ordenar.getPuntos()) {
            dlm.addElement(punto);
        }
        lstpuntos.setModel(dlm);
        ordenar.ordenarPuntos();
    }

    public Ventana() {
        ordenar = new Ordenar();

        btningresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = Integer.parseInt(txtpuntoX.getText());
                int y = Integer.parseInt(txtpuntoY.getText());
                Punto punto = new Punto(x, y);
                ordenar.encolar(punto);
                ordenar.ordenarPuntos();
                llenarJlist();
                txtpuntoX.setText("");
                txtpuntoY.setText("");
            }
        });

        btndistancia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordenar.ordenarPuntos();
                txtdistancia.setText("");
                double max_dist = 0;
                Punto max_punt = null;
                for (Punto punto : ordenar.getPuntos()) {
                    double dist = punto.distanciaOrigen();
                    txtdistancia.append(String.format("Punto (%d, %d): Distancia desde el origen: %.2f\n", punto.getX(), punto.getY(), dist));
                    if (dist > max_dist) {
                        max_dist = dist;
                        max_punt = punto;
                    }
                }
                if (max_punt != null) {
                    txtdistancia.append(String.format("\nEl punto más lejano del origen es (%d, %d) con una distancia de %.2f.\n", max_punt.getX(), max_punt.getY(), max_dist));
                } else {
                    txtdistancia.append("\nNo se han agregado puntos.\n");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().Coordenadas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
