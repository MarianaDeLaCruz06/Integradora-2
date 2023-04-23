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
		listOfProject[0]=new Project("Mandarina", "Lucas", Calendar.getInstance(), Calendar.getInstance(), 870.000, "Carlos", "jdsjijdisjid");
	}

	/**
	 * Registers a new project with the given parameters.
	 * @param nameProject the name of the project
	 * @param nameClient the name of the client
	 * @param day the start day of the project
	 * @param monthS the start month of the project
	 * @param yearS the start year of the project
	 * @param dateE the end day of the project
	 * @param monthE the end month of the project
	 * @param yearE the end year of the project
	 * @param budget the budget of the project
	 * @param managerName the name of the project manager
	 * @param managerPhone the phone number of the project manager
	 */
	public boolean registerProject(String nameProject, String nameClient, int day, int monthS, int yearS, int dateE, int monthE, int yearE, Double budget, String managerName, String managerPhone){
        
        
        Calendar initialDate = createFecha(day, monthS, yearS);
        Calendar finalDate = createFecha(dateE, monthE, yearE);

        Project newProject = new Project(nameProject, nameClient, initialDate, finalDate, budget, managerName, managerPhone);

		boolean indicador=false;

        for(int i=0; i < listOfProject.length; i++){
			if(listOfProject[i]==null&&!indicador){
				listOfProject[i] = newProject;
				indicador=true;
			}
		}
        
        
        return indicador;

    }
	/**
	 * Returns a string with information about all registered projects.
	 * @return a string with information about all registered projects
	 */
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
	/**
	 * Changes the status of a stage to "completed" and sets its actual start and end dates.
	 * @param optionP  the index of the project in the listOfProject array
	 * @param diaIR the actual start day of the stage
	 * @param mesIR the actual start month of the stage
	 * @param yearIR  the actual start year of the stage
	 * @param diaFR The day of the date on which the action is performed.
	 * @param mesFR The month of the date on which the action is performed
	 * @param yearFR The year of the date on which the action is performed.
	 * @return True if the stage has been successfully completed, false otherwise.
	 */
    public boolean culminateStage(int optionP, int diaIR, int mesIR, int yearIR, int diaFR, int mesFR, int yearFR){
		Calendar fechaRealFinal=createFecha(diaFR, mesFR, yearFR);
		Calendar fechaRealInicial=createFecha(diaIR, mesIR, yearIR);
		int EtapaActiva=listOfProject[optionP].ActiveStage();
		boolean indicador=false;
		if(indicador==false){
			listOfProject[optionP].getListOfStages()[EtapaActiva].setStartDateReal(fechaRealInicial);
			listOfProject[optionP].getListOfStages()[EtapaActiva].setStartDateReal(fechaRealFinal);
			listOfProject[optionP].getListOfStages()[EtapaActiva].setStatusStage(false);
			listOfProject[optionP].getListOfStages()[EtapaActiva+1].setStatusStage(true);
			indicador=true;
		}
		return indicador;
    }

	/**
	 * Edits the start and end dates of a stage in a project.
	 * @param stageOption The index of the project where the stage is located.
	 * @param dateSP The day of the planned start date of the stage.
	 * @param monthSP The month of the planned start date of the stage.
	 * @param yearSP The month of the planned start date of the stage.
	 * @param cantidadMeses The number of months to add to the planned start date to calculate the planned end date
	 * @return True if the stage was successfully edited, false otherwise
	 */
	public boolean editStage(int stageOption, int dateSP, int monthSP, int yearSP, int cantidadMeses){
		Calendar nuevafechaplanI=createFecha(dateSP, monthSP, yearSP);
		boolean indicador=false;
		if(indicador==false){
			int activa=listOfProject[stageOption].ActiveStage();
			listOfProject[stageOption].getListOfStages()[activa].setStartDatePlanned(nuevafechaplanI);
			Calendar nuevafechaplanF=nuevafechaplanI;
			nuevafechaplanF.add(Calendar.MONTH, cantidadMeses);
			listOfProject[stageOption].getListOfStages()[activa].setEndDatePlanned(nuevafechaplanF);
			indicador=true;
		}
		return indicador;
    }

	/**
	 * Returns a string with the information of the current stages of a project.
	 * @param stageOption The index of the project to show the stages of.
	 * @return A string with the information of the current stages of the project.
	 */
	public String showCurrentStages(int stageOption) {
		String message = "";
		message=listOfProject[stageOption].showCurrentStages();
		
		return message;
	}

	/**
	 * Registers a new capsule for the active stage of the project with the given ID.
	 * @param id The capsule ID.
	 * @param description The capsule description. 
	 * @param type The capsule type (1 = Technical, 2 = Management, 3 = Domain, 4 = Experiences).
	 * @param colabName  The name of the collaborator who created the capsule.
	 * @param jobTitle  The job title of the collaborator who created the capsule.
	 * @param lessonLearned The lesson learned from the capsule.
	 * @param OptionP The project ID.
	 * @return True if the capsule was registered successfully, false otherwise.
	 */
	public boolean registerCapsule(String id, String description, int type, String colabName, String jobTitle, String lessonLearned, int OptionP) {
		int StageActive=listOfProject[OptionP].ActiveStage();
		boolean indicador=false;
		if(indicador==false){
			TypeCapsule TipoCapsula= TypeCapsule.TECHNICAL;
			
			if(type==1){
			TipoCapsula= TypeCapsule.TECHNICAL;
			}else if(type==2){
			TipoCapsula= TypeCapsule.MANAGEMENT;
			}else if(type==3){
			TipoCapsula= TypeCapsule.DOMAIN;
			}else{
			TipoCapsula= TypeCapsule.EXPERIENCES;
			}

			for(int i=0; i<50;i++){
				if(listOfProject[OptionP].getListOfStages()[StageActive].getCapsules()[i]==null){
					listOfProject[OptionP].getListOfStages()[StageActive].getCapsules()[i]=new Capsule(id, description, TipoCapsula, colabName, jobTitle, lessonLearned);
					indicador=true;
					return indicador;
			}
		}
		}
		
		return indicador;
	}	

	/**
	 * Approves a capsule for a given project and stage.
	 * @param optionP the index of the project to approve the capsule for
	 * @param capsuleApr the index of the capsule to approve
	 * @return true if the capsule is successfully approved, false otherwise
	 */
	public boolean approveCapsule(int optionP, int capsuleApr){
		boolean indicador=false;
		int StageActive=listOfProject[optionP].ActiveStage();
		
		if(indicador==false){
			listOfProject[optionP].getListOfStages()[StageActive].getCapsules()[capsuleApr].setStatus(Status.APPROVED);
			indicador=true;
		}
		return indicador;
	}

	/**
	 * Publishes a capsule for a given project, stage, and URL.
	 * @param optionP the index of the project to publish the capsule for
	 * @param capsuleApr the index of the capsule to publish
	 * @param URL the URL to publish the capsule to
	 * @return true if the capsule is successfully published, false otherwise
	 */
	public boolean publishCapsule(int optionP, int capsuleApr, String URL){
		boolean indicador=false;
		if(indicador==false){
			int StageActive=listOfProject[optionP].ActiveStage();
			listOfProject[optionP].getListOfStages()[StageActive].getCapsules()[capsuleApr].setPublish(true);;
			listOfProject[optionP].getListOfStages()[StageActive].getCapsules()[capsuleApr].setURL(URL);
			indicador=true;
		}
		return indicador;
	}

	/**
	 * Returns a string representation of a selected capsule for a given project and stage.
	 * @param optionP the index of the project to show the capsule for
	 * @param capsuleApr the index of the capsule to show
	 * @return a string representation of the selected capsule
	 */
	public String showCapsulesSelect(int optionP, int capsuleApr){
		String msg="";
		int StageActive=listOfProject[optionP].ActiveStage();
		msg="\n"+listOfProject[optionP].getListOfStages()[StageActive].getCapsules()[capsuleApr].toStringMAX();
		return msg;

	}

	/**
	 * Returns a string representation of all capsules for a given project and stage.
	 * @param optionP the index of the project to show all capsules for
	 * @return a string representation of all capsules for the selected project and stage
	 */
	public String showCapsules(int optionP){
		String msg="";
		int StageActive=listOfProject[optionP].ActiveStage();
		for(int i=0; i<50;i++){
			if(listOfProject[optionP].getListOfStages()[StageActive].getCapsules()[i]==null){
				return msg;
			}else{
				msg+="\n"+"\n"+(i+1)+". "+listOfProject[optionP].getListOfStages()[StageActive].getCapsules()[i].toString();
			}
		}

		return msg;

	}

	/**
	 * Searches for a specific word within the descriptions of all capsules in all projects, and returns information about the project, stage, and capsule where the word was found.
	 * @param word the word to search for in the capsule descriptions
	 * @return  a String with information about the project, stage, and capsule where the word was found
	 */
	public String showWord(String word){
		String msg="";
		for(int i=0; i<listOfProject.length; i++){
            if(listOfProject[i]!=null){
                for (int j=0; j<6;j++){
					for(int s=0; s<50;s++){
						if(listOfProject[i].getListOfStages()[j].getCapsules()[s]!=null){
							if(listOfProject[i].getListOfStages()[j].getCapsules()[s].getDescription().contains(word)){
								msg+="\nWord found in: \n";
								msg+="Project: "+listOfProject[i].getName()+"\n";
								msg+="Stage: "+listOfProject[i].getListOfStages()[j].getTypeStage()+"\n";
								msg+="Capsule Identifier: "+listOfProject[i].getListOfStages()[j].getCapsules()[s].getId()+"\n";
							}
						}
					}
				}
                    
        
            } 
        }
    
        return msg;
	}

	/**
	 * Registers the number of capsules of each type for all projects and returns a String with this information.
	 * @return  a String with the quantity of each type of capsule registered for all projects
	 */
	public String registerTypeCapsules(){
        String msg="";
        int ContadorT=0;
        int ContadorG=0;
        int ContadorD=0;
        int ContadorE=0;

        for(int i=0; i<listOfProject.length; i++){
            if(listOfProject[i]!=null){
                for (int j=0; j<6;j++){
				for(int s=0; s<50;s++){
					if(listOfProject[i].getListOfStages()[j].getCapsules()[s]!=null){
							if(listOfProject[i].getListOfStages()[j].getCapsules()[s].getType()==TypeCapsule.TECHNICAL){
								ContadorT++;
							}else if(listOfProject[i].getListOfStages()[j].getCapsules()[s].getType()==TypeCapsule.MANAGEMENT){
								ContadorG++;
							}else if(listOfProject[i].getListOfStages()[j].getCapsules()[s].getType()==TypeCapsule.DOMAIN){
								ContadorE++;
							}else if(listOfProject[i].getListOfStages()[j].getCapsules()[s].getType()==TypeCapsule.EXPERIENCES){
								ContadorD++;
							}

							msg+="\nQuantity Type of Capsules registered\n";
							msg+="-TECHNICAL: "+ContadorT+"\n";
							msg+="-MANAGEMENT: "+ContadorG+"\n";
							msg+="-DOMAIN: "+ContadorD+"\n";
							msg+="-EXPERIENCES: "+ContadorE+"\n";

						}
					}
				}
                    
        
            } 
        }
    
        return msg;
	}

	/**
	 * Method to get the project with the most capsules created.
	 * @return a String indicating the name of the project with the most capsules created and the total number of capsules registered
	 */
	public String projectCapsules(){
		String msg="";
		String msg2="";
		int cantMaxCapsules = 0;

		for(int i=0; i<listOfProject.length; i++){
            if(listOfProject[i]!=null){
				int capsulesContador = 0;
                for (int j=0; j<6;j++){
					for(int s=0; s<50;s++){
						if(listOfProject[i].getListOfStages()[j].getCapsules()[s]!=null){
							capsulesContador++;
						}
					}
				}
				if (capsulesContador>cantMaxCapsules){
					cantMaxCapsules = capsulesContador;
					msg = " The project whith the most capsules created is: " + listOfProject[i].getName() + " .";
					msg2 = "with a total of " + cantMaxCapsules + " capsules registred";
				}
			}
		}

		return msg + msg2;
	}

	/**
	 * Method to search for a capsule registered by a collaborator in any project.
	 * @param colabName  the name of the collaborator to search for
	 * @return a String indicating the name of the project, stage number, and capsule position of any capsules registered by the collaborator in any project
	 */
	public String searchCapsuleColab(String colabName){
		String msg = "";
		boolean found = false;
		
		for(int i=0; i<listOfProject.length; i++){
			if(listOfProject[i]!=null){
				for (int j=0; j<6; j++){
					for(int s=0; s<50; s++){
						if(listOfProject[i].getListOfStages()[j].getCapsules()[s] != null){
							if(listOfProject[i].getListOfStages()[j].getCapsules()[s].getColabName().equals(colabName)){
								found = true;
								msg += "Collaborator " + colabName + " has registered a capsule in project " + listOfProject[i].getName() + 
									" in stage " + (j+1) + ", capsule position " + (s+1) + ".\n";
							}
						}
					}
				}
			}
		}
		
		if(!found){
			msg = "Collaborator " + colabName + " has not registered any capsules in any project.";
		}
		
		return msg;
	}
	
	/**
	 * Returns a message with the lessons learned from the chosen project
	 * @param m to choose the project where the lesson is to be learned
	 * @return a message with the lessons learned from the chosen project
	 */
	public String lessons(int m){
		String msg = "";
        for (int i = 0; i < listOfProject.length; i++) {
			if(listOfProject[i] != null){
				msg += listOfProject[i].lessons(m)+"\n";
			}
		}
        return msg;
	}

	/**
	 * Creates a Calendar object with the given day, month, and year.
	 * @param dia the day of the month (1-31).
	 * @param mes the month (1-12)
	 * @param year the year.
	 * @return a Calendar object with the given day, month, and year.
	 */
	public Calendar createFecha(int dia, int mes, int year){
		Calendar fecha = new GregorianCalendar(year,mes-1,dia);
        return fecha;
	}

  
}

