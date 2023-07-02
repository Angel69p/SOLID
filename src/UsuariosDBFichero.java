import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UsuariosDBFichero implements UsuariosDB, UsuariosDBEstadistica {
    public String ficheroDatos = "usuarios.txt";
    private int eliminaciones = 0;
    private int inserciones = 0;

    public UsuariosDBFichero() {
    }

    @Override
    public ArrayList<Usuario> obtener() {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            Scanner sc = new Scanner(new File(ficheroDatos));

            while (sc.hasNext()) {
                String usuarioActual = sc.next();
                String[] partes = usuarioActual.split(",");

                Usuario usuario = new Usuario();
                usuario.nombreUsuario = partes[0];
                usuario.nombre = partes[1];
                usuario.apellidos = partes[2];
                usuario.email = partes[3];
                usuario.nivelAcceso = Integer.parseInt(partes[4]);

                usuarios.add(usuario);
            }
            sc.close();
        } catch (Exception e) {
        }
        return usuarios;
    }

    @Override
    public void insertar(Usuario usuario) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(ficheroDatos, true);
            PrintStream printStream = new PrintStream(fileOutputStream);
            printStream.println(separarUsuarioPorComas(usuario));
            printStream.flush();
              inserciones++;
            printStream.close();
           
        } catch (Exception e) {
        }

    }

    private String separarUsuarioPorComas(Usuario usuario) {
        return usuario.nombreUsuario + ","
                + usuario.nombre + ","
                + usuario.apellidos + ","
                + usuario.email + ","
                + usuario.nivelAcceso;
    }

    @Override
    public void borrar(Usuario usuario) {
        ArrayList<Usuario> usuarios = obtener();

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).nombreUsuario.equalsIgnoreCase(usuario.nombreUsuario)) {
                usuarios.remove(i);
                eliminaciones++;

            }
        }

        try {
            PrintStream printStream = new PrintStream(ficheroDatos);

            for (Usuario usuarioActual : usuarios) {
                String usuarioComas = separarUsuarioPorComas(usuarioActual);
                printStream.println(usuarioComas);
                
            }
            printStream.flush();
            printStream.close();

        } catch (Exception e) {
        }
    }

    @Override
    public Usuario buscar(Usuario usuario) {
        ArrayList<Usuario> usuarios = obtener();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).nombreUsuario.equalsIgnoreCase(usuario.nombreUsuario)) {
                return usuario;
            }
        }

        return null;
    }

    @Override
    public int getInserciones() {
        return inserciones;
    }

    @Override
    public int getEliminaciones() {
        return eliminaciones;
    }

}
