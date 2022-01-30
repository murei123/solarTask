package Module;

import java.util.Date;

public class Birthday {
    private int id;
    private String name;
    private Date dr;

    public Birthday() {
    }

    public Birthday(int id, String name, Date dr) {
        this.name = name;
        this.dr = dr;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDr() {
        return dr;
    }

    public void setDr(Date dr) {
        this.dr = dr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}