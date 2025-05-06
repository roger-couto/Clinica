package br.csi.service;


import br.csi.dao.UsuarioDAO;
import br.csi.model.Usuario;

import java.util.ArrayList;

public class UsuarioService {

    private UsuarioDAO dao = new UsuarioDAO();

    public ArrayList<Usuario> getUsuarios() {
        return dao.getUsuarios();
    }

}
