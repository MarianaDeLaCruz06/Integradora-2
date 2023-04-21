package model;


public class Capsule{
    private String id;
    private String description;
    private Type type;
    private String lessonLearned;
    private Status status;

    // Constructor
    public Capsule(String id, String description, Type type, String lessonLearned) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.lessonLearned = lessonLearned;
        this.status = Status.TO_DEFINE;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType(){
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }
    
    public String getLessonLearned() {
        return lessonLearned;
    }
    
    public void setLessonLearned(String lessonLearned) {
        this.lessonLearned = lessonLearned;
    }

    public Status getStatus(){
        return status;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    public String toString(){
        String msg = "";
        msg = "\nID:" +id+ "\nDescription:" +description+ "\nType:" +type+ "\nLearned Lesson:" +lessonLearned+ "\nStatus:" +status; 
        return msg;
    }

    public String showDescription(){
        String msg = "\nID:" +id+ "\nDescription:" +description;
        return msg;
    }

}