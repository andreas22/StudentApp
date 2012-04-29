package controllers;
 
import java.io.File;
import play.*;
import play.mvc.*;
 
import java.util.*;
 
import models.*;
import org.joda.time.DateTime;
 
@With(Secure.class)
public class Admin extends Controller {
    
    @Before
    static void setConnectedUser() {                
        if(Security.isConnected()) {           
            User user = User.find("byEmail", Security.connected()).first(); 
            
            if(user == null){
                user = new User("andreas22@gmail.com", "1234", "Andreas");
                user.save();
            }
            
            renderArgs.put("user", user.fullname);            
        }
    }
 
    public static void index() {
        render();
    }
    
    public static void upload(){
        Upload upload = Upload.getLastUploadedFile();
        render(upload);
    }
    
    public static void uploadFile(String filename, File file){
        User user = User.find("byEmail", Security.connected()).first();                    
        Upload uploadObj = new Upload();
        uploadObj.UploadFile(filename, user, file);              
        Admin.upload();
    }
    
    public static void reports(String value, int report){
        List<Student> students = Student.findAll();  
        List<Result> results = null;        
        if(report != 0){    
            switch(report){
                case 1:
                    results = StudentResults.generalAveragePerQuarter(Integer.parseInt(value));
                    renderArgs.put("userSelected", Integer.parseInt(value) );
                    break;
                case 2:
                    results = StudentResults.subjectGradesPerQuarter(value);
                    renderArgs.put("subjectSelected", value );
                    break;
                case 3:                    
                    results = StudentResults.yearQuarterGeneralAverage(value);
                    renderArgs.put("yearquarterSelected", value );
                    break;
            }   
            renderArgs.put("reportSelected", report );            
        }
        else{
            renderArgs.put("reportSelected", 0 );            
        }
        render(students, results);
    } 
    
    public static void generalAveragePerQuarter(int studentId){        
        Admin.reports(String.valueOf(studentId), 1);
    }
    
    public static void subjectsGradePerQuarter(String subject){        
        Admin.reports(subject, 2);
    }
    
    public static void yearQuarterGeneralAverage(String value){          
        Admin.reports(value, 3);
    }
}