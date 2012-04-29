/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.joda.time.DateTime;
import play.db.jpa.*;
import play.data.validation.*;

/**
 *
 * @author ac1982
 */
@Entity
public class Student extends Model {
    @Required
    public String studentName;
    
    @Required
    public Date dateOfBirth;
    
    @Required
    public String className;
    
    public Student(String studentName, Date dateOfBith, String className){
        this.studentName = studentName;
        this.dateOfBirth = dateOfBith;
        this.className = className;
    }
    
    public String toString() {
        return studentName;
    }
}
