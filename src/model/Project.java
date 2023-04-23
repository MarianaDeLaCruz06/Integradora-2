package model;

import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;


//import model.Manager;


public class Project {

    private String nameproject;
    private String clientName;
    private Calendar startDate;
    private Calendar endDate;
    private double budget;
    private String managerName;
    private String managerPhone;
    private Stage[] listOfStages;
    private int currentStage;

    private DateFormat formatter;

    /**
    * Constructs a new Project object.
    * @param nameproject the name of the project
    * @param clientName the name of the client
    * @param startDate the start date of the project
    * @param endDate the end date of the project
    * @param budget the budget of the project
    * @param managerName the name of the project manager
    * @param managerPhone the phone number of the project manager
    * @param currentStage the current stage 
    */
    public Project(String nameproject, String clientName, Calendar startDate, Calendar endDate, double budget, String managerName, String managerPhone) {
        

        this.formatter = new SimpleDateFormat("dd/M/yy");

        this.nameproject = nameproject;
        this.clientName = clientName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.managerName = managerName;
        this.managerPhone = managerPhone;
        this.currentStage = currentStage;

        listOfStages=new Stage[6];
        
        Calendar dateStagePI=Calendar.getInstance();
        Calendar dateStagePF=Calendar.getInstance();
        Calendar dateStageRI=Calendar.getInstance();
        Calendar dateStageRF=Calendar.getInstance();

        listOfStages[0] = new Stage(TypeStages.INITIATION,startDate, dateStagePF, dateStageRI, dateStageRF, true);
        listOfStages[1] = new Stage(TypeStages.ANALYSIS, dateStagePI, dateStagePF, dateStageRI, dateStageRF, false);
        listOfStages[2] = new Stage(TypeStages.DESIGN,dateStagePI, dateStagePF, dateStageRI, dateStageRF, false);
        listOfStages[3] = new Stage(TypeStages.EXECUTION,dateStagePI, dateStagePF, dateStageRI, dateStageRF, false);
        listOfStages[4] = new Stage(TypeStages.CLOSING,dateStagePI, dateStagePF, dateStageRI, dateStageRF, false);
        listOfStages[5] = new Stage(TypeStages.MONITORING,dateStagePI, dateStagePF, dateStageRI, dateStageRF, false);

    }

    // Getters and Setters
    public String getName() {
        return nameproject;
    }

    public void setName(String nameproject) {
        this.nameproject = nameproject;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getStartDateFormated() {
        return formatter.format(this.startDate.getTime());
    }

    public Calendar getStartDate(){
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public String getEndDateFormated() {
        return formatter.format(this.endDate.getTime());
    }

    public Calendar getEndDate(){
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getManagerName(){
        return managerName;
    }

    public String getManagerNumber(){
        return managerPhone;
    }

    public Stage[] getListOfStages() {
        return listOfStages;
    }

    public void setListOfStages(Stage[] listOfStages) {
        this.listOfStages = listOfStages;
    }

    public int getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(int currentStage) {
        this.currentStage = currentStage;
    }

    /**
     * create a message with all the information about the project
     * @return the message
     */
    public String getProjectInfo() {
		String msg = "";
        
        msg = "Name: " + nameproject + "\nClient: " + clientName + "\nInitial Date: " + getStartDateFormated() + 
		"\nFinal Date: " + getEndDateFormated() + "\nTotalBudget: " + budget + ".\n";

        return msg;
	}
    
    /**
     * Culminates a stage with the specified parameters.
     * @param stageOption The option number of the stage to culminate.
     * @param dateSR The start date of the stage in dd format.
     * @param monthSR The start date of the stage in mm format.
     * @param yearSR The start date of the stage in yyyy format.
     * @param dateER The end date of the stage in dd format.
     * @param monthER The end date of the stage in mm format.
     * @param yearER The end date of the stage in yyyy format.
     * @param statusN The status of the stage (1 for "completed", 2 for "not completed").
     * @return A boolean indicating whether the operation was successful or not.
     */
	public boolean culminateStage(int stageOption, int dateSR, int monthSR, int yearSR, int dateER, int monthER, int yearER, int statusN){
        boolean indic = false;
        
        Calendar newDateStartReal=new GregorianCalendar(yearSR, monthSR ,dateSR);
        Calendar newDateEndReal=new GregorianCalendar(yearER, monthER, yearER);

        if (statusN == 1){
            listOfStages[stageOption].setStartDateReal(newDateStartReal);
            listOfStages[stageOption].setEndDateReal(newDateEndReal);
            listOfStages[stageOption].setStatusStage(true);
            indic = true;
        } else if(statusN == 2){
            listOfStages[stageOption].setStartDateReal(newDateStartReal);
            listOfStages[stageOption].setEndDateReal(newDateEndReal); 
            listOfStages[stageOption].setStatusStage(false);
            indic = true;
        }

        return indic;
    }

    /**
     * shows the current stages
     * @return the message of the stages that are active
     */
    public String showCurrentStages(){
        String msg="";
    
        for(int i=0; i<listOfStages.length;i++){
            if(listOfStages[i].getStatusStage()==true){
                msg=listOfStages[i].toStringStage();
            }
        }
        return msg;
    }

    /**
     * shows the active stage
     * @return returns the number or position of the stage which is active
     */
    public int ActiveStage(){
        int posicion=0;
        for(int i=0;i<listOfStages.length;i++){
            if(listOfStages[i].getStatusStage()==true){
                posicion=i;
            }
        }
        return posicion;
    }

    /**
     * register a capsule
     * @param id the ID of the capsule
     * @param description the description  of the capsule
     * @param type The capsule type (1 = Technical, 2 = Management, 3 = Domain, 4 = Experiences).
	 * @param colabName  The name of the collaborator who created the capsule.
	 * @param jobTitle  The job title of the collaborator who created the capsule.
	 * @param lessonLearned The lesson learned from the capsule.
     */
    public void registerCapsule(String id, String description, String type, String colabName, String jobTitle, String lessonLearned){
        for(int i = 0; i < listOfStages.length; i++){ 
            if(listOfStages[i].getStatusStage()){
                listOfStages[i].registerCapsule(id, description, type, colabName, jobTitle, lessonLearned);

            }
        }
    }

    /**
     * to approve the capsules you need
     * @param id the Id of the capsule
     * @param status the status of the capsule
     */
    public void approveCapsule(String id, String status){
        for(int i = 0; i < listOfStages.length; i++){
            if(listOfStages[i].getStatusStage()){
                listOfStages[i].approveCapsule(id, status);

            }
        }
    }

    /**
	 * Returns a message with the lessons learned from the chosen project
	 * @param m to choose the project where the lesson is to be learned
	 * @return a message with the lessons learned from the chosen project
	 */
    public String lessons(int m){
        String msg = "";
        msg += listOfStages[m].gLessons()+"\n";
        return msg;
    }


}

