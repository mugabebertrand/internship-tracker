import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.concurrent.ThreadLocalRandom;
//Change access or something to private in order to maintain the privacy of the code 
class UserInfo {
    private String UsrName;
    private String Pass_wrd;

    public UserInfo(String UsrName, String Pass_wrd) {
        this.UsrName = UsrName;
        this.Pass_wrd = Pass_wrd;
    }

    public String getUsrName() { return UsrName; }
    public String getPass_wrd() { return Pass_wrd; }
    public void setUsrName(String UsrName) { this.UsrName = UsrName; }
    public void setPass_wrd(String Pass_wrd) { this.Pass_wrd = Pass_wrd; }
}
public class PathWay{
    public static void main(String[] args){
		Menu menu = new Menu();
		Menu2 menu2 = new Menu2();
		Menu3 menu3 = new Menu3();
        UserInfo currentUser = null;
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the Pathway+");
        int EscPara = 0;
		int attempt = 0;
		int year = 0;
		int rdk = 0;
		int Ndx = 0;
        String UsrName = "";
        String Pass_wrd = "";
        String Pass_wrd_Conf;
		String InfoStore = "";
        while(EscPara == 0){
        System.out.println("\nAre you a returning user?(Yes/No):");
        String UsrResp = input.nextLine();
        UsrResp = UsrResp.toLowerCase();
		UsrResp = UsrResp.replaceAll("\\s","");//To replace spaces in order to prevent Yes from not being accepted. 
        if(UsrResp.equals("yes")){
            System.out.println("Enter your username:");
            UsrName = input.nextLine();
			System.out.printf("Welcome back %s,\nEnter your password:\n",UsrName);
			Pass_wrd = input.nextLine();
			Pass_wrd = Pass_wrd.trim();//prevents trailing spaces
			UserInfo ReturningUser = new UserInfo(UsrName,Pass_wrd);
            try(BufferedReader reader = new BufferedReader(new FileReader(ReturningUser.getUsrName()))){
				reader.readLine();
                Pass_wrd_Conf = reader.readLine();
                while(attempt<3){
                if(Pass_wrd.equals(Pass_wrd_Conf)){
                    System.out.println("\n\nPassword confirmed\n");
                    currentUser = new UserInfo(UsrName, Pass_wrd_Conf);
                    attempt = 3;
                    EscPara++;
                }
                else{
                    System.out.println("That password is incorrect please try again");
                    attempt++;
                    Pass_wrd = input.nextLine();
                    if(attempt==3){
                        System.out.println("Too many failed logins. Terminating");
                        System.exit(0);
                    }
                }
            }

            }
            catch(Exception e){
                System.err.println("Account doesn't exist.\nMaybe you intended to create an account?");
            }
            //Continue account process from here. 
        }
        else if(UsrResp.equals("no")){
            System.out.println("Lets get an account started for you!");
            System.out.println("What username would you like to use?:");
            UsrName = input.nextLine();
            System.out.println("What is the password you would like to use?");
            Pass_wrd = input.nextLine();
            UserInfo newUser = new UserInfo(UsrName,Pass_wrd);
            try{BufferedWriter file = new BufferedWriter(new FileWriter(newUser.getUsrName()));
                file.write(newUser.getUsrName());
                file.write("\n");
                file.write(newUser.getPass_wrd());
                //You could utilize a randomization function in order to provide the user a 4 number code to enter to confirm the new account 
                file.close();
                System.out.println("Your account is now setup.\nRun program again with your account information.");
                input.close();
                System.exit(0);
            }
            catch(Exception e){
                System.err.println("File Failed to Open.");
                input.close();
                System.exit(0);
            }
            EscPara++;
        }
        else{
            System.out.println("Enter yes or no for you option choice:");
        }
    }
    //Continue coding here
	int arkx = 0;
	while(arkx!=1){
	menu3.printMenu();
    int choice = input.nextInt();
    if(choice==1){
		String internship;
        menu.printMenu();//accesses the menu option from the override file. 
        input.nextLine();
        String major = input.nextLine();
		major = major.toUpperCase();
		while(rdk==0){
        System.out.println("What year are you in? 1-4:");
		year = input.nextInt();
			if(year<=4 && year>=1){
				rdk = 1;
			}
			else{
				System.out.println("Try selecting a number that's between 1-4 years.");
			}
		}
		UniYear YearCurr1 = convert(year);
		internship = getInternship(major, YearCurr1);//Will function with rest of code 
		attempt = 0;
		int ark = 0;
		if(internship.contains("unavaliable")){
			System.out.println("\n");
		}
		else{
		do{
        System.out.println("\n\nAre you interested in accepting this position?(yes,no,exit)\n");
		//to consume the newline character 
		int countr = 0;
		countr++;
		String UsrResp = input.nextLine();
		UsrResp = UsrResp.toLowerCase();
		if(UsrResp.equals("yes")){
		//Write code here for file writing of the internship desired, Make sure to only include the position and not the whole description
		System.out.println("\nInternship Accepted!");
		try(BufferedWriter UsrFile = new BufferedWriter(new FileWriter(currentUser.getUsrName(),true))){
			UsrFile.write("\nMajor\n"+major);
			UsrFile.write("\n"+internship);
			//UsrFile.write("\nTotal Scholarship/Grant/Award Money:\n--------------------------------------------------");
			UsrFile.close();
			ark = 1;
			
		}
		catch(Exception e){
			System.err.println("Critical System Error");
			System.exit(0);
		}
        }
		else if(UsrResp.equals("no")){
			internship = getInternship(major, YearCurr1);
		}
		else if(UsrResp.equals("exit")){
			ark=1;
		}
		else{
			System.out.println("Please enter yes/no");
		}
		if(countr!=0){
		//input.nextLine();
	}
	}while(ark!=1);
    }
}
    else if(choice==2){
		if(Ndx<1){
		menu2.printMenu();//Prints modified version of print statement 
        input.nextLine();
		String major = input.nextLine();// full major name

        System.out.println("Enter your academic year (1-4):");
    	year = input.nextInt();
		int ark = 0;
		while(ark!=1){
		if(year<=4&&year>=1)
			ark++;
		else{
			System.out.println("Please enter a year between 1 and 4!\n");
			System.out.println("Enter your academic year (1-4):");
			year = input.nextInt();	
			UniYear Year_Curr = convert(year);//Saved for future Enum usage. currently unused because it doesn't work with the
		}
		}
        // Call scholarship function
        double award = getScholarship(major,year);

        System.out.printf("\nYou qualify for a scholarship of $%.2f this year.\n", award);
		rdk = 0;
		while(rdk!=1){
		input.nextLine();
		System.out.println("Would you like to accept this scholarship/grant/award?(yes/no)");
		String UsrResp2 = input.nextLine();
		UsrResp2 = UsrResp2.toLowerCase();
		if(UsrResp2.equals("yes")){
			try(BufferedWriter Adolo = new BufferedWriter(new FileWriter(currentUser.getUsrName(),true))){
				Adolo.write("\n\nTotal Scholarship/Grant/Award Money:\n\n--------------------------------------------------");
				Adolo.write("\n"+award);
				System.out.println("\nOffer accepted");
				Adolo.close();
				Ndx++;
				rdk++;
			}
			catch(FileNotFoundException e){
				System.err.println("\nCritical System Error 482");
			}
			catch(Exception e){
				System.err.println("\nGeneral Error 302");
			}
		}
		else if(UsrResp2.equals("no")){
			System.out.println("Scholarship declined");
			rdk++;
		}
		else{
			System.out.println("Please enter a valid option.");
		}
    }
	}
	else{
		System.out.println("\nYou cannot accept more than 1 Scholarship.\n");
	}
}
    else if(choice==3){//Write the total contents of the files 
    try(BufferedReader RXDT = new BufferedReader(new FileReader(currentUser.getUsrName()))){
		RXDT.readLine();
		RXDT.readLine();
		if(RXDT.readLine()==null){
			System.err.print("\nYou have no information in your file");
		}
		InfoStore = RXDT.readLine();
	    while((InfoStore = RXDT.readLine()) != null){
    	    System.out.println("\n" + InfoStore);
    	}
		System.out.println("\n");
		RXDT.close();
	}
	catch(FileNotFoundException e){
		System.err.println("Error Critical 309 File Not Found");
		System.exit(0);
	}
	catch(Exception e){
		System.err.println("Critical Error 206");
		System.exit(0);
	}
	}
	else if(choice==5){
		System.out.println("Exiting");
		System.exit(0);
	}
	else if(choice==4){
		System.out.println("This is the option to delete all the information on your file currently\n\n");
		int rvk = 0;
		String UsrResp;
		while(rvk==0){
			System.out.println("Are you sure you would like to proceed with this choice?(yes/no)");
			input.nextLine();//consume newline
			UsrResp = input.nextLine();
			UsrResp = UsrResp.toLowerCase();
			if(UsrResp.equals("yes")){
				try(BufferedReader LTVK = new BufferedReader(new FileReader(UsrName))){
					LTVK.readLine();
					LTVK.readLine();
					if(LTVK.readLine()==null){
						System.out.println("\nYou have no information to delete!");
						LTVK.close();
						break;
					}
				}
				catch(FileNotFoundException e){
					System.err.println("ERROR 921 FileNotFoundException");
				}
				catch(Exception e){
					System.err.println("General ERROR 738");
				}
				try(BufferedWriter LSRKS = new BufferedWriter(new FileWriter(UsrName))){
					LSRKS.write(UsrName+"\n");
					LSRKS.write(Pass_wrd);
					System.out.println("File information deleted successfully");
					LSRKS.close();
					rvk++;
				}
				catch(FileNotFoundException e){
					System.err.println("Critical Error 2026 File not found");
				}
				catch(Exception e){
					System.err.println("General Error 992");
				}
			}
			else if(UsrResp.equals("no")){
				rvk++;//to exit the loop
			}
			else{
				System.out.println("Please enter a valid option of either yes or no.");
			}
		}
	}
	else{
		System.out.println("Invalid Option\nPlease try again");
	}
}
    input.close();
}
public static double getScholarship(String major,int year) {

        major = major.toUpperCase();

        double baseAmount = 1000.00;
        double yearlyIncrease;
        String scholarshipName;

        switch (major) {

            case "CS":
                yearlyIncrease = 500.0;
                scholarshipName = "Tech Innovators Scholarship";
                break;
            case "ME":
                yearlyIncrease = 450.0;
                scholarshipName = "Engineering Excellence Grant";
                break;
            case "AE":
                yearlyIncrease = 600.0;
                scholarshipName = "Aerospace Advancement Award";
                break;
			case "BE":
				yearlyIncrease = 332;
				scholarshipName = "Bioengineering General Fund";
				break;
            default:
                yearlyIncrease = 336.0;
                scholarshipName = "Pathway+ General Support Grant";
                break;
        }
        double amount = baseAmount + (year * yearlyIncrease);
        System.out.printf(
            "\nMajor: %s\nScholarship: %s\nAward Amount This Year: $%.2f\n",
            major, scholarshipName, amount
        );
        return amount;
    }
    public static String getInternship(String major, UniYear year) {
        int randomInt;
	    String internship = "";
	    switch (major) {
	    case "CS":
            int min = 1;
            int max = 2;
	        switch (year) {
                case FIRST_YEAR://year 1
                randomInt = 1;
                    switch(randomInt){
                        case 1:
                            internship = "\nIntro to Coding Internship:\nIntro to Coding Internship provides a crucial transition from theoretical knowledge to practical application in the software development world.\nThis temporary, hands-on role allows aspiring coders to work on real-world projects under the guidance of experienced developers, applying fundamental programming skills in a professional setting. Interns typically gain experience with industry-standard programming languages (like Python, Java, or JavaScript), version control tools such as Git, and collaborative development practices like Agile methodologies and code reviews. Beyond technical skills, a successful internship enhances crucial soft skills like problem-solving, critical thinking, communication, and time management.\nIt serves as an invaluable opportunity for mentorship, networking, and building a professional project portfolio, often acting as a direct recruitment pipeline for full-time roles, significantly boosting a new coder's confidence and career prospects.";
                            break;
                    }
                break;
                case SECOND_YEAR://year 2
                randomInt = ThreadLocalRandom.current().nextInt(min,max+1);
                    switch(randomInt){
                        case 1:
                            internship = "\nSoftware Testing Internship:\nThe internship involves learning and executing various testing methodologies. Core responsibilities include reviewing requirements, designing test cases (both manual and automated), executing those tests, and meticulously logging defects in tracking systems like JIRA.\nInterns gain practical experience with essential testing tools (e.g., Selenium for automation) and develop a strong understanding of different testing types, such as functional testing, regression testing, and user acceptance testing (UAT). Beyond technical know-how, the role heavily cultivates attention to detail, critical thinking, and problem-solving abilities, as a tester must anticipate how a user might break the software.";
                        break;
                        case 2:
                            internship = "\nWeb Development Internship:\nWeb Development Internship is an entry-level position where interns contribute directly to the creation, modification, and maintenance of functional websites and web applications.\nThe core responsibility involves writing and reviewing code using foundational languages like HTML, CSS, and JavaScript, often under the guidance of a senior developer. Interns gain invaluable practical experience in front-end development, learning to implement responsive design to ensure sites work perfectly across all devices and building user interfaces with modern frameworks (such as React, Angular, or Vue.js). Depending on the role, they may also receive exposure to back-end tasks, including server-side logic, API development, and database management (using tools like SQL or MongoDB).\nBeyond coding, the intern learns professional best practices, including using Git for version control, participating in agile team ceremonies, debugging code, and effectively collaborating with designers and project managers.\nUltimately, this hands-on experience transforms theoretical knowledge into a highly marketable skill set, builds a crucial professional portfolio, and creates networking opportunities that are essential for launching a successful career in software or web development.";
                        break;
                        default:
                            System.err.println("\n\nError 308");
                    }
                break;
                case THIRD_YEAR://year 3 
                    max = 7;
                    randomInt = ThreadLocalRandom.current().nextInt(min,max+1);
                    switch(randomInt){
                        case 1:
                          internship = "\nCybersecurity Internship:\nA Cybersecurity Internship provides students and recent graduates with essential, hands-on experience in defending an organization's systems, networks, and data against real-world threats.\nIntern responsibilities are diverse and focused on the core areas of security operations, including assisting the security team with vulnerability assessments, conducting penetration testing exercises, and monitoring security tools like SIEM (Security Information and Event Management) systems to detect and respond to suspicious activity.\nThey are frequently involved in incident response, helping to analyze security events, trace the origin of a threat, and implement mitigation strategies. The experience is invaluable for developing a practical command of technical skills like network security configuration, forensic analysis, and scripting (often in Python) for automation.\nCrucially, a cybersecurity internship allows the individual to build a strong professional network, gain exposure to industry compliance standards (like NIST or ISO 27001), and develop essential soft skills like critical thinking, communication, and meticulous attention to detail, which are all vital for a successful and evolving career in the field.";  
                        break;
                        case 2:
                            internship = "\nAI Research Internship:\nAn AI Research Internship is a highly specialized role focused on contributing to the cutting-edge development of machine learning, deep learning, and artificial intelligence models.\nInterns work alongside senior researchers, typically on projects involving novel algorithms, advanced data processing, or the application of AI to solve complex, often theoretical, problems in areas like Natural Language Processing (NLP), Computer Vision, or Reinforcement Learning. Key responsibilities involve conducting extensive literature reviews, designing and executing experiments, preprocessing and analyzing large datasets, and implementing models using frameworks such as PyTorch or TensorFlow with a strong foundation in Python.\nThis internship is crucial for developing rigorous research methodology, deepening expertise in statistical analysis, and learning the process of documenting and publishing results, offering a direct pathway toward careers in AI research, academia, or advanced data science.";
                            break;
                        
                        case 3:
                            internship = "\nMobile App Development Internship:\nAn Mobile App Development Internship provides hands-on experience in building and optimizing applications for platforms like iOS and Android. The intern works closely with a development team, contributing directly to the design, coding, testing, and maintenance of features in a production mobile application.\nCore responsibilities include writing native code using languages like Swift/Kotlin or cross-platform code using frameworks like React Native or Flutter, and ensuring the app delivers a seamless user experience (UX) across various devices and screen sizes.\nInterns gain practical exposure to integrating APIs, managing local data storage, utilizing version control (Git), and engaging in the debugging and quality assurance (QA) processes essential for a public-facing application.\nUltimately, this internship accelerates the development of a specialized portfolio and provides a real-world understanding of the mobile application lifecycle, from conception through deployment to the App Store or Google Play.";
                            break;
                        case 4:
                            internship = "\nGame Development Internship:\nA Game Development Internship is a creative and technical position focused on contributing to the production of video games across various platforms (PC, console, or mobile).\nInterns are integrated into a multi-disciplinary team, working directly on a live project under the guidance of senior designers and engineers.\nKey responsibilities include implementing gameplay features using languages like C++ or C#, scripting game logic, building and iterating on levels, and debugging systems within industry-standard engines such as Unity or Unreal Engine.\nThe experience is vital for mastering the complete game development pipeline, gaining proficiency in version control (Git/Perforce), collaborating effectively with artists and designers, and translating abstract game design concepts into polished, functional, and engaging player experiences.";
                            break;
                        case 5:
                            internship = "\nData Science Internship:\nA Data Science Internship offers a practical, business-focused experience where the intern applies statistical methods and computational techniques to extract actionable insights from large, complex datasets.\nThe intern's primary goal is to support the data science team by contributing to the entire analytical lifecycle, from data collection and cleaning to model deployment.\nCore responsibilities involve using tools like Python (with libraries such as Pandas and scikit-learn) or R to perform Exploratory Data Analysis (EDA), developing predictive machine learning models, and utilizing SQL for data querying.\nA critical component is communicating complex findings through clear data visualizations (e.g., Tableau or Power BI) and comprehensive reports, enabling non-technical stakeholders to make data-driven decisions that impact the company's products or strategy.";
                            break;
                        case 6:
                            internship = "\nCloud Computing Internship:\nA Cloud Computing Internship is a hands-on technical role that provides experience with the architecture, deployment, management, and automation of services on leading cloud platforms like AWS, Microsoft Azure, or Google Cloud Platform (GCP)\\n" + 
                                                                "Interns primarily focus on helping a company leverage the scale, security, and flexibility of these technologies to solve real-world business challenges\\n" + 
                                                                "Core responsibilities involve assisting with the provisioning and maintenance of cloud resources such as Virtual Machines, storage, and networking components\\n" + 
                                                                "The intern is often tasked with writing and refining Python, Bash, or PowerShell scripts to automate repetitive operational tasks, a practice central to DevOps and Infrastructure as Code (IaC)\\n" + 
                                                                "Additionally, you'll support the deployment and monitoring of cloud-native applications, often involving tools like Docker and Kubernetes for containerization, and assist in cloud migration or optimization projects\\n" + 
                                                                "This role is crucial for developing practical skills in cloud security (IAM), troubleshooting, and performance monitoring, all while collaborating with experienced cloud engineers.";
                        break;
                        case 7:
                            internship = "\nEmbedded Systems Internship:\nAn Embedded Systems Internship is a highly technical role that bridges the gap between software programming and electrical engineering, focusing on the development of specialized computing systems found in devices like IoT sensors, medical equipment, and automotive control units\\n" + 
                                                                "Interns primarily focus on writing, testing, and debugging firmware for microcontrollers and microprocessors, often using the C/C++ programming languages to ensure the code is efficient and works within strict memory and performance limits\\n" + 
                                                                "Key responsibilities include assisting with hardware-software integration, which involves configuring device drivers and working with low-level hardware interfaces and communication protocols like I2C, SPI, and UART\\n" + 
                                                                "You will gain hands-on experience using specialized debugging tools such as oscilloscopes and logic analyzers, and may work with a Real-Time Operating System (RTOS) to manage time-critical system tasks\\n" + 
                                                                "This internship provides invaluable skills in real-time programming, data sheet analysis, and low-level hardware interaction, which are essential for a career in embedded software or hardware design.";
                        break;
                    }
                    case FOURTH_YEAR://year 4
                        max = 10;
                        randomInt = ThreadLocalRandom.current().nextInt(min,max+1);
                        switch(randomInt){
                        case 1:
                          internship = "\nSoftware Engineer Internship:\nA Software Engineer Internship is a critical, hands-on role where you apply fundamental Computer Science principles to the development, maintenance, and testing of production-level software applications.\nInterns are integrated into an Agile or Scrum team and contribute directly to the codebase, often working on a specific feature, component, or bug fix under the mentorship of a Senior Engineer Core responsibilities include writing clean, scalable, and well-documented code using languages like Python, Java, JavaScript, or C++, and actively participating in code reviews to maintain quality and learn best practices.\nYou will be responsible for utilizing Git or similar version control systems to manage changes, performing comprehensive unit and integration testing, and leveraging your knowledge of algorithms and data structures to solve real-world problems.\nThis experience is essential for developing proficiency in the full Software Development Life Cycle (SDLC), working with CI/CD pipelines, and gaining exposure to areas like cloud services (AWS/Azure/GCP) and database management (SQL/NoSQL).";  
                        break;
                        case 2:
                            internship = "\nBackend Developer Internship:\nA Backend Developer Internship is a specialized role focused on building, maintaining, and scaling the server-side logic and infrastructure that powers web and mobile applications.\nInterns are typically responsible for writing robust, high-performance code in languages like Python, Java, Node.js, or Go to implement core business logic and create RESTful or GraphQL APIs.\nA major component of the job involves working extensively with databases, including designing schemas, writing optimized queries in SQL (e.g., PostgreSQL, MySQL), or managing NoSQL stores (e.g., MongoDB).\nCore responsibilities also include integrating third-party services, ensuring application security, and optimizing the application for speed and scalability.\nInterns gain practical experience with cloud platforms (AWS, Azure, GCP), version control with Git, and containerization tools like Docker as they work within an Agile/Scrum team structure.\nThis role is crucial for developing expertise in system architecture, performance tuning, and the reliable delivery of data to the frontend.";
                            break;
                        case 3:
                            internship = "\nFrontend Developer Internship:\nA Frontend Developer Internship is a creative and technical role focused on building the user-facing side of websites and applications, ensuring they are responsive, accessible, and highly engaging.\nInterns focus on implementing visual and interactive elements using the core triad of web development: HTML, CSS, and JavaScript. They are expected to gain proficiency with modern frontend frameworks and libraries like React, Angular, or Vue.js to manage component-based architecture and application state. Core responsibilities include translating UI/UX designs (often from Figma or Sketch) into clean, semantic code, participating in cross-browser compatibility testing, and ensuring optimal page load performance.\nThis role is essential for developing expertise in responsive design, client-side performance optimization, state management, and effective collaboration with both backend engineers and design teams.";
                            break;
                        case 4:
                            internship = "\nFull Stack Developer Internship:\nA Full Stack Developer Internship is a comprehensive technical role that provides experience across the entire spectrum of web application development, from the user interface to the core server logic.\nInterns work on both the frontend, utilizing technologies like React, Angular, or Vue.js along with HTML, CSS, and JavaScript, and the backend, using server-side languages such as Python, Node.js, or Java. Key responsibilities include designing and developing new features end-to-end, which involves creating and managing APIs, designing and interacting with SQL or NoSQL databases, and building responsive user interfaces.\nThis role is crucial for gaining broad experience in the Software Development Life Cycle (SDLC), understanding how the frontend and backend interact, and becoming proficient in deployment tools, cloud services, and version control systems like Git.";
                            break;
                        case 5:
                            internship = "\nDevOps Engineer Internship:\nA DevOps Engineer Internship is a highly collaborative role focused on streamlining the software development lifecycle from coding and deployment to maintenance and operations.\nInterns gain practical experience with automation—the core principle of DevOps—by writing scripts using languages like Python, Bash, or Groovy to automate repetitive tasks and build processes.\nKey responsibilities include assisting with the setup and maintenance of Continuous Integration/Continuous Deployment (CI/CD) pipelines using tools such as Jenkins, GitLab CI, or GitHub Actions.\nYou will also work with Infrastructure as Code (IaC) tools like Terraform or Ansible to provision and manage cloud resources (e.g., AWS, Azure, or GCP), and gain essential experience with containerization technologies, primarily Docker and Kubernetes, for packaging and deploying applications.\nThis internship is crucial for developing skills in system monitoring, log management, and ensuring the reliability and scalability of production environments.";
                            break;
                        case 6:
                            internship = "\nMachine Learning Engineer Internship\nA Machine Learning (ML) Engineer Internship is a specialized role focused on applying scientific methods and programming skills to build, train, and deploy intelligent models into production systems.\nInterns primarily work with data pipelines, using languages like Python and its specialized libraries (TensorFlow, PyTorch, Scikit-learn) to clean, process, and engineer features from large datasets.\nKey responsibilities include designing experiments, training and evaluating various ML models (e.g., deep learning, classic algorithms), and optimizing model performance, efficiency, and scalability.\nThis role is crucial for gaining experience with the entire ML lifecycle (MLOps), including versioning models, deploying them via APIs, and setting up monitoring systems to ensure continuous performance and drift detection in a production environment.";
                        break;
                        case 7:
                            internship = "\nDatabase Administrator Internship\nA Database Administrator (DBA) Internship is a practical role focused on the installation, configuration, maintenance, and performance of a company's database systems, such as MySQL, PostgreSQL, Oracle, or SQL Server.\nInterns assist with core operational tasks, including monitoring database health, ensuring data integrity, and conducting regular backups and recovery tests to guarantee business continuity.\nKey responsibilities often involve writing and optimizing complex SQL queries and stored procedures to improve application performance, managing user access and permissions for security, and participating in database patching or migration projects.\nThis internship is essential for developing expertise in data modeling, performance tuning, security protocols, and managing the lifecycle of enterprise data.";
                        break;
                        case 8:
                            internship = "\nSystems Analyst Internship\nA Systems Analyst Internship is a critical, hybrid role that bridges the gap between a business's operational needs and its technological capabilities, focusing on analyzing, designing, and improving organizational systems.\nInterns primarily focus on requirements gathering by interviewing stakeholders, documenting current processes, and identifying opportunities for system enhancements or replacements.\nKey responsibilities include creating detailed functional and technical specifications, modeling business processes (often using tools like flowcharts or UML diagrams), and assisting with the testing and implementation of new software solutions.\nThis role is essential for developing expertise in business process analysis, translating non-technical needs into technical requirements, and ensuring that technology solutions effectively solve organizational problems and drive strategic value.";
                        break;
                        case 9:
                            internship = "\nCloud Infrastructure Internship\nA Cloud Infrastructure Internship is a specialized technical role focused on the design, deployment, configuration, and management of the fundamental compute, networking, and storage services on major cloud providers like AWS, Microsoft Azure, or Google Cloud Platform (GCP).\nInterns work directly with the foundational building blocks of the cloud, focusing on the stability and scalability of the environment.\nKey responsibilities include deploying and managing virtual machines (EC2 instances, etc.), configuring virtual networks and subnets, and managing scalable storage solutions (S3, Azure Blob Storage).\nYou will gain critical experience with Infrastructure as Code (IaC) tools, such as Terraform or CloudFormation, to automate resource provisioning, and assist with monitoring system health, managing access controls (IAM), and implementing basic security best practices.\nThis internship is essential for developing expertise in cloud architecture principles, core networking concepts, and robust automation practices necessary for maintaining large-scale cloud environments.";
                            break;
                        case 10:
                            internship = "\nAI/ML Research Internship\nA AI/ML Research Internship is a highly analytical and technical role focused on exploring cutting-edge theories and developing novel models to solve complex, open-ended problems, often with the goal of publication or product innovation.\nInterns work under the guidance of research scientists to investigate new machine learning algorithms or techniques, particularly in areas like Deep Learning, Natural Language Processing (NLP), or Computer Vision. Key responsibilities involve conducting extensive literature reviews, designing and performing controlled experiments, manipulating massive datasets, and rigorously evaluating the performance of models.\nThis role is crucial for developing expertise in mathematical modeling, statistical rigor, data analysis (often using Python and Jupyter notebooks), and communicating complex findings through comprehensive reports and presentations.";
                        break;
                        default:
                            System.err.println("Major error 354");
                        }
                    break;//final breakout for CS next line is ME
	        }
	    break;

	   case "ME":
	        min = 1;
	        max = 1;
	        switch(year){
	            case FIRST_YEAR://year 1
	                max = 1;
	                randomInt = ThreadLocalRandom.current().nextInt(min,max+1);
	                switch(randomInt){
	                    case 1:
	                        internship = "\nIntroduction to Engineering Workshop Internship:\nThis internship introduces first-year mechanical engineering students to the fundamentals of hands-on engineering practice. Interns work in supervised workshop environments, gaining exposure to basic manufacturing processes such as machining, welding, and 3D printing. The program emphasizes understanding mechanical components, interpreting engineering drawings, and adhering to safety protocols. By the end, interns develop practical skills in mechanical assembly, teamwork, and technical communication.";
	                        break;
	                }    
	                break;            

	            case SECOND_YEAR://year 2
	                max = 2;
	                randomInt = ThreadLocalRandom.current().nextInt(min,max+1);
	                switch(randomInt){
	                    case 1:
	                        internship = "\nCAD Design Internship:\nThis internship focuses on Computer-Aided Design (CAD) and modeling of mechanical systems. Interns work with professional software such as SolidWorks, AutoCAD, or CATIA to design mechanical parts and assemblies, perform basic stress analyses, and generate engineering documentation. Emphasis is placed on manufacturability, tolerance analysis, and collaboration with design teams.";
	                        break;
	                    case 2:
	                        internship = "\nManufacturing Process Internship:\nInterns participate in a manufacturing environment, gaining first-hand exposure to production workflows, machining, material handling, and process optimization. They learn about lean manufacturing principles, quality control, and the role of automation in modern production lines. The experience builds an understanding of how design translates into scalable manufacturing.";
	                        break;
	                }    
	                break;

	            case THIRD_YEAR://year 3
	                max = 7;
	                randomInt = ThreadLocalRandom.current().nextInt(min,max+1);
	                switch(randomInt){
	                    case 1:
	                        internship = "\nThermal Systems Internship:\nInterns assist in analyzing and testing heating, ventilation, and cooling systems, focusing on thermodynamic efficiency and heat transfer. Tasks may include data collection from sensors, energy audits, and simulation of systems using MATLAB or ANSYS Fluent.";
	                        break;
	                    case 2:
	                        internship = "\nAutomotive Engineering Internship:\nThis internship provides hands-on experience in the design, analysis, and testing of vehicle systems. Interns may contribute to projects involving powertrains, suspension systems, or lightweight materials. Skills in CAD, finite element analysis (FEA), and prototype testing are commonly developed.";
	                        break;
	                    case 3:
	                        internship = "\nRobotics and Mechatronics Internship:\nInterns collaborate with multidisciplinary teams to design mechanical subsystems for robotic applications. They engage in actuator selection, control integration, and motion analysis while working with microcontrollers like Arduino or Raspberry Pi.";
	                        break;
	                    case 4:
	                        internship = "\nRenewable Energy Systems Internship:\nThis internship focuses on the design and performance analysis of wind, solar, or hydro energy systems. Interns model mechanical components such as turbines and gear systems, assessing efficiency and sustainability under different environmental conditions.";
	                        break;
	                    case 5:
	                        internship = "\nHVAC Design Internship:\nInterns assist with the design and optimization of heating, ventilation, and air conditioning systems for residential or industrial settings. Responsibilities include drafting duct layouts, load calculations, and supporting engineers in energy efficiency assessments.";
	                        break;
	                    case 6:
	                        internship = "\nMaterials Testing Internship:\nThis internship focuses on understanding the mechanical properties of materials through laboratory testing, including tensile, fatigue, and impact tests. Interns gain familiarity with materials characterization techniques and data interpretation.";
	                        break;
	                    case 7:
	                        internship = "\nFinite Element Analysis (FEA) Internship:\nInterns work with simulation tools such as ANSYS or Abaqus to perform stress and deformation analyses on mechanical structures. They learn meshing techniques, boundary condition setup, and result interpretation for design validation.";
	                        break;
	                }    
	                break;

	            case FOURTH_YEAR://year 4
	                max = 10;
	                randomInt = ThreadLocalRandom.current().nextInt(min,max+1);
	                switch(randomInt){
	                    case 1:
	                        internship = "\nProduct Design Engineer Internship:\nA senior-level internship where students contribute to the development and optimization of mechanical products from concept to prototype. They participate in design reviews, FEA analysis, material selection, and manufacturability assessments.";
	                        break;
	                    case 2:
	                        internship = "\nAerospace Systems Internship:\nInterns collaborate on the design and testing of mechanical systems for aircraft or spacecraft. Work involves stress analysis, component modeling, and thermal simulation under aerospace standards.";
	                        break;
	                    case 3:
	                        internship = "\nAutomotive Powertrain Internship:\nThis internship provides deep exposure to engine mechanics, hybrid systems, and drivetrain optimization. Interns may assist in component testing, performance tuning, or emissions analysis.";
	                        break;
	                    case 4:
	                        internship = "\nManufacturing Engineering Internship:\nInterns work on process improvement projects, applying Six Sigma and Lean principles to reduce waste and improve throughput. Exposure includes CNC programming, automation, and quality assurance systems.";
	                        break;
	                    case 5:
	                        internship = "\nMechanical R&D Internship:\nInterns assist in experimental testing and design innovation within a research and development lab. They contribute to prototype fabrication, performance evaluation, and technical reporting.";
	                        break;
	                    case 6:
	                        internship = "\nBiomechanical Systems Internship:\nA cross-disciplinary internship focused on mechanical design for biomedical devices such as prosthetics, implants, or surgical tools. Interns apply FEA, materials science, and ergonomics principles to enhance human-device interaction.";
	                        break;
	                    case 7:
	                        internship = "\nRenewable Energy Engineer Internship:\nInterns analyze and optimize renewable systems, focusing on wind turbine or solar tracker mechanics. They use simulation tools to model load conditions, efficiency, and environmental impacts.";
	                        break;
	                    case 8:
	                        internship = "\nIndustrial Automation Internship:\nThis internship focuses on integrating robotics and automated machinery into manufacturing lines. Interns learn PLC programming, system calibration, and mechatronic system troubleshooting.";
	                        break;
	                    case 9:
	                        internship = "\nAerospace Propulsion Internship:\nInterns assist with the analysis and testing of propulsion components such as compressors, turbines, and nozzles. The role includes thermodynamic modeling, vibration testing, and CFD analysis.";
	                        break;
	                    case 10:
	                        internship = "\nMechanical Design Optimization Internship:\nInterns perform performance and cost optimization studies using CAD and CAE tools. They refine existing designs for efficiency, manufacturability, and environmental sustainability.";
	                        break;
	                }    
	                break;
	        }
	        break;
	    case "CE":
	        min = 1;
	        max = 1;
	        switch(year){
	            case FIRST_YEAR://year 1
	                max = 1;
	                randomInt = ThreadLocalRandom.current().nextInt(min,max+1);    
	                switch(randomInt){
	                    case 1:
	                        internship = "\nIntro to Civil Design Internship:\nAn introductory internship where students observe basic drafting, surveying, and construction processes. Interns learn how design plans are created and applied in small-scale projects, gaining exposure to CAD and site safety fundamentals.";
	                        break;
	                }               
	                break;
	            case SECOND_YEAR://year 2
	                max = 2;
	                randomInt = ThreadLocalRandom.current().nextInt(min,max+1);
	                switch(randomInt){
	                    case 1:
	                        internship = "\nStructural Drafting Internship:\nInterns assist in creating and reviewing civil and structural drawings using AutoCAD or Revit. They learn to interpret blueprints, understand materials, and apply design standards under engineer supervision.";
	                        break;
	                    case 2:
	                        internship = "\nSurveying Internship:\nInterns gain field experience in land measurement and mapping. They use surveying equipment, collect data, and learn how topographic information supports infrastructure design.";
	                        break;
	                }    
	                break;
	            case THIRD_YEAR://year 3
	                max = 7;
	                randomInt = ThreadLocalRandom.current().nextInt(min,max+1);
	                switch(randomInt){
	                    case 1:
	                        internship = "\nConstruction Management Internship:\nAssist site engineers in planning, scheduling, and quality control. Interns gain insight into cost estimation, materials management, and construction documentation.";
	                        break;
	                    case 2:
	                        internship = "\nTransportation Engineering Internship:\nInterns support projects focused on road design, traffic flow, and transportation planning. They analyze roadway data and help optimize infrastructure safety and efficiency.";
	                        break;
	                    case 3:
	                        internship = "\nEnvironmental Engineering Internship:\nWork on environmental impact assessments and water treatment systems. Interns learn sustainability practices and regulatory compliance procedures.";
	                        break;
	                    case 4:
	                        internship = "\nGeotechnical Internship:\nInterns assist in soil testing, foundation analysis, and site evaluations. They interpret lab results and understand how soil behavior affects structural design.";
	                        break;
	                    case 5:
	                        internship = "\nStructural Engineering Internship:\nSupport engineers in designing and analyzing building structures. Interns perform load calculations and help prepare design documents for construction approval.";
	                        break;
	                    case 6:
	                        internship = "\nWater Resources Internship:\nInterns work with hydraulic models and water flow simulations. They learn to design pipelines, drainage systems, and flood control measures.";
	                        break;
	                    case 7:
	                        internship = "\nUrban Planning Internship:\nParticipate in city development projects and zoning research. Interns assist in mapping, public infrastructure analysis, and sustainable city planning.";
	                        break;
	                }    
	                break;
	            case FOURTH_YEAR://year 4
	                max = 10;
	                randomInt = ThreadLocalRandom.current().nextInt(min,max+1);
	                switch(randomInt){
	                    case 1:
	                        internship = "\nStructural Design Internship:\nInterns design reinforced concrete and steel structures using software like STAAD Pro or ETABS. They apply real-world load and safety design principles.";
	                        break;
	                    case 2:
	                        internship = "\nTransportation Systems Internship:\nWork with government or consulting agencies on traffic and infrastructure modeling. Interns learn data analysis and modern transport design methods.";
	                        break;
	                    case 3:
	                        internship = "\nBridge Engineering Internship:\nAssist in bridge design, inspection, and maintenance projects. Interns gain understanding of load distribution and safety codes.";
	                        break;
	                    case 4:
	                        internship = "\nHydraulic Engineering Internship:\nInterns support river flow modeling, flood prediction, and dam structure analysis. They apply fluid mechanics to real-world civil systems.";
	                        break;
	                    case 5:
	                        internship = "\nConstruction Project Internship:\nEngage in on-site supervision, resource allocation, and progress tracking. Interns develop project management and teamwork skills.";
	                        break;
	                    case 6:
	                        internship = "\nUrban Infrastructure Internship:\nWork on city planning projects, assisting in road, drainage, and utility layout design. Interns apply sustainable engineering concepts.";
	                        break;
	                    case 7:
	                        internship = "\nGeotechnical Research Internship:\nPerform advanced soil and rock stability studies. Interns help prepare reports for foundation and slope design recommendations.";
	                        break;
	                    case 8:
	                        internship = "\nStructural Simulation Internship:\nUse finite element software to simulate load behavior in buildings and bridges. Learn optimization techniques and safety compliance.";
	                        break;
	                    case 9:
	                        internship = "\nEnvironmental Impact Internship:\nAssist in creating environmental assessments for construction projects. Learn data analysis and sustainability evaluation.";
	                        break;
	                    case 10:
	                        internship = "\nInfrastructure Design Internship:\nParticipate in multi-disciplinary projects combining civil, structural, and environmental design. Apply CAD and project coordination skills.";
	                        break;
	                }    
	                break;
	        }
	        break;

	    case "BE":
	        min = 1;
	        max = 1;
	        switch(year){
	            case FIRST_YEAR://year 1
	                max = 1;
	                randomInt = ThreadLocalRandom.current().nextInt(min,max+1);    
	                switch(randomInt){
	                    case 1:
	                        internship = "\nIntro to Biomedical Lab Internship:\nThis entry-level internship introduces students to basic lab techniques, biosafety, and documentation. Interns assist in assembling lab equipment and learning about biomedical devices and their applications.";
	                        break;
	                }               
	                break;
	            case SECOND_YEAR://year 2
	                max = 2;
	                randomInt = ThreadLocalRandom.current().nextInt(min,max+1);
	                switch(randomInt){
	                    case 1:
	                        internship = "\nMedical Device Design Internship:\nInterns assist in creating 3D models and prototypes of simple medical devices using CAD tools. They learn about biocompatibility, materials, and basic FDA regulatory requirements.";
	                        break;
	                    case 2:
	                        internship = "\nBiomedical Research Internship:\nInterns participate in experimental setups related to tissue engineering or biomaterials. They collect data, maintain lab records, and gain exposure to scientific research practices.";
	                        break;
	                }    
	                break;
	            case THIRD_YEAR://year 3
	                max = 7;
	                randomInt = ThreadLocalRandom.current().nextInt(min,max+1);
	                switch(randomInt){
	                    case 1:
	                        internship = "\nClinical Engineering Internship:\nInterns work in hospital engineering departments maintaining and calibrating medical equipment. They gain real-world understanding of device safety and hospital operations.";
	                        break;
	                    case 2:
	                        internship = "\nBiomechanics Internship:\nAssist engineers in analyzing motion, stress, and material behavior in biological systems. Interns use simulation software to study human movement or prosthetic design.";
	                        break;
	                    case 3:
	                        internship = "\nTissue Engineering Internship:\nInterns work with researchers culturing cells and developing tissue scaffolds. They learn sterile techniques and gain exposure to regenerative medicine principles.";
	                        break;
	                    case 4:
	                        internship = "\nBiomedical Data Analysis Internship:\nInterns analyze medical datasets to identify patterns related to health outcomes. They use statistical software and basic programming to support research conclusions.";
	                        break;
	                    case 5:
	                        internship = "\nBioinstrumentation Internship:\nAssist in designing and testing sensors for physiological signal monitoring. Interns gain practical experience with circuits, microcontrollers, and biosignal processing.";
	                        break;
	                    case 6:
	                        internship = "\nRehabilitation Engineering Internship:\nInterns help develop assistive and rehabilitation technologies such as prosthetics or mobility aids. They learn human-centered design and usability testing.";
	                        break;
	                    case 7:
	                        internship = "\nMedical Imaging Internship:\nInterns learn about imaging modalities such as MRI, CT, and ultrasound. They assist in calibration, image processing, and equipment evaluation projects.";
	                        break;
	                }    
	                break;
	            case FOURTH_YEAR://year 4
	                max = 10;
	                randomInt = ThreadLocalRandom.current().nextInt(min,max+1);
	                switch(randomInt){
	                    case 1:
	                        internship = "\nBiomedical Product Development Internship:\nInterns collaborate on advanced device design, testing, and validation processes. They learn about clinical trials, documentation, and commercialization steps.";
	                        break;
	                    case 2:
	                        internship = "\nRegulatory Affairs Internship:\nInterns assist in preparing FDA and ISO documentation for medical devices. They gain insight into compliance, safety standards, and product approval workflows.";
	                        break;
	                    case 3:
	                        internship = "\nNeural Engineering Internship:\nInterns work on research or development of brain-computer interfaces and neural sensors. They apply signal analysis and electronics principles to human-machine systems.";
	                        break;
	                    case 4:
	                        internship = "\nBiomaterials Internship:\nInterns test and characterize materials used in implants or tissue scaffolds. They study properties like strength, biocompatibility, and degradation behavior.";
	                        break;
	                    case 5:
	                        internship = "\nClinical Research Internship:\nAssist in managing and monitoring clinical trials. Interns work on data integrity, patient documentation, and ethical compliance for biomedical studies.";
	                        break;
	                    case 6:
	                        internship = "\nProsthetics and Orthotics Internship:\nInterns contribute to the design and fitting of prosthetic limbs or orthotic devices. They learn anatomy, ergonomics, and customization techniques for patient comfort.";
	                        break;
	                    case 7:
	                        internship = "\nBiomedical Software Internship:\nWork on software systems for data collection or medical imaging analysis. Interns learn programming basics for healthcare technology solutions.";
	                        break;
	                    case 8:
	                        internship = "\nMedical Robotics Internship:\nAssist in developing robotic surgical or rehabilitation systems. Interns learn about mechanical design, control algorithms, and system integration.";
	                        break;
	                    case 9:
	                        internship = "\nPharmaceutical Engineering Internship:\nInterns help optimize drug delivery systems and manufacturing processes. They apply chemical and mechanical concepts to biomedical product design.";
	                        break;
	                    case 10:
	                        internship = "\nBiomedical Innovation Internship:\nInterns collaborate with multidisciplinary teams to develop new healthcare solutions. They engage in concept prototyping, testing, and innovation documentation.";
	                        break;
	                }    
	                break;
	        }
	        break;

	    case "AE":
	        min = 1;
	        max = 1;
	        switch(year){
	            case FIRST_YEAR://year 1
	                max = 1;
	                randomInt = ThreadLocalRandom.current().nextInt(min,max+1);    
	                switch(randomInt){
	                    case 1:
	                        internship = "\nIntro to Aerospace Systems Internship:\nThis beginner-level internship introduces students to fundamental aerospace concepts, including aircraft structure and propulsion basics. Interns assist in lab experiments, learning about aerodynamics and safety in aerospace environments.";
	                        break;
	                }               
	                break;
	            case SECOND_YEAR://year 2
	                max = 2;
	                randomInt = ThreadLocalRandom.current().nextInt(min,max+1);
	                switch(randomInt){
	                    case 1:
	                        internship = "\nAerospace Design Internship:\nInterns help create CAD models of aircraft components using SolidWorks or CATIA. They gain hands-on experience in design documentation, tolerance analysis, and prototype evaluation.";
	                        break;
	                    case 2:
	                        internship = "\nWind Tunnel Testing Internship:\nAssist in experimental testing of aerodynamic models within wind tunnels. Interns collect airflow and pressure data to understand lift, drag, and design optimization.";
	                        break;
	                }    
	                break;
	            case THIRD_YEAR://year 3
	                max = 7;
	                randomInt = ThreadLocalRandom.current().nextInt(min,max+1);
	                switch(randomInt){
	                    case 1:
	                        internship = "\nFlight Systems Internship:\nInterns work on control systems, navigation sensors, and avionics testing. They gain experience in system integration and flight data analysis.";
	                        break;
	                    case 2:
	                        internship = "\nPropulsion Engineering Internship:\nAssist in evaluating engine performance and combustion systems. Interns learn thermodynamic analysis and simulation techniques for propulsion systems.";
	                        break;
	                    case 3:
	                        internship = "\nAerodynamics Internship:\nInterns perform computational simulations using CFD tools like ANSYS Fluent. They analyze airflows over wings and optimize aerodynamic efficiency.";
	                        break;
	                    case 4:
	                        internship = "\nSpace Systems Internship:\nParticipate in spacecraft subsystem design or orbital analysis. Interns gain exposure to mission planning and satellite component development.";
	                        break;
	                    case 5:
	                        internship = "\nStructures and Materials Internship:\nInterns assist in testing lightweight materials and analyzing stress behavior in aerospace components. They learn to apply finite element analysis in design validation.";
	                        break;
	                    case 6:
	                        internship = "\nAvionics Internship:\nWork on electronic and control systems in aircraft or drones. Interns gain hands-on experience with sensors, wiring, and system calibration.";
	                        break;
	                    case 7:
	                        internship = "\nUnmanned Aerial Vehicle (UAV) Internship:\nInterns assist in the design, assembly, and testing of UAV platforms. They contribute to flight control tuning, sensor integration, and prototype development.";
	                        break;
	                }    
	                break;
	            case FOURTH_YEAR://year 4
	                max = 10;
	                randomInt = ThreadLocalRandom.current().nextInt(min,max+1);
	                switch(randomInt){
	                    case 1:
	                        internship = "\nAerospace Design Engineering Internship:\nInterns participate in detailed aircraft or spacecraft component design. They apply simulation tools and engineering standards to optimize performance and safety.";
	                        break;
	                    case 2:
	                        internship = "\nFlight Testing Internship:\nAssist in pre-flight inspections, data acquisition, and post-flight performance analysis. Interns learn testing protocols and instrumentation calibration.";
	                        break;
	                    case 3:
	                        internship = "\nRocket Propulsion Internship:\nInterns support rocket motor testing and analysis of thrust and fuel efficiency. They gain insight into combustion science and propulsion mechanics.";
	                        break;
	                    case 4:
	                        internship = "\nSpace Mission Design Internship:\nWork with teams planning mission trajectories and satellite payload integration. Interns use orbital mechanics tools to assist in mission simulations.";
	                        break;
	                    case 5:
	                        internship = "\nAerospace Simulation Internship:\nInterns perform dynamic modeling of flight systems using MATLAB or Simulink. They learn how control laws and physics models affect performance stability.";
	                        break;
	                    case 6:
	                        internship = "\nAircraft Maintenance Engineering Internship:\nAssist maintenance teams in inspecting and troubleshooting aircraft systems. Interns learn aviation regulations, documentation, and repair procedures.";
	                        break;
	                    case 7:
	                        internship = "\nThermal Control Systems Internship:\nInterns study heat management in spacecraft and propulsion systems. They analyze cooling mechanisms and material insulation properties.";
	                        break;
	                    case 8:
	                        internship = "\nAerospace Manufacturing Internship:\nWork with composite materials and automated production tools. Interns learn quality assurance, CNC machining, and process optimization.";
	                        break;
	                    case 9:
	                        internship = "\nSpace Robotics Internship:\nInterns assist in the design and testing of robotic arms or autonomous systems for space applications. They explore motion control and reliability in extreme conditions.";
	                        break;
	                    case 10:
	                        internship = "\nAerospace Systems Integration Internship:\nInterns collaborate across subsystems, ensuring compatibility between structures, electronics, and propulsion. They develop an understanding of system-level engineering practices.";
	                        break;
	                }    
	                break;
	        }
	        break;

	    case "EE":
	        min = 1;
	        max = 1;
	        switch(year){
	            case FIRST_YEAR://year 1
	                max = 1;
	                randomInt = ThreadLocalRandom.current().nextInt(min,max+1);    
	                switch(randomInt){
	                    case 1:
	                        internship = "\nIntro to Circuits Internship:\nAn introductory internship focused on learning basic electrical components and circuits. Interns assist with simple breadboard projects, testing voltage, and understanding Ohm’s law in practice.";
	                        break;
	                }               
	                break;
	            case SECOND_YEAR://year 2
	                max = 2;
	                randomInt = ThreadLocalRandom.current().nextInt(min,max+1);
	                switch(randomInt){
	                    case 1:
	                        internship = "\nEmbedded Systems Internship:\nInterns work with microcontrollers such as Arduino or Raspberry Pi to design basic embedded applications. They learn coding in C/C++, wiring, and debugging hardware interfaces.";
	                        break;
	                    case 2:
	                        internship = "\nControl Systems Internship:\nAssist in developing simple automation and feedback systems. Interns use sensors and actuators to understand system response and control theory concepts.";
	                        break;
	                }    
	                break;
	            case THIRD_YEAR://year 3
	                max = 7;
	                randomInt = ThreadLocalRandom.current().nextInt(min,max+1);
	                switch(randomInt){
	                    case 1:
	                        internship = "\nPower Systems Internship:\nInterns support the design and maintenance of power distribution networks. They learn about transformers, load flow, and electrical safety protocols.";
	                        break;
	                    case 2:
	                        internship = "\nCommunication Systems Internship:\nAssist in testing antennas, wireless modules, or fiber optic systems. Interns analyze signal transmission and reception performance.";
	                        break;
	                    case 3:
	                        internship = "\nElectronics Design Internship:\nInterns design and simulate circuit boards using software like Multisim or Altium. They assemble prototypes and test signal integrity.";
	                        break;
	                    case 4:
	                        internship = "\nRenewable Energy Internship:\nWork on projects involving solar panels, inverters, and power converters. Interns learn efficiency optimization and sustainable energy integration.";
	                        break;
	                    case 5:
	                        internship = "\nInstrumentation Internship:\nInterns assist in setting up sensors, transducers, and data acquisition systems. They collect and analyze measurement data from industrial equipment.";
	                        break;
	                    case 6:
	                        internship = "\nSignal Processing Internship:\nApply digital filtering and signal analysis using MATLAB or Python. Interns learn techniques for improving sound or image data quality.";
	                        break;
	                    case 7:
	                        internship = "\nAutomation Internship:\nInterns help program PLCs and test control loops in industrial environments. They gain hands-on experience with sensors, actuators, and logic design.";
	                        break;
	                }    
	                break;
	            case FOURTH_YEAR://year 4
	                max = 10;
	                randomInt = ThreadLocalRandom.current().nextInt(min,max+1);
	                switch(randomInt){
	                    case 1:
	                        internship = "\nCircuit Design Internship:\nInterns design and test advanced analog and digital circuits. They gain exposure to PCB layout, signal conditioning, and circuit troubleshooting.";
	                        break;
	                    case 2:
	                        internship = "\nEmbedded Hardware Internship:\nAssist in developing firmware and hardware integration for IoT or embedded products. Interns work with microprocessors, sensors, and real-time debugging tools.";
	                        break;
	                    case 3:
	                        internship = "\nPower Electronics Internship:\nInterns study converters, rectifiers, and power supply design. They contribute to projects improving energy efficiency and stability in electrical systems.";
	                        break;
	                    case 4:
	                        internship = "\nTelecommunications Internship:\nSupport communication network configuration and testing. Interns work with transmission systems, modulation techniques, and network diagnostics.";
	                        break;
	                    case 5:
	                        internship = "\nRobotics and Mechatronics Internship:\nInterns design and wire robotic control systems. They apply sensor feedback and circuit interfacing to develop automated solutions.";
	                        break;
	                    case 6:
	                        internship = "\nRenewable Power Systems Internship:\nAssist in the design and monitoring of solar or wind power systems. Interns learn about grid-tie inverters, power electronics, and data acquisition.";
	                        break;
	                    case 7:
	                        internship = "\nVLSI Design Internship:\nInterns learn chip design fundamentals using HDL languages. They simulate logic circuits and understand semiconductor manufacturing basics.";
	                        break;
	                    case 8:
	                        internship = "\nAutomation and Controls Internship:\nAssist in designing industrial automation systems using PLCs and SCADA. Interns integrate control loops for optimized performance.";
	                        break;
	                    case 9:
	                        internship = "\nSmart Grid Internship:\nInterns participate in developing intelligent power distribution systems. They explore data-driven energy management and IoT integration for grids.";
	                        break;
	                    case 10:
	                        internship = "\nResearch in Electrical Systems Internship:\nWork with academic or industrial researchers on advanced electrical or renewable projects. Interns focus on innovation and data-driven experimentation.";
	                        break;
	                }    
	                break;
	        }
	        break;
        case "BIO":
    min = 1;
    max = 1;
    switch(year){
        case FIRST_YEAR://year 1
            max = 1;
            randomInt = ThreadLocalRandom.current().nextInt(min,max+1);    
            switch(randomInt){
                case 1:
                    internship = "\nLaboratory Assistant Internship:\nAn introductory lab internship focusing on basic biology techniques. Interns assist with sample labeling, microscope use, and maintaining clean lab conditions while learning safety protocols.";
                    break;
            }                
            break;
        case SECOND_YEAR://year 2
            max = 2;
            randomInt = ThreadLocalRandom.current().nextInt(min,max+1);
            switch(randomInt){
                case 1:
                    internship = "\nMicrobiology Internship:\nInterns work with bacterial cultures and learn aseptic techniques, media preparation, and microbial identification. They gain experience in data collection and sterile lab practices.";
                    break;
                case 2:
                    internship = "\nEcology Internship:\nAssist in field research involving plant and animal population studies. Interns collect environmental data, perform habitat assessments, and support ecological data analysis.";
                    break;
            }    
            break;
        case THIRD_YEAR://year 3
            max = 7;
            randomInt = ThreadLocalRandom.current().nextInt(min,max+1);
            switch(randomInt){
                case 1:
                    internship = "\nGenetics Internship:\nInterns assist in DNA extraction, PCR, and gel electrophoresis. They gain exposure to genetic data analysis and learn how genes influence biological traits.";
                    break;
                case 2:
                    internship = "\nBiomedical Research Internship:\nWork alongside scientists in a medical or pharmaceutical lab. Interns help with tissue culture, drug efficacy tests, and experiment documentation.";
                    break;
                case 3:
                    internship = "\nMarine Biology Internship:\nAssist in studying aquatic life and ecosystems. Interns collect and analyze water samples and observe marine organisms in controlled or field environments.";
                    break;
                case 4:
                    internship = "\nEnvironmental Biology Internship:\nInterns monitor air, water, or soil quality in environmental protection projects. They contribute to conservation data reporting and sustainability efforts.";
                    break;
                case 5:
                    internship = "\nBotany Internship:\nAssist in plant identification, classification, and growth experiments. Interns learn about plant physiology, greenhouse management, and data collection techniques.";
                    break;
                case 6:
                    internship = "\nZoology Internship:\nInterns observe animal behavior and assist with care in research or conservation facilities. They participate in biological data collection and animal monitoring activities.";
                    break;
                case 7:
                    internship = "\nBiochemistry Internship:\nWork in labs conducting enzyme assays or protein analysis. Interns gain practical experience with spectrophotometry and other molecular biology tools.";
                    break;
            }    
            break;
        case FOURTH_YEAR://year 4
            max = 10;
            randomInt = ThreadLocalRandom.current().nextInt(min,max+1);
            switch(randomInt){
                case 1:
                    internship = "\nMolecular Biology Internship:\nInterns perform advanced lab work including cloning, sequencing, and gene expression studies. They contribute to ongoing research in cellular biology.";
                    break;
                case 2:
                    internship = "\nPharmaceutical Research Internship:\nAssist in drug discovery or testing phases under supervision. Interns document test results and gain experience in regulatory lab environments.";
                    break;
                case 3:
                    internship = "\nBioinformatics Internship:\nInterns analyze biological datasets using computational tools like Python or R. They work with genomic data to identify trends or mutations.";
                    break;
                case 4:
                    internship = "\nEnvironmental Health Internship:\nWork with public health or environmental agencies to monitor pollution and biological risk factors. Interns assist in data analysis and community reporting.";
                    break;
                case 5:
                    internship = "\nClinical Laboratory Internship:\nInterns rotate through diagnostic testing areas such as hematology or microbiology. They assist in specimen processing and result validation.";
                    break;
                case 6:
                    internship = "\nNeuroscience Internship:\nAssist with brain or nervous system research, including behavioral and physiological studies. Interns handle data collection and learn imaging or recording techniques.";
                    break;
                case 7:
                    internship = "\nBiotechnology Internship:\nInterns work with bio-manufacturing processes like fermentation or genetic modification. They help test prototypes and learn production-scale biology.";
                    break;
                case 8:
                    internship = "\nForensic Biology Internship:\nAssist forensic scientists in analyzing biological evidence such as DNA or blood samples. Interns learn about legal chain-of-custody and lab accuracy standards.";
                    break;
                case 9:
                    internship = "\nPublic Health Research Internship:\nInterns participate in epidemiological studies and data collection on health patterns. They help analyze biological data for disease prevention efforts.";
                    break;
                case 10:
                    internship = "\nResearch Assistant Internship:\nWork directly with faculty or scientists on independent research projects. Interns help design experiments, collect data, and prepare reports for publication.";
                    break;
            }    
            break;
    }
    break;
	    default:
			internship = "Internship positions for "+major+" are currently unavaliable";
	        break;
	}
        System.out.print(internship);
	    return internship;
	}
public enum UniYear {
	FIRST_YEAR,
    SECOND_YEAR,
    THIRD_YEAR,
    FOURTH_YEAR;
}
static UniYear convert(int choice){
    switch(choice){
        case 1:
            return UniYear.FIRST_YEAR;
        case 2:
            return UniYear.SECOND_YEAR;
        case 3:
            return UniYear.THIRD_YEAR;
        case 4:
            return UniYear.FOURTH_YEAR;
        default:
            throw new IllegalArgumentException("Invalid Year Choice, Please select a valid year!");
    }
}
}
