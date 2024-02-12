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

import Model.Course;
public class CourseDao {
 
      
         String dbUrl="jdbc:mysql://localhost:3306/school";
        String username="root";
        String passwd="Auca@2023";
         
        public String recordCourse(Course sm){
            try{
                 Connection con=DriverManager.getConnection(dbUrl,username,passwd);
                 PreparedStatement ps=con.prepareStatement("insert into Course(id,cname,lecturer) values(?,?,?) ");
                 ps.setInt(1,sm.getId());
                 ps.setString(2,sm.getCname());
                 ps.setString(3,sm.getLecturer());
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
        public String updateCourse(Course sm) {
            try{
                 Connection con = DriverManager.getConnection(dbUrl, username, passwd);
                String sql="update Course set ,cname=?,lecturer=? where id=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(3,sm.getId());
                ps.setString(2,sm.getCname());
                ps.setString(3,sm.getLecturer());
               ;
               int rowsAffected = ps.executeUpdate();
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
        public String deleteCourse(Course sobj){
            try{
                  Connection con = DriverManager.getConnection(dbUrl, username, passwd);
                  String sql="delete from Course where id=?";
                  PreparedStatement ps = con.prepareStatement(sql);
               ps.setInt(1, sobj.getId());
                int rowsAffected = ps.executeUpdate();
               con.close();
               if(rowsAffected>=1){
//                  con.close();
                   return "Course deleted Successful";
               }else{
                 con.close();
                   return "Course Not deleted";
               }
            }catch(Exception ex){
                
            }
                       return "Server Error!";

        }
        public Course searchCourse(Course sObj) {
        	 try {
	              Connection con =DriverManager.getConnection(dbUrl, username, passwd);
	           PreparedStatement pst =con.prepareStatement("select * from Course where id=?");
	           pst.setInt(1, sObj.getId());
	           return (Course) pst.executeQuery();
	          
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return null;
        	    }
        public List<Course> AllCourses(){
            try{
                Connection con = DriverManager.getConnection(dbUrl, username, passwd);
                String sql="select * from Course";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet res = ps.executeQuery();
                List<Course> opList = new ArrayList<>();
                while(res.next()){
                	Course sm = new Course();
                    sm.setId(res.getInt("id"));
                    sm.setCname(res.getString("cname"));
                    sm.setLecturer(res.getString("lecturer"));
                      
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