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
    int option = 0;
    public void menu(){

        System.out.println("Welcome to Green SQA");

        do {
            
            System.out.println("1. Create project");
            System.out.println("2. Edit stages of a project");
            System.out.println("3. Culminate stages of a project");
            System.out.println("4. Register capsules");
            System.out.println("5. Approbate capsules");
            System.out.println("6. Publicate capsules");
            System.out.println("7. Exit");

            System.out.print("Select an option: ");

            try {
                option = Integer.parseInt(reader.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid option. Please try again.");
                continue;
            }

            switch (option) {
                case 1:
                    registerProject();
                break;

                case 2:
                    editStage();
                break;

                case 3:
                    
                break;

                case 4:
                    registerCapsule();
                break;

                case 5:
                   approveCapsule();
                break;

                case 6:
                    showCapsule();
                break;

                case 7:
                    System.out.println("Goodbye!");
                break;

                default:
                    System.out.println("Invalid option. Please try again.");
                break;
            }
            
        }  while (option != 7);


    }

    /**
     * The registerProject method prompts the user to enter information about a project and calls the registerProject method in the Controller class.
     * If the project is registered successfully, a success message is displayed. Otherwise, an error message is displayed.
    */
    public void registerProject(){


        System.out.println("Insert the information of the project:");

        System.out.println("Name of project");
        String nameProject = reader.nextLine();

        System.out.println("Name of client");
        String nameClient = reader.nextLine();

        System.out.println("Planned start date of the project");
        System.out.println("Date");
        int dateS = Integer.valueOf(reader.nextLine());
        System.out.println("Month");
        int monthS = Integer.valueOf(reader.nextLine());
        System.out.println("Year");
        int yearS = Integer.valueOf(reader.nextLine());

        System.out.println("Planned finish date of the project");
        System.out.println("Date");
        int dateE = Integer.valueOf(reader.nextLine());
        System.out.println("Month");
        int monthE = Integer.valueOf(reader.nextLine());
        System.out.println("Year");
        int yearE = Integer.valueOf(reader.nextLine());

        System.out.println("Dime la cantidad de meses por etapa espaciada ej: 3 4 5");
        // 4 5 6 7 = [4,5,6,7]
        String[] monthString = reader.nextLine().split(" ");
        int[] monthsArray = new int[monthString.length];
        for (int i = 0; i < monthsArray.length; i++) {
            monthsArray[i] = Integer.valueOf(monthString[i]);
        }

        System.out.println("Project budget");
        double budget = Double.valueOf(reader.nextLine());

        for (int i = 0; i < 6; i++) {
            int months = Integer.valueOf(reader.nextLine());

            /* 
            System.out.println("""
                    1. INITIATION
                    2. ANALYSIS
                    3. DESIGN
                    4. EXECUTION
                    5. CLOSING
                    6. MONITORING
                    7. CONTROL""");

            int stageSlected = Integer.valueOf(reader.nextLine());
            */

            System.out.println("Name of manager");
            String managerName = reader.nextLine();

            System.out.println("Number of manager");
            String managerPhone = reader.nextLine();
            
            if(controller.registerProject(nameProject, nameClient, dateS, monthS, yearS, dateE, monthE, yearE, budget, managerName, managerPhone)){

                System.out.println("The project is registered correctly");
            }else{
                
                System.out.println("The project is Could not register");
            }

        }
    }

    private void editStage(){

        String consult = controller.showProjects();

        if(consult.equals("")){
            System.out.println("There are not create projects");
        }else{
            System.out.println("\nIngrese con un numero a que proyecto desea editarle la etapa ");
            int stageOption = Integer.valueOf(reader.nextLine());
            System.out.println("\n-Esta es la etapa actual de ese proyecto:");
            int num=2;
            System.out.println(controller.showStages(stageOption-1, num)); 
            System.out.println("\n-MODO EDICION-");
            System.out.println("Ingrese la fecha inicial planeada");
            System.out.println("Date");
            int dateSP = Integer.valueOf(reader.nextLine());
            System.out.println("Month");
            int monthSP = Integer.valueOf(reader.nextLine());
            System.out.println("Year");
            int yearSP = Integer.valueOf(reader.nextLine());
            
            System.out.println("\nIngrese la fecha final planeada");
            System.out.println("-Ingrese el dia");
            int dateEP = Integer.valueOf(reader.nextLine());
            System.out.println("\n-Ingrese el mes");
            int monthEP = Integer.valueOf(reader.nextLine());
            System.out.println("\n-Ingrese el año");
            int yearEP = Integer.valueOf(reader.nextLine());

            if(controller.editStage((stageOption-1), dateSP, (monthSP-1), yearSP, dateEP, (monthEP-1), yearEP)){
                System.out.println("Etapa editada correctamente");
            }else{
                System.out.println("No se puedo editar la etapa");
            }
        }
    }

    /**The registerCapsule method prompts the user to input the details of a new capsule unit 
     * (ID, description, type, and lesson learned) and registers it in the system using a controller 
     * class. If the registration is successful, it prints a success message; otherwise, it prints 
     * an error message.
     */
    public void registerCapsule(){
        System.out.println("Enter capsule details:");

        System.out.print("ID: ");
        String id = reader.nextLine();

        System.out.print("Description: ");
        String description = reader.nextLine();

        System.out.print("Type: ");
        String type = reader.nextLine();

        System.out.print("Lesson learned: ");
        String lessonLearned = reader.nextLine();

        if(controller.registerCapsule(id, description, type, lessonLearned)){
            System.out.println("Capsule unit register");
        }else{
            System.out.println("Error, can't register capsule unit register");
        }

    }
    /**The approveCapsule method prompts the user to input the ID of a capsule unit 
     * that needs to be approved and the new status (approved or not approved). It then 
     * calls a method in the controller class to update the status of the unit. If the 
     * update is successful, it prints a success message; otherwise, it prints an error
     *  message.
    */
    public void approveCapsule(){
    
        System.out.println("Please, type the id of the needed unit");
        String id = reader.nextLine();
        String approbation = "";
        boolean condition = false;
        while(!condition){
            System.out.println("Please type the new status: 1. for Approved. 2. Not approved.");
            int choose = reader.nextInt();
            if(choose==1){
            approbation = "Approved";
            condition= true;
            }
            if(choose==2){
            approbation = "Not approved";
            condition = true;
            }
        }
        if(controller.approveCapsule(id, approbation)==true){
            System.out.println("The unit status has been updated");
        }
        else{
            System.out.println("Error, can't change unit status. \nPlease verify if the unit is registered");
        }
    
    }

    /** The showCapsule method displays the information registered in the system by calling 
     * a method in the controller class. If there is no information to display, it prints a
     *  message saying so.
    */
    public void showCapsule() {
		System.out.println("This is the information registered in the system: ");
		String consult = controller.getCapsule();

		if(consult.equals("")){
			System.out.println("Sorry, can't find information registered");
		}
		else{
			System.out.println(consult);
		}
	}
    
    public static void createStage() {
        Scanner rd = new Scanner(System.in);
        String datos = rd.nextLine();
        
    }
    
} //project[3].stage[5].capsule[5].getX
