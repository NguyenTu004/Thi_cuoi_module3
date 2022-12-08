package com.example.module3.servlet;

import com.example.module3.service.AdminService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "adminServlet", value = "/adminServlet")
public class adminServlet extends HttpServlet {
    private AdminService adminService = new AdminService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add" : displayAdd(request,response);break;
            case "delete" : displayDelete(request,response);break;
            case "update" : displayUpdate(request,response);break;
            default:home(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": create(request,response); break;
            case "update" : update(request,response);break;
            case "search" : search(request,response);break;
            default:
        }
    }
    private void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
        adminService.create(request);
        response.sendRedirect("/adminServlet");
    }
    private void home(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view.jsp");
        request.setAttribute("listStaff", adminService.findAllStaff());
        request.setAttribute("listMapDepartment", adminService.listMapDepartment());
        requestDispatcher.forward(request,response);
    }
    private void displayAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("create.jsp");
        request.setAttribute("listDepartment", adminService.findAllDepartment());
        requestDispatcher.forward(request,response);
    }
    private void displayUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("update.jsp");
        request.setAttribute("staff", adminService.findByIdStaff(request));
        request.setAttribute("listMapDepartment", adminService.listMapDepartment());
        request.setAttribute("listDepartment", adminService.findAllDepartment());
        requestDispatcher.forward(request,response);
    }
    private void displayDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view.jsp");
        if (adminService.deleteStaff(request)){
            request.setAttribute("notification", "Successful delete");
        }
        request.setAttribute("listStaff", adminService.findAllStaff());
        request.setAttribute("listMapDepartment", adminService.listMapDepartment());
        requestDispatcher.forward(request,response);
    }
    private void update(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        adminService.updateStaff(request);
        response.sendRedirect("/adminServlet");
    }
    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view.jsp");
        request.setAttribute("listStaff", adminService.listSearch(request));
        request.setAttribute("listMapDepartment", adminService.listMapDepartment());
        requestDispatcher.forward(request,response);
    }
}
