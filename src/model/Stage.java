package model;

import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Stage {

    
    private TypeStages TypeStage;
    private Calendar startDatePlanned;
    private Calendar endDatePlanned;
    private Calendar startDateReal;
    private Calendar endDateReal;
    private boolean statusStage;
    private Capsule[] listOfCapsules;         

    private DateFormat formatter;
    
    /**
     * The constructor for the stage class
     * @param TypeStage Type of the stage (e.g. planning, execution, monitoring, evaluation)
     * @param startDatePlanned Planned start date for the stage
     * @param endDatePlanned Planned end date for the stage
     * @param startDateReal Real start date for the stage
     * @param endDateReal Real end date for the stage
     * @param statusStage  Status of the stage (true if completed, false if not completed)
     */
    public Stage(TypeStages TypeStage, Calendar startDatePlanned, Calendar endDatePlanned, Calendar startDateReal, Calendar endDateReal, boolean statusStage) {
        
        this.formatter = new SimpleDateFormat("dd/MM/yyyy");
        
        this.TypeStage = TypeStage;
        this.startDatePlanned = startDatePlanned;
        this.endDatePlanned = endDatePlanned;
        this.startDateReal = startDateReal;
        this.endDateReal = endDateReal;
        this.statusStage = statusStage;
        
        listOfCapsules = new Capsule[50];

    }
    
    // getters and setters

    public Capsule[] getCapsules(){
        return  listOfCapsules;
    }

    public TypeStages getTypeStage() {
        return TypeStage;
    }

    public void setTypeStage(TypeStages TypeStage) {
        this.TypeStage = TypeStage;
    }

    public Calendar getStartDatePlanned() {
        return getStartDatePlanned();
    }

    public String getStartDateFormated(){
        return formatter.format(this.startDatePlanned.getTime());
    }

    public void setStartDatePlanned(Calendar startDatePlanned) {
        this.startDatePlanned = startDatePlanned;
    }

    public Calendar getEndDatePlanned() {
        return getEndDatePlanned();
    }

    public String getEndDateFormated(){
        return formatter.format(this.endDatePlanned.getTime());
    }
    
    public void setEndDatePlanned(Calendar endDatePlanned) {
        this.endDatePlanned = endDatePlanned;
    }

    public Calendar getStartDateReal() {
        return getStartDateReal();
    }

    public String getStartDateRealFormated(){
        return formatter.format(this.startDateReal.getTime());
    }

    public void setStartDateReal(Calendar startDateReal) {
        this.startDateReal = startDateReal;
    }

    public Calendar getEndDateReal() {
        return getEndDateReal();
    }

    public String getEndDateRealFormated(){
        return formatter.format(this.endDateReal.getTime());
    }
    
    public void setEndDateReal(Calendar endDateReal) {
        this.endDateReal = endDateReal;
    }

    public boolean getStatusStage(){
        return statusStage;
    }

    public void setStatusStage(boolean StatusStage){
        this.statusStage=StatusStage;
    } 

    /**
     * in a message shows certain things about the stage
     * @return the message
     */
    public String toStringStage(){
        String msg = "";

        msg = "\n Stage: " + TypeStage + "\n Date Start Planned: " + getStartDateFormated() + "\n Date End Planned: " + getEndDateFormated() + "\n Status of stage: " + statusStage+"\n";
    
        return msg;
    }
 
    /**
     * shows certain of the things of the stage mainly the actual dates
     * @return the message
     */
    public String toStringStageReal(){
		String msg = "";

		msg = "\n Stage: " + TypeStage +"\n  Date Start Planned" + getStartDateFormated() +"\n Date End Planned" + getEndDateFormated() +"\n Date Start Real: " + getStartDateFormated() + "\nFecha real fin: "+ getEndDateFormated() + "\n Status of the state: " + statusStage;
		
        return msg;
	}

	/**
	* Method that registers a new capsule with the given parameters.
	* @param id the id of the capsule
	* @param description the description of the capsule
	* @param type the type of the capsule (Technical or Experiences)
    * @param colabName The collaborator name 
    * @param jobTitle the position of the collaborator
	* @param lessonLearned the lesson learned from the capsule
	* @return true if the capsule is successfully registered, false otherwise
	*/
    public boolean registerCapsule(String id, String description, String type,String colabName, String jobTitle, String lessonLearned) {
        
		TypeCapsule typeN = TypeCapsule.TECHNICAL;
		if(type.equalsIgnoreCase("Technical")){
			typeN = TypeCapsule.EXPERIENCES;
		}
		
		Capsule newCapsule = new Capsule(id, description, typeN, colabName, jobTitle,  lessonLearned);

        for(int i = 0; i<listOfCapsules.length; i++){

			if(listOfCapsules[i] == null){
				listOfCapsules[i] = newCapsule;
				return true;
			}

		}

        return false;

    }


	/**
	 * Aprobar una cápsula existente.
	 * @param id The id of the capsule to be updated.
	 * @param status The new status to be set for the capsule (either "approved" or "not approved").
	 * @return True if the capsule was found and the status was updated successfully, false otherwise.
	 */
	public boolean approveCapsule(String id, String status) {
		id = "";

		Status statusN = Status.NOT_APPROVED;
		if(status.equalsIgnoreCase("Approved")){
			statusN = Status.APPROVED;
		}

		for(int i = 0; i<listOfCapsules.length; i++){
			if(listOfCapsules[i]!=null){
				id = listOfCapsules[i].getId();
				if(id.equals(id)){
					listOfCapsules[i].setStatus(statusN);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns a string with a list of all the capsule IDs in the system.
	 * @return A string with the list of capsule IDs.
	 */
    public String searchCapsule(){

		String msg = "";

		for(int i = 0; i < listOfCapsules.length; i++ ){

			if(listOfCapsules[i]!=null){
                msg += "\n"+ (i+1) + ". " + listOfCapsules[i].getId();
            }
		}

		return msg;
	}

	/**
	 * Returns a string with a list of all the capsules in the system, including their properties.
	 * @return A string with the list of capsules and their properties.
	 */
    public String getCapsule() {

		String msg = "";
		for(int i = 0; i<listOfCapsules.length; i++){
			if(listOfCapsules[i]!=null){
				msg += "\n" + listOfCapsules[i].toString();
			}
		}

		return msg;

	}

    public String searchCapsuleByHashtags(String hashtagToSearch) {

		String msg = "";


		for (int i = 0; i<listOfCapsules.length; i++){

			if(listOfCapsules[i]!=null){
                if (listOfCapsules[i].getDescription().contains(hashtagToSearch)) {
                    msg += "\n" + listOfCapsules[i].toString();
                }

				
			}
		}

		return msg;

	}

    /**
     * Edits a project stage by setting its planned and real start dates.
     * @param stageOption The index of the stage to be edited.
     * @param dateSP The day of the planned start date.
     * @param monthSP The month of the planned start date
     * @param yearSP The year of the planned start date.
     * @param dateSR The day of the real start date.
     * @param monthSR The month of the real start date.
     * @param yearSR The year of the real start date.
     * @return True if the stage was edited successfully, false otherwise.
     */
    public boolean editStage(int stageOption, int dateSP, int monthSP, int yearSP, int dateSR, int monthSR, int yearSR){
        boolean ind = false;

		if(ind==false){
			startDatePlanned = new GregorianCalendar(yearSP, monthSP, dateSP);
			startDateReal = new GregorianCalendar(yearSR, monthSR, dateSR);
		

			ind = true;
		}
        
        return ind;

    }

    /**
     * Retrieves the lessons learned in all the capsules of the project stage.
     * @return A string with the lessons learned in all the capsules of the project stage.
     */
    public String gLessons(){
        String msg = "" ;

        for (int i=0; i<50; i++) {
            if(listOfCapsules[i] != null){
                msg += listOfCapsules[i].getLessonLearned() +"\n";
            }
        }

        return msg;
    }
    
       
}
