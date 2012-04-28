/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import javax.persistence.*;
import org.joda.time.DateTime;
import play.db.jpa.*;

/**
 *
 * @author ac1982
 */
@Entity
public class Student extends Model {
    public String studentName;
    public Date dateOfBirth;
    public String className;
    
    public Student(String studentName, Date dateOfBith, String className){
        this.studentName = studentName;
        this.dateOfBirth = dateOfBith;
        this.className = className;
    }
}
