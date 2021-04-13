/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.impl;

import db.DB;
import db.DbException;
import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.dao.SellerDao;
import model.entites.Departament;
import model.entites.Seller;

/**
 *
 * @author Lucas Murilo
 */
public class SellerDaoJDBC implements SellerDao{
    
    private  Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    
    @Override
    public void insert(Seller seller) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Seller seller) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Seller findById(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try{
            st = conn.prepareStatement(
                    "SELECT seller.*, department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE seller.Id = ?");
            
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                Departament departament = new Departament(rs.getInt("DepartamentId"), rs.getString("DepName"));
                Seller seller = instatiSeller(rs, departament);
                return seller;
                
            } 
        } 
        
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closePreparedStatment(st);
            DB.closeResultSet(rs);
        }
       
        return null;
        
    }

    @Override
    public List<Seller> findByIdDepartament(int id) {
      PreparedStatement st = null;
      ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT seller.*, department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE DepartmentId = ? "
                    + "ORDER BY Name ");
            st.setInt(1, id);
            rs = st.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Departament> map = new HashMap<>();
            
            while(rs.next()){
                Departament dep = map.get(rs.getInt("DepartamentId"));
                if(dep == null){
                    dep = new Departament(rs.getInt("DepartamentId"), rs.getString("DepName"));
                    map.put(rs.getInt("DepartamentId"), dep);
                }
                
                Seller seller = new Seller(rs, dep);
                list.add(seller);
            }
            
            return list;
            
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closePreparedStatment(st);
            DB.closeResultSet(rs);
        }        
    }

    @Override
    public List<Seller> findAll() {
     PreparedStatement st = null;
     ResultSet rs = null;
     
     try{
         st = conn.prepareStatement( 
                    "SELECT seller.*, department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "ORDER BY Name ");
         
        rs = st.executeQuery();
        List<Seller> list = new ArrayList<>();
        Map<Integer, Departament> map = new HashMap<>();
        Departament dep = map.get(rs.getInt("DepartamentId"));
       
        
        while(rs.next()){
            if(dep == null){
            dep = new Departament(rs.getInt("DepartamentId"), rs.getString("DepName"));
            map.put(rs.getInt("DepartamentId"), dep);
        }
            
            Seller sl = instatiSeller(rs, dep);
            list.add(sl);
     }
        return list;
        
    }
     catch(SQLException e){
         throw new DbException(e.getMessage());
     }
     finally{
         DB.closePreparedStatment(st);
         DB.closeResultSet(rs);
     }
    }
    
    private Seller instatiSeller(ResultSet rs, Departament dep) throws SQLException{
        Seller sl = new Seller();
        sl.setId(rs.getInt("Id"));
        sl.setName(rs.getString("Name"));
        sl.setEmail(rs.getString("Email"));
        sl.setBirthDate(rs.getDate("BirthDate"));
        sl.setBaseSalary(rs.getDouble("BaseSalary"));
        sl.setDepartament(dep);
        return sl;
        
    }
    
}
