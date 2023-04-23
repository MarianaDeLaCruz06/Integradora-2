package model;


public class Capsule{
    private String id;
    private String description;
    private TypeCapsule type;
    private String colabName;
	private String jobTitle;
    private String lessonLearned;
    private Status status;
    private boolean publish;
    private String URL;

    /**
     * Constructor for the Capsule class
     * @param id The unique identifier for the capsule
     * @param description The description of the capsule
     * @param type The type of the capsule
     * @param colabName The name of the collaborator who created the capsule
     * @param jobTitle The job title of the collaborator who created the capsule
     * @param lessonLearned  The lesson learned from the capsule
     */
    public Capsule(String id, String description, TypeCapsule type, String colabName, String jobTitle, String lessonLearned) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.colabName = colabName;
        this.jobTitle = jobTitle;
        this.lessonLearned = lessonLearned;
        this.status = Status.TO_DEFINE;
        this.publish=false;
        this.URL="-NINGUNO";
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColabName() {
        return colabName;
    }

    public void setColabName(String colabName) {
        this.colabName = colabName;
    }

    public String getJobTitle(String jobTitle){
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public boolean getPublish() {
        return publish;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String uRL) {
        URL = uRL;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeCapsule getType(){
        return type;
    }
    public void setType(TypeCapsule type) {
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

    /**
     * shows in a message information that carries the capsule
     * @return the message
     */
    public String toString(){
        String msg = "";
        msg = "ID:" +id+ "\nDescription:" +description+ "\nType:" +type+ "\nname collaborator:" +colabName+ "\n job title collaborator:" +jobTitle+ "\nLearned Lesson:" +lessonLearned+ "\nStatus:" +status+"\n"; 
        return msg;
    }

    /**
     * shows in a message information that carries the capsule by adding the publish and URL
     * @return the message
     */
    public String toStringMAX(){
        String msg = "";
        msg = "ID:" +id+ "\nDescription:" +description+ "\nType:" +type+ "\nname collaborator:" +colabName+ "\n job title collaborator:" +jobTitle+ "\nLearned Lesson:" +lessonLearned+ "\nStatus:" +status+"\nPublish: "+publish+"\nURL: "+URL+"\n"; 
        return msg;
    }

    /**
     * shows in a message the id and description
     * @return the message
     */
    public String showDescription(){
        String msg = "\nID:" +id+ "\nDescription:" +description;
        return msg;
    }

}