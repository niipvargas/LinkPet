
package DAO;

import Model.Usuario;
import Utils.ConexaoDoBanquinho;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuarioDAO {
    
    static public int InserirUsuarioDB(Usuario usuario){
        String sql = "INSERT INTO cadastro_pessoa (nome, endereco, telefone) VALUES (?,?,?)";
        Connection conexao = ConexaoDoBanquinho.CriarConexao(); // Conexao do banco 
        try{
            PreparedStatement stm;
            stm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, usuario.getNome());
            stm.setString(2, usuario.getEndereco());
            stm.setString(3, usuario.getTelefone());
            stm.execute();
            ResultSet rs = stm.getGeneratedKeys();
            rs.next();
            return rs.getInt(1); 
    }catch(SQLException e){   
            System.out.println("NÃO FOI POSSIVEL FAZER A INSERÇÃO DO USUARIO" + e);
    }
        return 0;
    }
    
    static public ArrayList<Usuario> BuscarTodosUsuarios() {
        String sql = "SELECT * FROM cadastro_pessoa ORDER BY codigo";
        Connection conexao = ConexaoDoBanquinho.CriarConexao();
        ArrayList listaUsuario = new ArrayList();
        
        try{
            PreparedStatement stm = conexao.prepareStatement(sql);
            ResultSet resultado = stm.executeQuery();
            while(resultado.next()){
                int codigo = resultado.getInt("codigo");
                String nome = resultado.getString("nome");
                String endereco = resultado.getString("endereco");
                String telefone = resultado.getString("telefone");
                Usuario p1 = new Usuario (codigo, nome, endereco, telefone);
                listaUsuario.add(p1);
            }
            return listaUsuario;
        }catch(SQLException e){
            System.out.println("NÃO FOI POSSIVEL BUSCAR DADOS" + e);
        }
        return listaUsuario;
    }
    
    public static void DeletarUsuarioPorCodigo(int codigo){
        String sql = "DELETE FROM  usuario WHERE codigo = ?";
        Connection conexao  = ConexaoDoBanquinho.CriarConexao();
        try{
            PreparedStatement stm  = conexao.prepareStatement(sql);
            stm.setInt(1, codigo);
            stm.executeUpdate();
        }catch (SQLException erro) {
            System.out.println("DEU ERRO AO DELETAR" + erro);
        }
    }

    public static void AtualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, endereco = ?, telefone = ? = ? WHERE codigo = ?";
        Connection conexao = ConexaoDoBanquinho.CriarConexao();
        try{
            PreparedStatement bla = conexao.prepareStatement(sql);
            bla.setString(1, usuario.getNome());
            bla.setString(2, usuario.getEndereco());
            bla.setString(3, usuario.getTelefone());
            bla.setInt(4, usuario.getCodigo());
            bla.executeUpdate();
        }catch(SQLException erro) {
            System.out.println("ERRO AO ATUALIZAR" + erro);
        }
    }
   
    }


    

