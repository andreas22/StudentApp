/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.joda.time.DateTime;
import javax.persistence.*;
import play.db.jpa.*;

/**
 *
 * @author ac1982
 */
@Entity
public class Upload extends Model  {
    public String fileName;   
    public Date uploadedAt;    
    
    @ManyToOne
    public User user;
    
    public Upload(){           
        
    }
    
    public static Upload getLastUploadedFile(){
        return Upload.find("order by uploadedAt desc").first();        
    }
    
    public void UploadFile(String fileName, User user, File file){
        try{                
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            FileInputStream fstream = new FileInputStream(file);            
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            String[] items;
            
            int studentId;
            String studentName;
            Date dob;
            String studentClass;
            int year; 
            String quarter;
            int mathematics; 
            int computerScience;
            int literature;
            int iteration = 1;
                       
            while ((strLine = br.readLine()) != null)   {                    
                if(iteration != 1){                    
                    items = strLine.split(",");
                    studentId = (items[0] != null) ? Integer.parseInt(items[0]) : 0;                   
                    studentName = (items[1] != null) ? items[1] : "";                                        
                    dob = (items[2] != null) ? (Date)formatter.parse(items[2]) : null;                       
                    studentClass = (items[3] != null) ? items[3] : "";
                    year = (items[4] != null) ? Integer.parseInt(items[4]) : 0;
                    quarter = (items[5] != null) ? items[5] : "";
                    mathematics = (items[6] != null) ? Integer.parseInt(items[6]) : 0;
                    computerScience = (items[7] != null) ? Integer.parseInt(items[7]) : 0;
                    literature = (items[8] != null) ? Integer.parseInt(items[8]) : 0;
                    
                    if(studentId != 0){                        
                        Student student = Student.findById( new Long(studentId));                        
                        
                        if(student == null){
                            student = new Student(studentName, dob, studentClass).save();                            
                            //System.out.println("Creating new student ID: " + studentId + " Name: " + studentName + ".. ID: " + student.id);                                                        
                        }
                        else{
                            //System.out.println("Student found ID: " + studentId + " | " + studentName);
                        }
                        
                        StudentResults results = new StudentResults(year, quarter, mathematics, computerScience, literature, student).save();
                        if(results != null){
                            //System.out.println("Student Results added: " + results.id + "|" + results.year);
                        }
                        else{
                            //System.out.println("Student Results failed: " + results.id + "|" + results.year);
                        }
                    }                                       
                }                          
                iteration++;                
            }
            //Close the input stream
            in.close();                                              
            
            this.fileName = fileName;             
            //this.uploadedAt = (Date)sqlFormat.parse(sqlFormat.format(date));
            this.user = user;
            this.save();         
        }
        catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }   
}
