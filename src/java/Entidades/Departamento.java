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
public class Departamento
{
    private int codigo;
    private String nome;

    public Departamento()
    {
    }

    public Departamento(int codigo, String nome)
    {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Departamento(int codigo)
    {
        this.codigo = codigo;
        if(this.codigo != 0)
            buscaNome();
    }

    public Departamento(String nome)
    {
        this.nome = nome;
        if(this.nome != null && !this.nome.equals(""))
            buscaCodigo();
    }

    public int getCodigo()
    {
        return codigo;
    }
    
    public void buscaCodigo()
    {
        ResultSet rs = new Conexao().consultar("select departamento_id from departamento where departamento_nome like '%" + nome + "%'");
        
        try 
        {
            if(rs != null && rs.next())
            {
                this.codigo = rs.getInt("departamento_id");
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public String getNome()
    {
        return nome;
    }
    
    public void buscaNome()
    {
        ResultSet rs = new Conexao().consultar("select departamento_nome from departamento where departamento_id = " + codigo);
        
        try 
        {
            if(rs != null && rs.next())
            {
                this.nome = rs.getString("departamento_nome");
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }
    
    
}
