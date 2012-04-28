/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.persistence.*;
import org.joda.time.DateTime;
import play.db.jpa.*;

/**
 *
 * @author ac1982
 */
@Entity
public class Upload extends Model  {
    public String fileName;
    public DateTime uploadedAt;
    
    @ManyToOne
    public User user;
    
    public Upload(String fileName, DateTime uploadedAt, User user){
        this.fileName = fileName; 
        this.uploadedAt = uploadedAt;
        this.user = user;
    }
}
