/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladoras;

import Entidades.Docente;
import java.util.List;

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
}
