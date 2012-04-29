/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.*;
import javax.persistence.*;
import org.joda.time.DateTime;
import play.db.jpa.*;
import sun.font.Type1Font;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/**
 *
 * @author ac1982
 */
@Entity
public class StudentResults extends Model {
    public int studentYear;
    public String quarter;
    public int mathematics;
    public int computerScience;
    public int literature;
    public Double total;    
        
    @ManyToOne
    public Student student;
    
    public StudentResults(){
        
    }
    
    public StudentResults(int year, String quarter, int mathematics, int computerScience, int literature, Student student){
        this.studentYear = year;
        this.quarter = quarter;
        this.mathematics = mathematics;
        this.computerScience = computerScience;
        this.literature = literature;
        this.student = student;
    }
    
    public static List<StudentResults> findByYear(int year) {
        return StudentResults.find("studentYear = ?", year).fetch();
    }
    
    public static List<Result> generalAveragePerQuarter(int studentId) {        
        String q = "select studentYear, quarter, AVG(mathematics + computerScience + literature) as total from StudentResults where student = " + studentId + " GROUP BY studentYear, quarter ORDER BY studentYear, quarter";
        return StudentResults.getResults(q);
    }
    
    public static List<Result> subjectGradesPerQuarter(String subject) {     
        String q = "select studentYear, quarter, AVG(" + subject + ") as total from StudentResults GROUP BY studentYear, quarter ORDER BY studentYear, quarter";        
        return StudentResults.getResults(q);
    }
    
    public static List<Result> yearQuarterGeneralAverage(String value) {   
        if(value != null){    
            String delimiter = "-";
            String[] temp = value.split(delimiter);            
            int year = Integer.parseInt(temp[0]);
            String quarter = temp[1];
            String q = "select studentYear, quarter, AVG(mathematics), AVG(computerScience), AVG(literature) from StudentResults WHERE studentYear = " + year + " AND quarter = '" + quarter + "' GROUP BY studentYear, quarter ORDER BY studentYear, quarter";         
            return StudentResults.getResults(q);
        }
        List<Result> results = null;
        return results;
    }
    
    private static List<Result> getResults(String q){                      
        Query query = JPA.em().createQuery(q);        
        List<Result> stuResults = new ArrayList<Result>();      
        Result rs = null;
        List<Object[]> results = query.getResultList();     
        int iteration = 1;
        for (Object[] objects : results) {
            
            if(objects.length == 3){
                int dYear = Integer.parseInt(objects[0].toString());
                String quarter = objects[1].toString();
                double total = Double.parseDouble(objects[2].toString());                        
                rs = new Result();
                rs.year = dYear;
                rs.quarter = quarter;
                rs.total = total;  
            }
            else if(objects.length == 5){
                int dYear = Integer.parseInt(objects[0].toString());
                String quarter = objects[1].toString();
                double subject1 = (objects[2].toString() != "") ? Double.parseDouble(objects[2].toString()) : 0; 
                double subject2 = (objects[3].toString() != "") ? Double.parseDouble(objects[3].toString()) : 0;
                double subject3 = (objects[4].toString() != "") ? Double.parseDouble(objects[4].toString()) : 0;
                
                rs = new Result();
                rs.year = dYear;
                rs.quarter = quarter;   
                Double[] subResults = new Double[3];
                subResults[0] = subject1; 
                subResults[1] = subject2; 
                subResults[2] = subject3; 
                rs.resultsBySubject = subResults;                           
            }
     
            if(rs != null){                
                stuResults.add(rs);                
            }
        }               
        return stuResults;
    }
}
