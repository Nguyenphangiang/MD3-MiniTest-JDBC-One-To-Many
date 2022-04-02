package dao;

import model.Classes;
import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassesDAO implements IClassDAO{
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/school";
    public static final String JDBC_ACCOUNT = "root";
    public static final String JDBC_PASSWORD = "Giangpro123";
    public static final String INSERT_INTO_CLASSES = "insert into classes values (?,?,?);";
    public static final String SELECT_FROM_CLASSES = "select class_Name,class_Description from classes where id=?;";
    public static final String SELECT_ALL_FROM_CLASSES = "select * from classes";
    public static final String SQL_DELETE_CLASSES = "delete from classes where id=?";
    public static final String SQL_EDIT_CLASS = "update classes set class_Name = ? , class_Description = ? where id = ?";

    public ClassesDAO() {
    }
    protected Connection getconnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL,JDBC_ACCOUNT,JDBC_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void insertClasses(Classes classes) throws SQLException {
        System.out.println(INSERT_INTO_CLASSES);
        try(Connection connection = getconnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_CLASSES)){
            preparedStatement.setInt(1,classes.getClassesId());
            preparedStatement.setString(2,classes.getClassName());
            preparedStatement.setString(3,classes.getClassDescription());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Classes selectClasses(int id) {
        Classes classes = null;
        try(Connection connection = getconnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_CLASSES)){
            preparedStatement.setInt(1,id);
            ResultSet rs =  preparedStatement.executeQuery();
            while (rs.next()){
                String className = rs.getString("class_Name");
                String classDescription = rs.getString("class_Description");
                classes = new Classes(id,className,classDescription);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

    @Override
    public List<Classes> selectAllClasses() {
        List<Classes> classes = new ArrayList<>();
        try(Connection connection = getconnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_CLASSES)){
           ResultSet rs = preparedStatement.executeQuery();
           while (rs.next()){
               int id = rs.getInt("id");
               String className = rs.getString("class_Name");
               String classDescription = rs.getString("class_Description");
               classes.add(new Classes(id,className,classDescription));
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

    @Override
    public boolean deleteClasses(int id) throws SQLException {
        boolean rowDeleted;
        try(Connection connection = getconnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_CLASSES)){
            preparedStatement.setInt(1,id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateClasses(Classes classes) throws SQLException {
        boolean rowUpdated;
        try(Connection connection = getconnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_EDIT_CLASS)){
            preparedStatement.setString(1,classes.getClassName());
            preparedStatement.setString(2,classes.getClassDescription());
            preparedStatement.setInt(3,classes.getClassesId());
            rowUpdated = preparedStatement.executeUpdate() >0 ;
        }
        return rowUpdated;
    }
}
