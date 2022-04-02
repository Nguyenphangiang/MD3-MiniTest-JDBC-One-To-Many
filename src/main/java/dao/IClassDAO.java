package dao;

import model.Classes;
import model.Student;

import java.sql.SQLException;
import java.util.List;

public interface IClassDAO {
    void insertClasses(Classes classes) throws SQLException;

    Classes selectClasses(int id);

    List<Classes> selectAllClasses();

    boolean deleteClasses(int id) throws SQLException;

    boolean updateClasses(Classes classes) throws SQLException;

}
