/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Banco.Conexao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlo
 */
public class Usuario
{
    private String usuario;
    private String senha;

    public Usuario()
    {
    }

    public Usuario(String usuario, String senha)
    {
        this.usuario = usuario;
        this.senha = senha;
    }
    
    public boolean getUsuario()
    {
        ResultSet rs = new Conexao().consultar("select * from usuario where usu_nome = '" + usuario + "' and usu_senha = '" + senha + "'");
        try 
        {
            if(rs.next())
                return true;
        }
        catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
