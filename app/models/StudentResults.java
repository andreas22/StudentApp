/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.*;
import javax.persistence.*;
import org.joda.time.DateTime;
import play.db.jpa.*;

/**
 *
 * @author ac1982
 */
@Entity
public class StudentResults extends Model {
    public int year;
    public String quarter;
    public int mathematics;
    public int computerScience;
    public int literature;
        
    @ManyToOne
    public Student student;
    
    public StudentResults(int year, String quarter, int mathematics, int computerScience, int literature, Student student){
        this.year = year;
        this.quarter = quarter;
        this.mathematics = mathematics;
        this.computerScience = computerScience;
        this.literature = literature;
        this.student = student;
    }
    
    public static List<StudentResults> findByYear(int year) {
        return StudentResults.find("year = ?", year).fetch();
    }
}
