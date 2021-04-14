/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.services;

import java.util.ArrayList;
import java.util.List;
import model.dao.DaoFactory;
import model.dao.DepartamentDao;
import model.entites.Departament;

/**
 *
 * @author Lucas Murilo
 */
public class DepartamentService {
    
    private DepartamentDao dao = DaoFactory.createDepartmentDao();
    
    public List<Departament> findAll(){
        
        return dao.findAll();    
    }
    
}
