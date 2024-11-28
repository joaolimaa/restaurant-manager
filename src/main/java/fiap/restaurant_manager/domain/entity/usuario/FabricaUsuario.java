import fiap.restaurant_manager.domain.entity.usuario.Usuario;

public class FabricaUsuario {
    private Usuario usuario;

    public Usuario comNomeCpfNascimento(String nome, String cpf){
        this.usuario = new Usuario(cpf, nome, "");
        return this.usuario;
    }


}