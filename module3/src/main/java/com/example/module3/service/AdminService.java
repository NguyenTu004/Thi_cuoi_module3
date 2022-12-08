package com.example.module3.service;

import com.example.module3.DAO.AdminDAO;
import com.example.module3.model.Department;
import com.example.module3.model.Staff;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class AdminService {
    private AdminDAO adminDAO;

    public AdminService() {
        adminDAO = new AdminDAO();
    }
    public void create(HttpServletRequest request){
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        double salary = Double.parseDouble(request.getParameter("salary"));
        Long departmentId = Long.parseLong(request.getParameter("department"));
        adminDAO.createStaff(new Staff(name, email,address,phone,salary, departmentId));
    }
    public List<Staff> findAllStaff(){
        return adminDAO.findAllStaff();
    }
    public List<Department> findAllDepartment(){
        return adminDAO.findAllDepartment();
    }
    public Map<Long,Department> listMapDepartment(){
        return adminDAO.listMapDepartment();
    }
    public Staff findByIdStaff(HttpServletRequest request){
        long id = Long.parseLong(request.getParameter("id"));
        return adminDAO.findByIdStaff(id);
    }
    public boolean deleteStaff(HttpServletRequest request){
        long id = Long.parseLong(request.getParameter("id"));
        return adminDAO.deleteStaff(id);
    }
    public void updateStaff(HttpServletRequest request){
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        double salary = Double.parseDouble(request.getParameter("salary"));
        long departmentId = Long.parseLong(request.getParameter("department"));
        adminDAO.updateStaff(new Staff(id,name, email,address,phone,salary, departmentId));
    }
    public List<Staff> listSearch(HttpServletRequest request){
        String search = request.getParameter("search");
        return adminDAO.searchStaff(search);
    }
}
