/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladoras;

import Entidades.Departamento;

/**
 *
 * @author carlo
 */
public class ctrDepartamento
{
    public int getCodigo(String nome)
    {
        Departamento dep = new Departamento(nome);
        return dep.getCodigo();
    }
}
