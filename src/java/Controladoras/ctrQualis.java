/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladoras;

import Entidades.Qualis;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlo
 */
public class ctrQualis
{
    public boolean salvar(String nome, InputStream arquivo, String ano, int dep_cod)
    {
        try 
        {
            return new Qualis(ano, arquivo, nome).salvar(dep_cod);
        }
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(ctrQualis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean apagar(String nome, String ano, int dep_cod)
    {
        return new Qualis(nome,ano).apagar(dep_cod);
    }
    
    public List<String> get(int cod)
    {
        return new Qualis().get(cod);
    }
}
