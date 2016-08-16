package spring_boot_style.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 * A java class representing an App_Info in the HUAWEI app store.
 */
@Entity
@Table(name = "app_info") //if omitted, will try to match same name table of this class
@Data //getter and setters generated by lombok, hibernate and jackson will use these to hydrate objects from database
public class App {

    @Id
    @NotNull
    private String appid; //C10000291, 8 digits
    private int score;
    private String title;
    private String url;
    private String thumbnailUrl;
    private String intro;
    private String developer;

    private String top5App;

    @Transient
    private String[] top5AppsArr;
    @Transient
    public String[] getTop5AppsArray() {

        if(this.top5App != null && this.top5App.length() > 0){
            this.top5App = this.top5App.substring(1, this.top5App.length()-1);

            String[] appIDs = this.top5App.split(",");
            this.top5AppsArr = new String[5];

            for(int i = 0 ; i < appIDs.length; i++)
                this.top5AppsArr[i] = appIDs[i].trim();
        }else
            return new String[]{};

        return top5AppsArr;
    }

    // default constructor necessary for hibernate to connect class with mysql database, avoid overriden
    public App(){}

    public App(String appid){this.appid = appid;}


}