/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.impl;

import db.DB;
import db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.dao.DepartamentDao;
import model.entites.Departament;

/**
 *
 * @author Lucas Murilo
 */
public class DepartamentDaoJDBC implements DepartamentDao {
    
    private Connection conn;

    public DepartamentDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    
    

    @Override
    public void insert(Departament departament) {
        PreparedStatement st = null;
        
        try{
            st = conn.prepareStatement(
                    "INSERT INTO department " +
				"(Name) " +
				"VALUES " +
				"(?)", 
				Statement.RETURN_GENERATED_KEYS);
            st.setString(1, departament.getName());
            int rowsAfeccted = st.executeUpdate();
            if(rowsAfeccted > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    departament.setId(id);
                }
            }
            else {
		throw new DbException("Unexpected error! No rows affected!");
        }
    }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(Departament departament) {
       PreparedStatement st = null;
       try{
           st = conn.prepareStatement("UPDATE department " +
				"SET Name = ? " +
				"WHERE Id = ?");
           st.setInt(1, departament.getId());
           st.setString(2, departament.getName());
           st.executeUpdate();
       }
       catch(SQLException e){
           throw new DbException(e.getMessage());
           
       }
       finally{
           DB.closePreparedStatment(st);
       }
    }

    @Override
    public void delete(int id) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "DELETE FROM department WHERE Id = ?");
            
            st.setInt(1, id);
            st.executeUpdate();
        }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closePreparedStatment(st);
        }
    }

    @Override
    public Departament findByIdDepartament(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT * FROM department WHERE Id = ?"); 
            
            st.setInt(1, id);
            rs = st.executeQuery();
            Departament dep;
            if(rs.next()){
                dep = new Departament(rs.getInt("Id"), rs.getString("Name"));
                return dep;
            }  
         return null;
        }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closePreparedStatment(st);
            DB.closeResultSet(rs);
        } 
    }

    @Override
    public List<Departament> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try{
            st = conn.prepareStatement(
            "SELECT * FROM department ORDER BY Name");
            rs = st.executeQuery();
            List<Departament> list = new ArrayList<>();
            while (rs.next()) {
                Departament dep = new Departament(rs.getInt("Id"), rs.getString("Name"));
                list.add(dep);
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
    
}
