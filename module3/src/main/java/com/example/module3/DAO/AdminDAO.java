package com.example.module3.DAO;

import com.example.module3.connection.MyConnection;
import com.example.module3.model.Department;
import com.example.module3.model.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminDAO {
    private Connection connection ;
    private final String SELECT_ALL_STAFF = "select * from staff;";
    private final String SELECT_ALL_DEPARTMENT = "select * from department;";
    private final String INSERT_INTO_STAFF = "insert into staff (name,email,address,phone,salary,departmentId)\n" +
                                                    "value ( ? , ? , ? , ? , ? , ? );";
    private final String UPDATE_STAFF = "update staff set name = ?,email = ?,address = ?,phone = ?,salary = ?,departmentId = ?\n" +
                                        "where id = ?;";
    private final String DELETE_STAFF = "delete from staff where id = ? ;";
    private final String SEARCH_STAFF = "select * from staff where name like concat('%',?,'%') ;";
    private final String SELECT_STAFF = "select * from staff where id = ?;";
    public AdminDAO() {
        connection = MyConnection.getConnection();
    }
    public List<Staff> findAllStaff(){
        List<Staff> listStaff = new ArrayList<Staff>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_STAFF)){
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                double salary = rs.getDouble("salary");
                long departmentId = rs.getLong("departmentId");
                listStaff.add(new Staff(id,name,email,address,phone,salary, departmentId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listStaff;
    }
    public List<Department> findAllDepartment(){
        List<Department> listDepartment = new ArrayList<Department>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_DEPARTMENT)){
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                String name = rs.getString("name");
                listDepartment.add(new Department(id,name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDepartment;
    }
    public Map<Long,Department> listMapDepartment(){
        Map<Long,Department> listDepartment = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_DEPARTMENT)){
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                String name = rs.getString("name");
                listDepartment.put(id,new Department(id,name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDepartment;
    }
    public void createStaff(Staff staff){
        try (PreparedStatement statement = connection.prepareStatement(INSERT_INTO_STAFF)){
            statement.setString(1,staff.getName());
            statement.setString(2,staff.getEmail());
            statement.setString(3,staff.getAddress());
            statement.setString(4,staff.getPhone());
            statement.setDouble(5,staff.getSalary());
            statement.setLong(6,staff.getDepartmentId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateStaff(Staff staff){
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_STAFF)){
            statement.setString(1,staff.getName());
            statement.setString(2,staff.getEmail());
            statement.setString(3,staff.getAddress());
            statement.setString(4,staff.getPhone());
            statement.setDouble(5,staff.getSalary());
            statement.setLong(6,staff.getDepartmentId());
            statement.setLong(7,staff.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean deleteStaff(Long id){
        try (PreparedStatement statement = connection.prepareStatement(DELETE_STAFF)){
            statement.setLong(1,id);
            return statement.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<Staff> searchStaff(String search){
        List<Staff> listStaff = new ArrayList<Staff>();
        try (PreparedStatement statement = connection.prepareStatement(SEARCH_STAFF)){
            statement.setString(1, search);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                double salary = rs.getDouble("salary");
                long departmentId = rs.getLong("departmentId");
                listStaff.add(new Staff(id,name,email,address,phone,salary, departmentId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listStaff;
    }
    public Staff findByIdStaff(Long staffId){
        try (PreparedStatement statement = connection.prepareStatement(SELECT_STAFF)){
            statement.setLong(1, staffId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                double salary = rs.getDouble("salary");
                long departmentId = rs.getLong("departmentId");
                return new Staff(id,name,email,address,phone,salary, departmentId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
