package controller;

import dao.ClassesDAO;
import dao.StudentDAO;
import model.Classes;
import model.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet(name = "SchoolServlet", urlPatterns = "/school")
public class SchoolServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO;
    private ClassesDAO classesDAO;
    public void init(){
        studentDAO = new StudentDAO();
        classesDAO = new ClassesDAO();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        try {
            switch (action) {
                case "add" :
                    addNewStudent(request,response);
                    break;
                case "edit":
                    editStudentInformation(request,response);
                    break;
                case "delete":
                    deleteStudent(request,response);
                    break;
                case "addClass":
                    addNewClass(request,response);
                    break;
                case "editClass":
                    editClassInformation(request,response);
                    break;
                case "deleteClass":
                    deleteClass(request,response);
                    break;
            }
        } catch (SQLException ex ){
                throw new ServletException(ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        try {
            switch (action){
                case "add" :
                    showFormAddStudent(request,response);
                    break;
                case "edit":
                    showFormEditStudent(request,response);
                    break;
                case "delete":
                    showFormDeleteStudent(request,response);
                    break;
                case "class":
                    listClass(request,response);
                    break;
                case "addClass":
                    showFormAddClass(request,response);
                case "editClass":
                    showFormEditClass(request,response);
                    break;
                case "deleteClass":
                    showDeleteFormClass(request,response);
                    break;
                default :
                    listStudent(request,response);
                    break;
            }
        } catch (SQLException ex){
            throw new ServletException(ex);
        }
    }

    private void listStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> studentList = studentDAO.selectAllStudent();
        request.setAttribute("studentList",studentList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/list.jsp");
        dispatcher.forward(request,response);
    }
    private void listClass(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        List<Classes> classesList = classesDAO.selectAllClasses();
        request.setAttribute("classesList",classesList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("classes/list.jsp");
        dispatcher.forward(request,response);
    }
    private void showFormEditStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student existingStudent = studentDAO.selectStudent(id);
        RequestDispatcher dispatcher =request.getRequestDispatcher("student/edit.jsp");
        request.setAttribute("student",existingStudent);
        dispatcher.forward(request,response);
    }
    private void showFormEditClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("classId"));
        Classes existingClass = classesDAO.selectClasses(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("classes/edit.jsp");
        request.setAttribute("classes",existingClass);
        dispatcher.forward(request,response);
    }
    private void showFormAddStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/add.jsp");
        dispatcher.forward(request,response);
    }
    private void showFormAddClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("classes/add.jsp");
        dispatcher.forward(request,response);
    }
    private void showFormDeleteStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student deleteStudent = studentDAO.selectStudent(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/delete.jsp");
        request.setAttribute("student",deleteStudent);
        dispatcher.forward(request,response);
    }
    private void showDeleteFormClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("classId"));
        Classes deleteClass = classesDAO.selectClasses(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("classes/delete.jsp");
        request.setAttribute("classes",deleteClass);
        dispatcher.forward(request,response);
    }
    private void addNewStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String studentFirstName = request.getParameter("firstName");
        String studentLastName = request.getParameter("lastName");
        String studentAddress = request.getParameter("address");
        int classId = Integer.parseInt(request.getParameter("className"));
        Student newStudent = new Student(studentFirstName,studentLastName,studentAddress,classId);
        studentDAO.insertStudent(newStudent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/add.jsp");
        dispatcher.forward(request,response);
    }
    private void addNewClass(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String className = request.getParameter("className");
        String classDescription = request.getParameter("classDescription");
        Classes newClass = new Classes(className,classDescription);
        classesDAO.insertClasses(newClass);
        RequestDispatcher dispatcher = request.getRequestDispatcher("classes/add.jsp");
        dispatcher.forward(request,response);
    }
    private void editStudentInformation(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String className = request.getParameter("className");
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = new Student(id,firstName,lastName,address,className);
        studentDAO.updateStudent(student);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/edit.jsp");
        dispatcher.forward(request,response);
    }
    private void editClassInformation(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String className = request.getParameter("className");
        String classDescription = request.getParameter("classDescription");
        int id = Integer.parseInt(request.getParameter("classId"));
        Classes classes = new Classes(id,className,classDescription);
        classesDAO.updateClasses(classes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("classes/edit.jsp");
        dispatcher.forward(request,response);
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.deleteStudent(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student/delete.jsp");
        dispatcher.forward(request,response);
    }
    private void deleteClass(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("classId"));
        classesDAO.deleteClasses(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("classes/delete.jsp");
        dispatcher.forward(request,response);
    }

}
