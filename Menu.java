public class Menu {
    public void printMenu() {
        System.out.println("What is your major");
        System.out.println("CS  - Computer Science");
        System.out.println("ME  - Mechanical Engineering");
        System.out.println("BE  - Biomedical Engineering");
        System.out.println("AE  - Aerospace Engineering");
        System.out.println("CE  - Civil Engineering");
        System.out.println("EE  - Electrical Engineering");
        System.out.println("BIO - Biology");
        System.out.print("Select your choice: ");
    }
}

// to override 
class Menu2 extends Menu {
    @Override
    public void printMenu() {
		System.out.println("What is your major");
        System.out.println("CS  - Computer Science");
        System.out.println("ME  - Mechanical Engineering");
        System.out.println("BE  - Biomedical Engineering");
        System.out.println("AE  - Aerospace Engineering");
        System.out.print("Select your choice: ");
    }
}
class Menu3 extends Menu{
    @Override
    public void printMenu(){
    System.out.println("\nWhat would you like to do today?");
    System.out.println("1. Check on internship opportunities for you major:");
    System.out.println("2. Check and select grants, scholarships and funds:");
    System.out.println("3. Read the information on your file currently:");
    System.out.println("4. Clear your file:");
    System.out.println("5. Exit");
}
}