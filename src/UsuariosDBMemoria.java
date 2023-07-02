import java.util.ArrayList;

public class UsuariosDBMemoria implements UsuariosDB, UsuariosDBEstadistica {
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private int eliminaciones = 0;
    private int inserciones = 0;



    @Override
    public ArrayList<Usuario> obtener() {
        return usuarios;
    }

    @Override
    public Usuario buscar(Usuario usuario) {
        for (Usuario usuarioActual : usuarios) {
            if (usuarioActual.nombreUsuario.equalsIgnoreCase(usuario.nombreUsuario)) {
                return usuarioActual;
            }
        }

        return null;
    }

    @Override
    public void insertar(Usuario usuario) {
        if (buscar(usuario) != null) {
            return;
        }
        usuarios.add(usuario);
        inserciones++;
    }

    @Override
    public void borrar(Usuario usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).nombreUsuario.equalsIgnoreCase(usuario.nombreUsuario)) {
                usuarios.remove(i);
                eliminaciones++;
            }

        }
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
