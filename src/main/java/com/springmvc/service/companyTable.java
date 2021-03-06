package com.springmvc.service;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.springmvc.entity.Company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class companyTable {
    /*
create table companyTable(
originalArea varchar(50) NOT NULL,
id int NOT NULL primary key,
name varchar(50) NOT NULL,
enterprisesNature varchar(50) NOT NULL,
industry varchar(50) NOT NULL,
mainBusiness varchar(50) NOT NULL,
People varchar(50) NOT NULL,
Address varchar(50) NOT NULL,
postalCode varchar(50) NOT NULL,
telephone varchar(50) NOT NULL,
fax varchar(50) NOT NULL,
email varchar(50)) default charset = utf8;
 * */
    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/company";
            String username = "root";
            String password = "22003x";
            conn = DriverManager.getConnection(url, username, password);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void insert(Company company){
        if(find(company.id)) {
            String sql = "insert into companyTable(originalArea,id,name,enterprisesNature,industry,"
                    + "mainBusiness,People,Address,postalCode,telephone,"
                    + "fax,email"
                    + ") values(?,?,?,?,?,?,?,?,?,?,?,?)";
            System.out.println(sql);
            try {
                Connection conn = getConnection();
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
                ps.setString(1, company.originalArea);
                ps.setInt(2, company.id);
                ps.setString(3, company.name);
                ps.setString(4, company.enterprisesNature);
                ps.setString(5, company.industry);
                ps.setString(6, company.mainBusiness);
                ps.setString(7, company.People);
                ps.setString(8, company.Address);
                ps.setString(9, company.postalCode);
                ps.setString(10, company.telephone);
                ps.setString(11, company.fax);
                ps.setString(12, company.email);
                ps.executeUpdate();
                ps.close();
                conn.close();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("failed");
        }
    }

    public boolean find(int id) {
        String sql = "select * from companyTable where id=" + id;
        System.out.println(sql);
        int ch = 0;
        try {
            Connection conn = getConnection();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            Statement stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                ch++;
            }
            rs.close();
            stmt.close();
            ps.close();
            conn.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        if(ch == 0) {
            return true;
        }else {
            return false;
        }
    }

    public boolean updateS(int id, String name, String data){
        try {
            Connection conn = getConnection();
            String sql = "update companyTable set " + name + "=? where id=?";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, data);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateI(int id, String name, int data) {
        try {
            Connection conn = getConnection();
            String sql = "update companyTable set " + name + "=? where id=?";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setInt(1, data);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
