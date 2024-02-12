import static org.junit.jupiter.api.Assertions.*;
import DAO.StudentDao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import Model.Student;

class ClassTest {

	  private StudentDao studentDao;

    @BeforeEach
    public void setUp() throws Exception {
        // Initialize StudentDao
         studentDao = new StudentDao();
    }

    @AfterEach
    public void tearDown() throws Exception {
        // Cleanup resources if needed
    }

    @Test
    public void testRegister() {
        Student student = new Student();
        student.setId(24689);
        student.setFirstName("Kevin");
        student.setLastName("Best");
        student.setCountry("Rwanda");

       
		String result = studentDao.recordStudent(student);
        assertEquals("Successfully inserted", result);
    }

    @Test
    public void testUpdate() {
        Student student = new Student();
        student.setId(24567);
        student.setFirstName("Kevin");
        student.setLastName("Best");
        student.setCountry("Rwanda");

       String result = studentDao.updateStudent(student);
        assertEquals("Successfully Updated", result);
    }

/*    @Test
    public void testDelete() {
        Student student = new Student();
        student.setId(24601);

        String result = studentDao.delete(student);
        assertEquals("Successfully Deleted", result);
    }*/

    @Test
    public void testSearch() throws SQLException {
        Student student = new Student();
        student.setId(24604);

        ResultSet resultSet = (ResultSet) studentDao.searchStudent(student);
        assertNotNull(resultSet);

        // Check if ResultSet contains data
        assertTrue(resultSet.next());

        // Retrieve data from ResultSet
        int id = resultSet.getInt("id");
        String firstname = resultSet.getString("firstname");
        String country = resultSet.getString("country");

        // Verify retrieved data
        assertEquals(24601, id);
        assertEquals("Kevin", firstname);
        assertEquals("Kenya", country);
    }

    @Test
    public void testStudentList() throws SQLException {
    	Student student = new Student();
    	ResultSet resultSet = (ResultSet) studentDao.AllStudents();
        assertNotNull(resultSet);

        // Check if ResultSet contains data
        assertTrue(resultSet.next());

        // Retrieve data from ResultSet
        int id = resultSet.getInt("id");
        String firstname = resultSet.getString("firstname");
        String country = resultSet.getString("country");

        // Verify retrieved data
        assertEquals(24601, id);
        assertEquals("Kevin", firstname);
        assertEquals("Linux", country);
    }
}
