package model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Controller {

	private Project[] listOfProject;

	/**
	* Constructor that initializes the capsules, projects, and stages arrays with default values.
	*/
    public Controller() {
        
		listOfProject = new Project[10];
	}

	/**
	* Method that registers a new project with the given parameters.
	* @param nameProject the name of the project
	* @param nameClient the name of the client
	* @param dateS the start day of the project
	* @param monthS the start month of the project
	* @param yearS the start year of the project
	* @param dateE the end day of the project
	* @param monthE the end month of the project
	* @param yearE the end year of the project
	* @param budget the budget of the project
	* @param managerName the name of the project manager
	* @param managerPhone the phone number of the project manager
	* @return true if the project is successfully registered, false otherwise
	*/
	public boolean registerProject(String nameProject, String nameClient, int dateS, int monthS, int yearS, int dateE, int monthE, int yearE, Double budget, String managerName, String managerPhone){
        
        
        Calendar initialDate = new GregorianCalendar(yearS, monthS, dateS);
        Calendar finalDate = new GregorianCalendar(yearE, monthE, dateE);

        Project newProject = new Project(nameProject, nameClient, initialDate, finalDate, budget, managerName, managerPhone);

		Calendar startDatePlanned = new GregorianCalendar(0000, (1), 00);
        Calendar endDatePlanned = new GregorianCalendar(0000, (1), 00);      
        Calendar startDateReal = new GregorianCalendar(0000, (1), 00);
        Calendar endDateReal = new GregorianCalendar(0000, (1), 00);

		createStage2("Stages.INITIATION", startDatePlanned, endDatePlanned, startDateReal, endDateReal, statusStage);


        for(int i=0; i < listOfProject.length; i++){

			if(listOfProject[i] == null){

				listOfProject[i] = newProject;
				return true;
			}
		}
        
        
        return false;

    }

	public String showProjects(){
        String msg="";
        for (int i=0; i<listOfProject.length;i++){
            if(listOfProject[i] == null){
				return msg;
			} else {
				msg +="\n"+(i+1)+". "+ listOfProject[i].getProjectInfo();
			}
		} 
        
        return msg;
    }


	
    public void createStage2(String projectName, String TypeStage, Calendar startDatePlanned, Calendar endDatePlanned, Calendar startDateReal, Calendar endDateReal, boolean StatusStage){
        for(int i = 0; i < listOfProject.length; i++){
            if(listOfProject[i].getName().equals(projectName)){
                listOfProject[i].createStage2(TypeStage, startDatePlanned, endDatePlanned, startDateReal, endDateReal, StatusStage)(TypeStage, startDatePlanned, endDatePlanned,startDateReal,endDateReal,StatusStage);

            }
        }
    }

	public void editStage(String projectName, Stages stage, int stageOption, int dateSP, int monthSP, int yearSP, int dateSR, int monthSR, int yearSR){
        for(int i = 0; i < listOfProject.length; i++){
            if(listOfProject[i].getName().equals(projectName)){
				Stage stageToEdit = listOfProject[i].searchStage(stage);
                if(stageToEdit != null){
					stageToEdit.editStage(stageOption, dateSP, monthSP, yearSP, dateSR, monthSR, yearSR);
					return;
				}
            }
        }
    }



	public void nextStage(String projectName, int cont, int stageOption){
        for(int i = 0; i < listOfProject.length; i++){
            if(listOfProject[i].getName().equals(projectName)){
                listOfStage[i].nextStage(cont, stageOption);
            }
        }
    }




  
}

