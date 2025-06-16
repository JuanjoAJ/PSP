import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.Semaphore;

public class HiloCliente implements Runnable {
    private Socket socket;
    private PrintWriter salida;
    private BufferedReader entrada;
    private String nombreUsuario;
    private List<HiloCliente> usuarios;
    private Semaphore semaforo;

    public HiloCliente(Socket socket,
                       List<HiloCliente> usuarios,
                       Semaphore semaforo) {
        this.socket = socket;
        this.usuarios = usuarios;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        try {
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            salida = new PrintWriter(socket.getOutputStream(), true);

            // 1) Pedimos y leemos el nombre del usuario
            salida.println("Por favor, introduce tu nombre:");
            nombreUsuario = entrada.readLine();

            // 2) Sección crítica: agregar usuario a la lista y notificar
            semaforo.acquire();
            try {
                usuarios.add(this);
                enviarListaUsuarios(); // actualiza la lista de usuarios conectados
                enviarATodos(nombreUsuario + " se ha unido al chat.", false);
            } finally {
                semaforo.release();
            }

            // 3) Escuchar y procesar mensajes del cliente
            String mensaje;
            while ((mensaje = entrada.readLine()) != null) {
                procesarMensaje(mensaje);
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Cliente desconectado. Mensaje: " + e.getMessage());
        } finally {
            desconectar();
        }
    }

    /**
     * Procesa los mensajes entrantes del cliente.
     * Si el mensaje comienza con '@', lo trata como mensaje privado.
     * Si no, lo reenvía a todos los demás usuarios.
     */
    private void procesarMensaje(String mensaje) {
        try {
            semaforo.acquire();
            try {
                if (mensaje.startsWith("@")) {
                    int espacio = mensaje.indexOf(" ");
                    if (espacio > 1) {
                        String destinatario = mensaje.substring(1, espacio);
                        String privado = mensaje.substring(espacio + 1);
                        if (enviarAUsuario(destinatario, nombreUsuario + " (privado): " + privado)) {
                            guardarMensajeEnArchivo(nombreUsuario + " -> " + destinatario + " (privado): " + privado);
                        }
                    }
                } else {
                    enviarATodos(nombreUsuario + ": " + mensaje, false);
                    guardarMensajeEnArchivo(nombreUsuario + ": " + mensaje);
                }
            } finally {
                semaforo.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina al usuario de la lista y notifica su salida.
     */
    private void desconectar() {
        try {
            semaforo.acquire();
            try {
                usuarios.remove(this);
                enviarListaUsuarios();
                enviarATodos(nombreUsuario + " ha salido del chat.", false);
            } finally {
                semaforo.release();
            }
            socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Envía la lista actual de usuarios a todos los clientes.
     * Se envía en el formato "/usuarios usuario1,usuario2,..."
     */
    private void enviarListaUsuarios() {
        StringBuilder sb = new StringBuilder("/usuarios ");
        for (HiloCliente mc : usuarios) {
            sb.append(mc.getNombreUsuario()).append(",");
        }
        if (!usuarios.isEmpty()) sb.deleteCharAt(sb.length() - 1); // quitar la última coma
        enviarATodos(sb.toString(), true); // incluir a todos
    }

    /**
     * Envía un mensaje a todos los usuarios conectados.
     */
    private void enviarATodos(String msg, boolean incluirRemitente) {
        for (HiloCliente mc : usuarios) {
            if (incluirRemitente || mc != this) {
                mc.enviarMensaje(msg);
            }
        }
    }

    /**
     * Envía un mensaje privado a un usuario específico.
     */
    private boolean enviarAUsuario(String nombreDestino, String msg) {
        for (HiloCliente mc : usuarios) {
            if (mc.getNombreUsuario().equals(nombreDestino)) {
                mc.enviarMensaje(msg);
                return true;
            }
        }
        return false;
    }

    /**
     * Envía un mensaje a este cliente.
     */
    public void enviarMensaje(String msg) {
        salida.println(msg);
    }

    /**
     * Guarda un mensaje en el archivo "mensajes.txt" de forma sincronizada.
     */
    private synchronized void guardarMensajeEnArchivo(String mensaje) {
        try (FileWriter fw = new FileWriter("mensajes.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
}
