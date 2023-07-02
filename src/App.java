public class App {
    public static void main(String[] args) throws Exception {

        Usuario usuario1 = new Usuario();
        usuario1.nombreUsuario = "openbootcamp";
        usuario1.nombre = "Open";
        usuario1.apellidos = "Bootcamp";
        usuario1.email = "ejemplos@open-bootcamp.com";
        usuario1.nivelAcceso = 1;

        Usuario usuario2 = new Usuario();
        usuario2.nombreUsuario = "openbootcamp2";
        usuario2.nombre = "Open";
        usuario2.apellidos = "Bootcamp";
        usuario2.email = "ejemplos@open-bootcamp.com";
        usuario2.nivelAcceso = 5;

        Usuario usuario3 = new Usuario();
        usuario3.nombreUsuario = "openbootcamp3";
        usuario3.nombre = "Open";
        usuario3.apellidos = "Bootcamp";
        usuario3.email = "ejemplos@open-bootcamp.com3";
        usuario3.nivelAcceso = 10;

        UsuariosDB usuariosDB;
        String tipo = "fichero";
        if (tipo.equalsIgnoreCase("fichero")) {
            usuariosDB = new UsuariosDBFichero();
        } else {
            usuariosDB = new UsuariosDBMemoria();
        }

        Usuarios usuarios = new Usuarios(usuariosDB);
        usuarios.insertar(usuario1);
        usuarios.insertar(usuario2);
        usuarios.insertar(usuario3);

        TIpoUSuario nivelUsuario = new TIpoUSuario(usuariosDB);
        System.out.println("\nNIVELES:");
        System.out.println("Admin: " + nivelUsuario.esAdministrador(usuario1));
        System.out.println("Admin: " + nivelUsuario.esAdministrador(usuario2));
        System.out.println("Admin: " + nivelUsuario.esAdministrador(usuario3));

        //usuarios.borrar("openbootcamp2");
        //usuarios.borrar("openbootcamp3");

        for (Usuario a : usuarios.listar()) {
            System.out.println(a);
        }

        imprimir(usuariosDB);

    }

    public static void imprimir(UsuariosDB usuariosDB) {
        if (usuariosDB instanceof UsuariosDBFichero) {
            System.out.println("Inserciones F: " + ((UsuariosDBFichero) usuariosDB).getInserciones());
            System.out.println("Eliminaciones F: " + ((UsuariosDBFichero) usuariosDB).getEliminaciones());
        } else if (usuariosDB instanceof UsuariosDBMemoria) {
            System.out.println("Inserciones M: " + ((UsuariosDBMemoria) usuariosDB).getInserciones());
            System.out.println("Eliminaciones M: " + ((UsuariosDBMemoria) usuariosDB).getEliminaciones());
        }

    }
}
