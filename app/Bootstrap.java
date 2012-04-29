import play.*;
import play.jobs.*;
import play.test.*;
 
import models.*;
 
@OnApplicationStart
public class Bootstrap extends Job {     
    public void doJob() {
        /*
        // Check if the database is empty
        if(User.count() == 0) {
           User user = User.find("byEmail", "andreas22@gmail.com").first();
           if(user.count() == 0){
            Fixtures.loadModels("data.yml");
           }
        }
        * 
        */
    }
 
}