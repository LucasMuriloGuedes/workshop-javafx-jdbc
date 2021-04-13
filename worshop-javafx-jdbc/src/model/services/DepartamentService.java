/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.services;

import java.util.ArrayList;
import java.util.List;
import model.entites.Departament;

/**
 *
 * @author Lucas Murilo
 */
public class DepartamentService {
    
    public List<Departament> findAll(){
        
        List<Departament> list = new ArrayList<>();
        list.add(new Departament(1, "Livros"));
        list.add(new Departament(2, "Eletronicos"));
        list.add(new Departament(2, "Gourmet"));
        
        
        return list;
        
    }
    
}
