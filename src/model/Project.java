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
    */
    public Project(String nameproject, String clientName, Calendar startDate, Calendar endDate, 
                   double budget, String managerName, String managerPhone, int[] months) {
        

        this.formatter = new SimpleDateFormat("dd/M/yy");

        this.nameproject = nameproject;
        this.clientName = clientName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.managerName = managerName;
        this.managerPhone = managerPhone;
        this.listOfStages = new Stage[6];
        


        for (int i = 0; i < listOfStages.length; i++) {
            listOfStages[i] = new Stage(null, startDate, endDate, startDate, endDate, ((i==0)?true:false));
        }

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

    public String getProjectInfo() {
		String msg = "";
        
        msg = "\nName: " + nameproject + "\nClient: " + clientName + "\nInitial Date: " + getStartDateFormated() + 
		"\nFinal Date: " + getEndDateFormated() + "\nTotalBudget: " + budget + ".\n";

        return msg;
	}

    public boolean createStage2(String TypeStage, Calendar startDatePlanned, Calendar endDatePlanned, Calendar startDateReal, Calendar endDateReal, boolean StatusStage){
		//Inicialización de variable de enum Stages
		
		Stages stageSelected = Stages.INITIATION ;

		//Forma 1

		/**
		 *  int stageS
		 * stageSelected = Stages.values()[stageS-1];
		*/

        switch (TypeStage) {
			case "1":
				stageSelected = Stages.INITIATION;
				
				break;
			case "2":
				stageSelected = Stages.ANALYSIS;
				break;
			case "3":
				stageSelected = Stages.DESIGN;
				break;
			case "4":
				stageSelected = Stages.EXECUTION;
				break;
			case "5":
				stageSelected = Stages.CLOSING;
				break;
			case "6":
				stageSelected = Stages.MONITORING;
				break;
			case "7":
				stageSelected = Stages.CONTROL;
				break;
		}
		
		Stage newStage = new Stage(stageSelected, startDatePlanned, endDatePlanned, startDateReal, endDateReal, StatusStage);

        /*for(int i=0; i <listOfStages.length; i++){

			if(listOfStages[i] == null){

				listOfStages[i] = newStage;

				return true;
			}
		}*/
        
        return false;

    }


	public boolean nextStage(int cont, int stageOption) {

		Calendar date = new GregorianCalendar(00, 01, 00);
	
		listOfStages[stageOption].setStatusStage(true);
		listOfStages[stageOption].setStartDatePlanned(date);
		listOfStages[stageOption].setEndDatePlanned(date);
		listOfStages[stageOption].setStartDateReal(date);
		listOfStages[stageOption].setEndDateReal(date);
	
		Stages[] stagesArray = {Stages.INITIATION, Stages.ANALYSIS, Stages.DESIGN, Stages.EXECUTION, Stages.CLOSING, Stages.DESIGN, Stages.CONTROL};
		Stages selectedStage = stagesArray[stageOption];
	
		switch (cont) {
			case 2:
				listOfStages[stageOption].setTypeStage(selectedStage);
				return true;
			case 3:
				listOfStages[stageOption].setTypeStage(selectedStage);
				return true;
			default:
				if (selectedStage != null) {
					listOfStages[stageOption].setTypeStage(selectedStage);
					return true;
				}
		}
	
		return false;
	}

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

    public String showStages(int stageOption, int num){
        String msg="";
    
       if (num==1){
        	msg=listOfStages[stageOption].toStringStageReal();
       }else{
        	msg =listOfStages[stageOption].toStringStage();
       }
        return msg;
    }

    public void registerCapsule(String id, String description, String type, String lessonLearned){
        for(int i = 0; i < listOfStages.length; i++){
            if(listOfStages[i].getStatusStage()){
                listOfStages[i].registerCapsule(id, description, type, lessonLearned);

            }
        }
    }

    public void approveCapsule(String id, String status){
        for(int i = 0; i < listOfStages.length; i++){
            if(listOfStages[i].getStatusStage()){
                listOfStages[i].approveCapsule(id, status);

            }
        }
    }

    public Stage searchStage(Stages stageToSearch) {
        for (int i = 0; i < listOfStages.length; i++) {
            if (listOfStages[i].getTypeStage().equals(stageToSearch)) {
                return listOfStages[i];
            }
            
        }
        return null;
    }

    public void moveToNextStage() {
        for (int i = 0; i < listOfStages.length; i++) {
            if (listOfStages[i].getStatusStage()) {
                listOfStages[i].setStatusStage(false);
                listOfStages[i+1].setStatusStage(true);
                return;
            }
        }
    }
}

