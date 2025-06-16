import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VentanaCliente extends JFrame {
    private JPanel contentPane;
    private JTextField textField;
    private Client client;

    public VentanaCliente() {
        setTitle("Lotería Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(237, 230, 80));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblWelcome = new JLabel("Bienvenido a la lotería");
        lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblWelcome.setBounds(125, 11, 203, 42);
        contentPane.add(lblWelcome);

        JLabel lblInstruccion = new JLabel("Introduce tu número de lotería");
        lblInstruccion.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblInstruccion.setBounds(115, 91, 219, 20);
        contentPane.add(lblInstruccion);

        textField = new JTextField();
        textField.setToolTipText("Introduce un número de 5 dígitos");
        textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textField.setBounds(115, 122, 219, 33);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnEnviar = new JButton("COMPROBAR");
        btnEnviar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnEnviar.setBounds(174, 196, 115, 33);
        contentPane.add(btnEnviar);

        // Iniciamos el cliente y conectamos al servidor
        try {
            client = new Client(8080);
            client.start();
        } catch (IOException e) {
            System.out.println("Fallo en la conexión con el cliente");
            JOptionPane.showMessageDialog(this, "Error al conectar con el servidor", "Error", JOptionPane.ERROR_MESSAGE);
        }

        //Añadimos la acción del botón
        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroLoteria = textField.getText().trim(); //quitamos los posibles espacios que deje el cliente sin querer
                // Enviar mensaje al servidor y recibir respuesta
                String respuesta = client.enviarMensaje(numeroLoteria);
                JOptionPane.showMessageDialog(contentPane, respuesta, "Resultado", JOptionPane.INFORMATION_MESSAGE);
                try {
                    client.close(); //Cerramos la conexión
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(contentPane, "Error al cerrar la conexión", "Error", JOptionPane.ERROR_MESSAGE);
                }
                // Finalizamos la ventana
                System.exit(0);
            }

        });
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VentanaCliente frame = new VentanaCliente();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
