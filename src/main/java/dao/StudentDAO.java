package dao;

import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements IStudentDAO{

    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/school";
    public static final String JDBC_ACCOUNT = "root";
    public static final String JDBC_PASSWORD = "Giangpro123";
    public static final String SQL_INSERT_INTO_STUDENT = "insert into student (student_First_Name,student_Last_Name,student_Address,class_id) values(?,?,?,?);";
    public static final String SQL_SELECT_FROM_STUDENT_CLASS = "select student_First_Name,student_Last_Name,student_Address,c.class_Name from student join classes c on student.class_id = c.id where student.id=?;";
    public static final String SQL_SELECT_ALL_STUDENT_CLASSNAME = "select student.id,student_First_Name,student_Last_Name,student_Address,c.class_Name from student join classes c on student.class_id = c.id;";
    public static final String SQL_DELETE_STUDENT_BY_ID = "delete from student where id=?";
    public static final String SQL_UPDATE_STUDENT = "update student join classes c on student.class_id = c.id set student_First_Name = ? , student_Last_Name = ? , student_Address = ? , c.class_Name = ? where student.id =?;";
    public static final String SQL_INSER_INTO_CLASS_MANAGER = "insert into class_manager values (?,?)";
    public static final String SQL_DELETE_CLASS_MANAGER = "alter table class_manager drop constraint fk_htk_student_id where student_id = ?";

    public StudentDAO() {
    }
    protected Connection getConnection(){
        Connection connection = null;
    try {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(JDBC_URL, JDBC_ACCOUNT, JDBC_PASSWORD);
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
        return connection;
}



    @Override
    public void insertStudent(Student student) throws SQLException {
        System.out.println(SQL_INSERT_INTO_STUDENT);
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_STUDENT,Statement.RETURN_GENERATED_KEYS);
            PreparedStatement preparedStatement1 = connection.prepareStatement(SQL_INSER_INTO_CLASS_MANAGER)){
            preparedStatement.setString(1,student.getFirstName());
            preparedStatement.setString(2,student.getLastName());
            preparedStatement.setString(3,student.getAddress());
            preparedStatement.setInt(4,student.getClassId());
            System.out.println(preparedStatement);
            int rowAffected = preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            int studentId = 0;
            if (rs.next())
                studentId = rs.getInt(1);
            if (rowAffected == 1){
                preparedStatement1.setInt(1,studentId);
                preparedStatement1.setInt(2,student.getClassId());
                preparedStatement1.executeUpdate();
            } else
                System.err.println("Id not found");
        }
    }

    @Override
    public Student selectStudent(int id) {
        Student student = null;
        try(Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_FROM_STUDENT_CLASS)){
        preparedStatement.setInt(1,id);
        System.out.println(preparedStatement);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            String firstName =rs.getString("student_First_Name");
            String lastName =rs.getString("student_Last_Name");
            String address =rs.getString("student_Address");
            String classes = rs.getString("class_Name");
            student = new Student(firstName,lastName,address,classes);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public List<Student> selectAllStudent() {
        List<Student> students = new ArrayList<>();
        try(Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_STUDENT_CLASSNAME)){
        System.out.println(preparedStatement);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            int id = rs.getInt("id");
            String firstName =rs.getString("student_First_Name");
            String lastName =rs.getString("student_Last_Name");
            String address =rs.getString("student_Address");
            String classes = rs.getString("class_Name");
            students.add(new Student(id,firstName,lastName,address,classes));
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }return students;
    }

    @Override
    public boolean deleteStudent(int id) throws SQLException {
        boolean rowDelete;
        try(Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_STUDENT_BY_ID)){
            preparedStatement.setInt(1,id);
            rowDelete = preparedStatement.executeUpdate() > 0;
        }
        return rowDelete;
    }

    @Override
    public boolean updateStudent(Student student) throws SQLException {
        boolean rowUpdate;
        try(Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_STUDENT)){
            preparedStatement.setString(1,student.getFirstName());
            preparedStatement.setString(2,student.getLastName());
            preparedStatement.setString(3,student.getAddress());
            preparedStatement.setString(4,student.getStudentClass());
            preparedStatement.setInt(5,student.getStudentId());
            rowUpdate = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdate;
    }
}
