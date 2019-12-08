/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Banco.Conexao;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private String curriculo;

    public Docente()
    {
    }

    public Docente(Departamento departamento, String nome, int codigo, String curriculo)
    {
        this.departamento = departamento;
        this.nome = nome;
        this.codigo = codigo;
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

    public String getCurriculo()
    {
        return curriculo;
    }

    public void setCurriculo(String curriculo)
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
    
    
}
