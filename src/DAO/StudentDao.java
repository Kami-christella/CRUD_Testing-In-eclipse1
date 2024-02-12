package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Model.Student;
public class StudentDao {
 
      
         String dbUrl="jdbc:mysql://localhost:3306/school";
        String username="root";
        String passwd="Auca@2023";
         
        public String recordStudent(Student sm){
            try{
                 Connection con=DriverManager.getConnection(dbUrl,username,passwd);
                 PreparedStatement ps=con.prepareStatement("insert into student(id,firstname,lastname,country) values(?,?,?,?) ");
                 ps.setInt(1,sm.getId());
                 ps.setString(2,sm.getFirstName());
                 ps.setString(3,sm.getLastName());
                 ps.setString(4, sm.getCountry());
                  int rowAffected=ps.executeUpdate();
                      con.close();
                      if(rowAffected>0){
                          return "data saved";
                      }else{
                          return "not data saved";
                      }
            }catch(Exception ex){
                ex.printStackTrace();
            }
           return "Server Error"; 
        }
        public String updateStudent(Student sOb) {
            try{
                 Connection con = DriverManager.getConnection(dbUrl, username, passwd);
                String sql="update student set firstname=?,lastname=?,country=? where id=?";
                PreparedStatement pst = con.prepareStatement(sql);
               pst.setString(1, sOb.getFirstName());
               pst.setString(2, sOb.getLastName());
               pst.setString(3,sOb.getCountry());
             
                pst.setInt(4,sOb.getId());
               ;
               int rowsAffected = pst.executeUpdate();
               con.close();
               if(rowsAffected>=1){
//                  con.close();
                   return "Data updated Successful";
               }else{
                 con.close();
                   return "Data Not Saved";
               }
                
            }catch(Exception ex){
                ex.printStackTrace();
               return "Server Error!";
            }
        }
        public String deleteStudent(Student sobj){
            try{
                  Connection con = DriverManager.getConnection(dbUrl, username, passwd);
                  String sql="delete from student where id=?";
                  PreparedStatement ps = con.prepareStatement(sql);
               ps.setInt(1, sobj.getId());
                int rowsAffected = ps.executeUpdate();
               con.close();
               if(rowsAffected>=1){
//                  con.close();
                   return "Student deleted Successful";
               }else{
                 con.close();
                   return "Student Not deleted";
               }
            }catch(Exception ex){
                
            }
                       return "Server Error!";

        }
        public Student searchStudent(Student sObj) {
        	 try {
	              Connection con =DriverManager.getConnection(dbUrl, username, passwd);
	           PreparedStatement pst =con.prepareStatement("select * from student where id=?");
	           pst.setInt(1, sObj.getId());
	           return (Student) pst.executeQuery();
	          
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return null;
        	    }
        public List<Student> AllStudents(){
            try{
                Connection con = DriverManager.getConnection(dbUrl, username, passwd);
                String sql="select * from student";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet res = ps.executeQuery();
                List<Student> opList = new ArrayList<>();
                while(res.next()){
                	Student sm = new Student();
                    sm.setId(res.getInt("id"));
                    sm.setFirstName(res.getString("firstname"));
                    sm.setLastName(res.getString("lastname"));
                      sm.setCountry(res.getString("country"));
                      
                    opList.add(sm);
                }
                con.close();
                return opList;
            }catch(Exception ex){
                ex.printStackTrace();
                return null;
            }
        }
        
        
    }