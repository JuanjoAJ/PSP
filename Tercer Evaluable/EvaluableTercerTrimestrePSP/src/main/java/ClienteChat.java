// ClienteChat.java
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class ClienteChat {
    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter salida;
    private JTextArea areaMensajes;
    private JTextField campoEntrada;
    private JComboBox<String> comboUsuarios;

    public ClienteChat(String direccionServidor) throws IOException {
        socket = new Socket(direccionServidor, 5000);
        entrada = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );
        salida = new PrintWriter(socket.getOutputStream(), true);

        String nombreUsuario = obtenerNombreDeUsuario();
        salida.println(nombreUsuario);

        construirInterfaz();

        new Thread(() -> {
            try {
                String mensajeServidor;
                while ((mensajeServidor = entrada.readLine()) != null) {
                    if (mensajeServidor.startsWith("/usuarios")) {
                        String[] partes = mensajeServidor.split(" ");
                        if (partes.length > 1) {
                            String[] usuarios = partes[1].split(",");
                            SwingUtilities.invokeLater(() -> {
                                comboUsuarios.removeAllItems();
                                for (String u : usuarios) {
                                    comboUsuarios.addItem(u);
                                }
                            });
                        }
                    } else {
                        areaMensajes.append(mensajeServidor + "\n");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private String obtenerNombreDeUsuario() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel etiqueta = new JLabel("Introduce tu nombre de usuario:");
        JTextField campoNombre = new JTextField(15);
        panel.add(etiqueta, BorderLayout.NORTH);
        panel.add(campoNombre, BorderLayout.CENTER);

        int resultado = JOptionPane.showConfirmDialog(
                null, panel, "Login",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );
        if (resultado == JOptionPane.OK_OPTION) {
            return campoNombre.getText().trim();
        } else {
            System.exit(0);
            return null;
        }
    }

    private void construirInterfaz() {
        JFrame ventana = new JFrame("Cliente de Chat");
        ventana.setSize(500, 500);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        areaMensajes = new JTextArea();
        areaMensajes.setEditable(false);
        JScrollPane scrollMensajes = new JScrollPane(areaMensajes);

        campoEntrada = new JTextField();
        JButton botonEnviar = new JButton("Enviar");
        botonEnviar.addActionListener(e -> {
            String msg = campoEntrada.getText().trim();
            if (!msg.isEmpty()) {
                salida.println(msg);
                areaMensajes.append("Yo: " + msg + "\n");
                campoEntrada.setText("");
            }
        });

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.add(campoEntrada, BorderLayout.CENTER);
        panelInferior.add(botonEnviar, BorderLayout.EAST);

        comboUsuarios = new JComboBox<>();
        comboUsuarios.setPreferredSize(new Dimension(150, 25));
        comboUsuarios.addActionListener(e -> {
            String sel = (String) comboUsuarios.getSelectedItem();
            if (sel != null && !sel.isEmpty()) {
                campoEntrada.setText("@" + sel + " ");
                campoEntrada.requestFocus();
            }
        });

        JPanel panelUsuarios = new JPanel(new BorderLayout());
        panelUsuarios.add(new JLabel("Usuarios conectados:"), BorderLayout.NORTH);
        panelUsuarios.add(comboUsuarios, BorderLayout.CENTER);
        panelUsuarios.setPreferredSize(new Dimension(150, 50));

        ventana.add(scrollMensajes, BorderLayout.CENTER);
        ventana.add(panelInferior, BorderLayout.SOUTH);
        ventana.add(panelUsuarios, BorderLayout.NORTH);

        ventana.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            new ClienteChat("localhost");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
