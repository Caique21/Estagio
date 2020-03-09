/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladoras;

import Entidades.Departamento;
import Entidades.Docente;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlo
 */
public class ctrDocente
{
    public List<String>get(int dep_cod)
    {
        return new Docente().getDocentes(dep_cod);
    }

    public boolean salvar(String docente, InputStream fileContent, int departamento)
    {
        return new Docente(new Departamento(departamento), docente, fileContent).salvar();
    }
    
    public boolean salvar(String docente, InputStream fileContent, int departamento, int codigo)
    {
        return new Docente(new Departamento(departamento), docente, codigo, fileContent).salvar();
    }

    public boolean getCurriculo(String docente,int departamento)
    {
        Docente d = new Docente(new Departamento(departamento), docente);
        return d.getCurriculo() != null;
    }
    
    public FileInputStream getArquivo(String docente)
    {
        InputStream is = new Docente(docente).getCurriculo();
        
        byte[] buffer;
        try 
        {
            if(is != null)
            {
                buffer = new byte[is.available()];
                is.read(buffer);
                File file = new File("D:/" + "Curriculo_"+docente);
                OutputStream outStream = new FileOutputStream(file.getAbsolutePath());
                outStream.write(buffer);
                
                return new FileInputStream(file);
            }
            else
                return null;
        }
        catch (IOException ex) 
        {
            Logger.getLogger(ctrQualis.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }        
    }

    public boolean apagar(String docente, int departamento)
    {
        Docente d = new Docente(new Departamento(departamento), docente);
        return d.apagar();
    }
    
    public boolean apagar(int codigo, int departamento)
    {
        Docente d = new Docente(new Departamento(departamento), codigo);
        return d.apagar();
    }
    
    public boolean consulta(int codigo)
    {
        Docente d = new Docente(codigo);
        return d == null;
    }
}
