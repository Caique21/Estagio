/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladoras;

import Entidades.Usuario;

/**
 *
 * @author carlo
 */
public class ctrLogin
{
    public boolean getUsuario(String usuario, String senha)
    {
        return new Usuario(usuario, senha).getUsuario();
    }
}
