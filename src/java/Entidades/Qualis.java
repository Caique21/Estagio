/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Banco.Conexao;
import Controladoras.ctrQualis;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
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
public class Qualis
{

    private String periodo;
    private String nome;
    private InputStream arquivo;

    public Qualis()
    {
        
    }
    
    public Qualis(String periodo, InputStream arquivo, String nome)
    {
        this.periodo = periodo;
        this.arquivo = arquivo;
        this.nome = nome;
    }

    public Qualis(String nome, String ano)
    {
        this.nome = nome;
        this.periodo = ano;
    }

    public String getPeriodo()
    {
        return periodo;
    }

    public void setPeriodo(String periodo)
    {
        this.periodo = periodo;
    }

    public InputStream getArquivo()
    {
        return arquivo;
    }

    public void setArquivo(InputStream arquivo)
    {
        this.arquivo = arquivo;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }
    
    public boolean salvar(int cod) throws FileNotFoundException
    {
        String sql = "insert into qualis (departamento_id,qualis_ano,qualis_documento, qualis_nome) values (?,?,?,?)";
        
        Conexao con = new Conexao();
        PreparedStatement p = con.getPreparedStatement(sql);
        
        try 
        {
            p.setInt(1, cod);
            p.setString(2, periodo);
            p.setBinaryStream(3, arquivo);
            p.setString(4, nome);
            
            p.executeUpdate();
            return true;
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(ctrQualis.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public List<String> get(int cod)
    {
        List<String>list = new ArrayList<>();
        
        ResultSet rs = new Conexao().consultar("select * from qualis where departamento_id = " + cod);
        
        try 
        {
            while(rs != null && rs.next())
            {
                list.add(new String(rs.getString("qualis_nome") + ";" + rs.getString("qualis_ano")));
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(ctrQualis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean apagar(int dep_cod)
    {
        String sql = "delete from qualis where qualis_nome = '$1', qualis_ano = '$2', departamento_id = $3";
        sql = sql.replace("$1", nome);
        sql = sql.replace("$2", periodo);
        sql = sql.replace("$3", String.valueOf(dep_cod));
        
        return new Conexao().manipular(sql);
    }
}
/*
    public void ler()
    {
        FileInputStream fileInput = null;
        try 
        {
            File file = new File(periodo);
            fileInput = new FileInputStream(file);
            try 
            {
                XSSFWorkbook worbook = new XSSFWorkbook(fileInput);
                XSSFSheet sheet = worbook.getSheetAt(0);
                
                Iterator<Row> ri = sheet.iterator();
                
                while(ri.hasNext())
                {
                    Row row = ri.next();
                    
                    Iterator<Cell> rc = row.iterator();
                    
                    while(rc.hasNext())
                    {
                        Cell cell = rc.next();
                        
                        switch(cell.getCellType())
                        {
                            case Cell.
                        }
                    }
                }
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(Qualis.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(Qualis.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally 
        {
            try 
            {
                fileInput.close();
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(Qualis.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }*/