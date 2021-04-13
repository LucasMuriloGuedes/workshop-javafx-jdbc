/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.entites.Departament;

/**
 *
 * @author Lucas Murilo
 */
public interface DepartamentDao {
    
    void insert(Departament departament);    
    void update(Departament departament);
    void delete(int id);
    Departament findByIdDepartament(int id);
    List<Departament> findAll();
    
    
}
