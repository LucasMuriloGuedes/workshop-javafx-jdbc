/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.entites.Seller;

/**
 *
 * @author Lucas Murilo
 */
public interface SellerDao {
    
    void insert(Seller seller);
    void update(Seller seller);
    void deleteById(int id);
    Seller findById(int id);
    List<Seller> findByIdDepartament(int id);
    List<Seller> findAll();
}
