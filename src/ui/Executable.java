package ui;

import java.util.Scanner;

import model.Controller;

public class Executable {
        
    private Scanner reader;
    private Controller controller;

    public Executable() {

        reader = new Scanner(System.in);
        controller = new Controller();
    }

    public static void main(String[] args) {

        Executable exe = new Executable();
        exe.menu();

    }

    /**
     * The menu method displays the options available to the user and takes in their input.
     * Depending on the input, the corresponding method is called from the Controller class.
     * The loop continues until the user selects option 6 to exit the program.
     */
    public void menu(){

        System.out.println("Welcome to Green SQA");
        boolean cond=false;

        while(!cond){
            System.out.println("1. Create project");
            System.out.println("2. Edit stages of a project");
            System.out.println("3. Culminate stages of a project");
            System.out.println("4. Register capsules");
            System.out.println("5. Approbate capsules");
            System.out.println("6. Publicate capsules");
            System.out.println("7. Other funtions");
            System.out.println("8. Exit");

            System.out.print("Select an option: ");
            int option=reader.nextInt();
            switch (option) {
                case 1:
                    registerProject();
                break;

                case 2:
                    editStage();
                break;

                case 3:
                    CulminateStage();
                break;

                case 4:
                    registerCapsule();
                break;

                case 5:
                   approveCapsule();
                break;

                case 6:
                    publishCapsule();
                break;

                case 7:
                    System.out.println("\n        -Choose the option you wish to perform-");
                    System.out.println("\n1. How many capsules are registered according to their type.");
                    System.out.println("2. List of lessons learned according to the registered capsules");
                    System.out.println("3. Inform the name of the project with more registration capsules.");
                    System.out.println("4. Capsules registered in a project according to the name of a collaborator");
                    System.out.println("5. Lessons learned by keyword (#)");
                    int option2 = reader.nextInt();
                    
                    switch (option2){
                        
                        case 1:
                            registerTypeCapsules();
                        break;

                        case 2:
                            viewListLessons();
                        break;

                        case 3:
                            projectCapsules();
                        break;

                        case 4:
                            searchCapsuleColab();
                        break;

                        case 5:
                            Consul();
                        break;
                    
                    }        
                

                case 8:
                    System.out.println("Goodbye!");
                    cond=true;
                break;

                default:
                    System.out.println("Invalid option. Please try again.");
                break;
                
            }

        }
    }   

    /**
     * The registerProject method prompts the user to enter information about a project and calls the registerProject method in the Controller class.
     * If the project is registered successfully, a success message is displayed. Otherwise, an error message is displayed.
     */
    public void registerProject(){

        
        System.out.println("\nInsert the information of the project:");
        reader.nextLine();
        System.out.println("Name of project");
        String nameProject = reader.nextLine();

        System.out.println("Name of client");
        String nameClient = reader.nextLine();

        System.out.println("Insert budget of project");
        Double budget=reader.nextDouble();
        System.out.println("Name of Green's manager");
        reader.nextLine();
        String managerName=reader.nextLine();
        System.out.println("Phone of "+managerName);
        String phoneManager=reader.nextLine();

        System.out.println("Planned start date of the project");
        System.out.println("Day");
        int dayS = reader.nextInt();
        System.out.println("Month");
        int monthS = reader.nextInt();
        System.out.println("Year");
        int yearS = reader.nextInt();

        System.out.println("Planned end date of the project");
        System.out.println("Day");
        int dayE = reader.nextInt();
        System.out.println("Month");
        int monthE = reader.nextInt();
        System.out.println("Year");
        int yearE = reader.nextInt();

        if(controller.registerProject(nameProject, nameClient, dayS, monthS, yearS, dayE, monthE,yearE,  budget, managerName, phoneManager)){

            System.out.println("\nThe project is registered correctly\n");
        }else{
            
            System.out.println("The project is Could not register");
        }

        
    }
    /**
     * Displays a list of projects and prompts the user to select a project to edit its stage. The method then prompts the user 
     * to enter the planned start date and the number of months the stage is going to take. The method then calls the editStage 
     * method in the Controller class to update the stage's information.
     */
    private void editStage(){

        String consult = controller.showProjects();

        if(consult.equals("")){
            System.out.println("There are not create projects");
        }else{
            System.out.println(consult);
            System.out.println("\nEnter with a number to which project you wish to edit the stage ");
            int stageOption = reader.nextInt();
            System.out.println("\n-This is the current stage of this project:");
            System.out.println(controller.showCurrentStages(stageOption-1)); 
            System.out.println("\n-MOOD EDITION-");
            System.out.println("Enter the planned start date");
            System.out.println("Enter the day");
            int dateSP = reader.nextInt();
            System.out.println("Enter the month");
            int monthSP = reader.nextInt();
            System.out.println("Enter the year");
            int yearSP = reader.nextInt();
            
            System.out.println("\nEnter the number of months the stage is going to take");
            int mesesCantidad=reader.nextInt();

            if(controller.editStage((stageOption-1), dateSP, (monthSP-1), yearSP, mesesCantidad)){
                System.out.println("Stage edited correctly");
                System.out.println("This is how it looks:");
                System.out.println(controller.showCurrentStages(stageOption-1));
            }else{
                System.out.println("I cannot edit the stage");
            }
           
        }
    }
    /**
     * Displays a list of projects and prompts the user to select a project to culminate its stage. The method then calls the culminateStage method in the Controller class to update the stage's status.
     */
    public void CulminateStage(){
        String Consulta=controller.showProjects();

        if(Consulta.equals("")){
            System.out.println("No projects created");
        }else{
            System.out.println("These are the projects created: ");
            System.out.println(Consulta);
            System.out.println("Which project would you like to complete? -Mark a number");
            int optionP=reader.nextInt();
            System.out.println("This is the current stage of this project");
            System.out.println(controller.showCurrentStages(optionP-1));
            System.out.println("Enter the actual date Initial");
            System.out.println("Enter the day");
            int diaIR=reader.nextInt();
            System.out.println("Enter the month");
            int mesIR=reader.nextInt();
            System.out.println("Enter the year");
            int yearIR=reader.nextInt();
            System.out.println("Enter the actual date Final");
            System.out.println("Enter the day");
            int diaFR=reader.nextInt();
            System.out.println("Enter the month");
            int mesFR=reader.nextInt();
            System.out.println("Enter the year");
            int yearFR=reader.nextInt();

            if(controller.culminateStage(optionP-1, diaIR, mesIR, yearIR, diaFR, mesFR, yearFR)){
                System.out.println("Stage successfully completed");
                System.out.println("The next stage is now available.");
            }else{
                System.out.println("Ocurrio un problema");
            }
            
        }
    }

    /**The registerCapsule method prompts the user to input the details of a new capsule unit 
     * (ID, description, type, and lesson learned) and registers it in the system using a controller 
     * class. If the registration is successful, it prints a success message; otherwise, it prints 
     * an error message.
     */
    public void registerCapsule(){
        String Consulta=controller.showProjects();

        if(Consulta.equals("")){
            System.out.println("No projects created");
        }else{
            System.out.println("These are the projects created: ");
            System.out.println(Consulta);
            System.out.println("To which project would you like to register the capsule? -Enter a number");
            int optionP=reader.nextInt();
            System.out.println("The Capsule will be registered at this stage.");
            System.out.println(controller.showCurrentStages(optionP-1));
            System.out.println("Enter capsule details:");
            reader.nextLine();
            System.out.print("ID: ");
            String id = reader.nextLine();

            System.out.print("Description: ");
            String description = reader.nextLine();

            System.out.print("Type:\n \n1.TECHNICIAN \n2.MANAGEMENT\n3.DOMAIN \n4.EXPERIENCES\n");
            int type = reader.nextInt();
            reader.nextLine();

            System.out.println("Enter the name of the collaborator");
			String colabName=reader.nextLine();
			System.out.println("enter the job title of "+colabName);
			String jobTitle=reader.nextLine();

            System.out.print("Lesson learned: ");
            String lessonLearned = reader.nextLine();
            
            if(controller.registerCapsule(id, description, type, colabName, jobTitle, lessonLearned, optionP-1)){
            System.out.println("\nCapsule unit register\n");
             }else{
            System.out.println("Error, can't register capsule unit register");
             }
        }
        

    }

    /**The approveCapsule method prompts the user to input the ID of a capsule unit 
     * that needs to be approved and the new status (approved or not approved). It then 
     * calls a method in the controller class to update the status of the unit. If the 
     * update is successful, it prints a success message; otherwise, it prints an error
     *  message.
     */
    public void approveCapsule(){
    
        String Consulta=controller.showProjects();

        if(Consulta.equals("")){
            System.out.println("No projects created");
        }else{
            System.out.println("These are the projects created: ");
            System.out.println(Consulta);
            System.out.println("Which project would you like to approve the capsule for? -Enter a number");
            int optionP=reader.nextInt();
            System.out.println("These are the capsules of this project in the Active stage");
            System.out.println(controller.showCapsules(optionP-1));
            System.out.println("Which Capsule wishes to approve");
            int optionCapsule=reader.nextInt();
            if(controller.approveCapsule(optionP-1, optionCapsule-1)){
                System.out.println("Capsule properly approved");
                System.out.println("This is how your capsule looked");
                System.out.println(controller.showCapsulesSelect(optionP-1, optionCapsule-1));
            }else{
                System.out.println("Capsule could not be approved");
            }
            
        }
    }

    /**
     *  Displays a list of capsules and prompts the user to select a capsule to publish. The method 
     * then calls the publishCapsule method in the Controller class to update the capsule's status.
     */
    public void publishCapsule(){
        String Consulta=controller.showProjects();

        if(Consulta.equals("")){
            System.out.println("No projects created");
        }else{
            System.out.println("These are the projects created: ");
            System.out.println(Consulta);
            System.out.println("To which project would you like to publish the capsule? -Enter a number");
            int optionP=reader.nextInt();
            System.out.println("These are the capsules of this project in the Active stage");
            System.out.println(controller.showCapsules(optionP-1));
            System.out.println("What Capsule wants to publish");
            int optionCapsule=reader.nextInt();
            System.out.println("Enter the URL");
            reader.nextLine();
            String URL=reader.nextLine();
            if(controller.publishCapsule(optionP-1, optionCapsule-1, URL)){
                System.out.println("\nCapsule published correctly");
                System.out.println("This is how your capsule looked");
                System.out.println(controller.showCapsulesSelect(optionP-1, optionCapsule-1));
            }else{
                System.out.println("Capsule could not be approved");
            }
            
        }
    }

    /**
     * Displays the number of capsules registered according to their type.
     */
    private void registerTypeCapsules(){
		String Consulta=controller.registerTypeCapsules();

		if(Consulta.equals("")){
			System.out.println("No capsules registered");
		}else{
			System.out.println(controller.registerTypeCapsules());
		}
	}

    /**
     *  Displays the name of the project with the most registered capsules.
     */
    private void projectCapsules(){
    
		String Consulta=controller.projectCapsules();

		if(Consulta.equals("")){
			System.out.println("No projects with registered capsules");
		}else{
			System.out.println(controller.projectCapsules());
		}
	}

    /**
     *  Displays a list of lessons learned according to the registered capsules.
     */
    public void viewListLessons(){
        System.out.println("-Stages-");
        System.out.println("1. INITIATION");
        System.out.println("2. ANALYSIS");
        System.out.println("3. DESIGN");
        System.out.println("4. EXECUTION");
        System.out.println("5. CLOSING");
        System.out.println("6. MONITORING");

        int stage = reader.nextInt();
        reader.nextLine();
        stage--;

        String msg = "";
        msg = controller.lessons(stage);
        System.out.println(msg);
    }

    /**
     * Displays the capsules registered in a project according to the name of a collaborator.
     */
    private void searchCapsuleColab(){
        reader.nextLine();

        System.out.println("Enter the name of the collaborator: ");
        String colabName = reader.nextLine();
    
        String result = controller.searchCapsuleColab(colabName);
    
        if(result.equals("")){
            System.out.println("Collaborator has not registered any capsule.");
        }else{
            System.out.println(result);
        }
    }
    
    /**
     * Displays the lessons learned by keyword (#)
     */
    public void Consul(){
        System.out.println("Enter the word to search for");
        reader.nextLine();
        String word=reader.nextLine();
        if(controller.showWord(word).equals("")){
            System.out.println("Word not found");
        }else{
            System.out.println(controller.showWord(word));
        }
    }
} 
