/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Banco.Conexao;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author carlo
 */
public class Docente
{
    private Departamento departamento;
    private String nome;
    private int codigo;
    private InputStream curriculo;

    public Docente()
    {
    }

    public Docente(int codigo) 
    {
        this.codigo = codigo;
       
        ResultSet rs = new Conexao().consultar("select docente_nome, departamento_id from docente where docente_id = " + codigo);
        try 
        {
            if(rs != null && rs.next())
            {
                this.nome = rs.getString("docente_nome");
                this.departamento = new Departamento(rs.getInt("departamento_id"));
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Docente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Docente(Departamento departamento, String nome, int codigo) 
    {
        this.departamento = departamento;
        this.nome = nome;
        this.codigo = codigo;
    }

    public Docente(Departamento departamento, String nome, int codigo, InputStream curriculo)
    {
        this.departamento = departamento;
        this.nome = nome;
        this.codigo = codigo;
        this.curriculo = curriculo;
    }

    public Docente(Departamento departamento, String nome, InputStream curriculo)
    {
        this.departamento = departamento;
        this.nome = nome;
        this.curriculo = curriculo;
    }

    public Docente(Departamento departamento, int codigo)
    {
        this.departamento = departamento;
        this.codigo = codigo;
    }

    public Docente(Departamento departamento, String nome)
    {
        this.departamento = departamento;
        this.nome = nome;
    }

    public Docente(String nome)
    {
        this.nome = nome;
    }

    public Departamento getDepartamento()
    {
        return departamento;
    }

    public void setDepartamento(Departamento departamento)
    {
        this.departamento = departamento;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public InputStream getCurriculo()
    {
        if(curriculo != null)
            return curriculo;
        else
        {
            ResultSet rs;
            if(departamento != null)
                rs = new Conexao().consultar("select docente_curriculo from docente where docente_nome = '" + nome + "' and departamento_id = " + departamento.getCodigo());
            else
                rs = new Conexao().consultar("select docente_curriculo from docente where docente_nome = '" + nome + "'");
                
            try 
            {
                if(rs != null && rs.next())
                {
                    this.curriculo = rs.getBinaryStream("docente_curriculo");
                    return curriculo;
                }
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(Qualis.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return null;
    }

    public void setCurriculo(InputStream curriculo)
    {
        this.curriculo = curriculo;
    }

    public List<String> getDocentes(int cod)
    {
        List<String>docentes = new ArrayList<>();
        
        ResultSet rs = new Conexao().consultar("select docente_nome from docente where departamento_id = " + cod);
        
        try 
        {
            while(rs != null && rs.next())
            {
                docentes.add(rs.getString("docente_nome"));
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(Docente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return docentes;
    }
    
    public boolean salvar()
    {
        String sql ="insert into docente (docente_id, docente_nome, departamento_id) values ($1,'$2',$3)";
        sql = sql.replace("$1", String.valueOf(codigo));
        sql = sql.replace("$2", nome);
        sql = sql.replace("$3", String.valueOf(departamento.getCodigo()));
        
        return new Conexao().manipular(sql);
    }

    public boolean alterar()
    {
        ResultSet rs = new Conexao().consultar("select docente_curriculo, docente_id from docente where departamento_id = " + departamento.getCodigo() + " and docente_nome = '" + nome + "'");
        try 
        {
            if(rs.next())
            {
                String sql = "update docente set docente_curriculo = ? where docente_id = ?";
        
                Conexao con = new Conexao();
                PreparedStatement p = con.getPreparedStatement(sql);

                try 
                {
                    p.setBinaryStream(1, curriculo);
                    p.setInt(2, rs.getInt("docente_id"));

                    p.executeUpdate();
                    return true;
                }
                catch(SQLException e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(Docente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean apagar()
    {
        String sql = "delete from docente where docente_id = $1 and departamento_id = $2";
        
        sql = sql.replace("$1", String.valueOf(codigo));
        sql = sql.replace("$2", String.valueOf(departamento.getCodigo()));
        
        return new Conexao().manipular(sql);
    }
    
    
}
