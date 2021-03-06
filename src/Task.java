import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;


/**
 * A class that describes a task
 */
public class Task
{
    private String project;
    private Status status;
    private String description;
    private Date date;
    private String name;
    private Display displayOption;
    private String method;


    /**
     * Constructor of the Task class
     * When a task is created, all of its attributes must be defined by the user
     */
    public Task()

{   date=new Date();
    Scanner sc =new Scanner(System.in);
    method="text";
    setDisplayOption(new DisplayText());
    displayOption.display("Give task a name:");
    name=sc.nextLine();

    displayOption.display("Which project does it belong to?");
    project = sc.nextLine();

    displayOption.display("Enter a task description\n");
    setDescription(new Scanner(System.in).next());

    setDate();

    status =Status.PENDING;


}

    public void setDisplayOption(Display displayMethod)
    {
        displayOption = displayMethod;
    }

    /**
     * Getter method for attribute project
     * @return the name of the project the task belongs to
     */
    public  String getProject()
    {
        return project;
    }

    /**
     * Sets the name of the project the task belongs to
     * @param project the name of the project that the task will be assigned to
     */
    public void setProject(String project)
    {
        this.project = project;
    }


    /**
     * Getter method for attribute date
     * @return the date of completion of the task
     */
    public Date getDate()
    {
        return date;
    }

    /**
     * Sets the date of completion of the task
     *
     */
    public void setDate()
    {
        displayOption.display("Due Date? (Please use \"dd/MM/yyyy hh:mm\" format)");
        Scanner sc =new Scanner(System.in);
        boolean success=false;
        do
        {
            try
            {
                date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(sc.nextLine());
                success=true;
                if(date.before(new Date()))
                {
                    displayOption.display("This day belongs to the past. Please enter a valid date:");
                    success=false;
                }




            } catch (ParseException e)
            {
                displayOption.display("Please enter the date in the requested format\n");



            }

        }
        while(!success);
    }

    /**
     *Checks if the task is completed
     * @return true if the task is completed, false if the task is not completed
     */
    public boolean isCompleted()
    {
        return status==Status.COMPLETED;
    }

    /**
     * Marks the task as completed
     */
    public void setCompleted()
    {
        status=Status.COMPLETED;
    }

    /**
     * Getter for the name attribute
     * @return the name of the task
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name of the task
     * @param name the name that task will take
     */
    public void setName(String name)
    {
        this.name = name;
    }


    public String getDescription()
    {
        return description;
    }


    public void setDescription(String description)
    {


        this.description = description;
    }




    /**
     * Checks if a task is expired
     * @return true if the task is expired, false if not
     */
    public boolean isExpired()
    {
        return status==Status.EXPIRED;
    }

    /**
     * Marks the task as expired
     */
    public void setExpired()
    {
        status=Status.EXPIRED;
    }

    /**
     * Checks if a task is expired and marks it as expired if so.
     */
    public void checkExpiration()
    {
        if(date.before(new Date()))
            setExpired();

    }


    /**
     * A comaprison method to be used for comparing dates of two tasks
     * @param b A task whose date will be compared to the date of the current task
     * @return -1 if date of current task is earlier than the date of the other task
     *         -2 if date of current task is later than the date of the other task
     *          0 if tasks have the same date
     */
    public int compareTo(Task b)
    {
            if(getDate().before(b.getDate()))
                return -1;
            else if(getDate().after(b.getDate()))
                return 1;
            else
                return 0;
    }

    public void editTask()
    {
        displayOption.display("What do you want to change?\n" +
        "(N) for Name\n" +
        "(T) for Time and Date\n" +
        "(D) for Description\n" +
        "(P) for Project\n" +
        "(R) to Return to the previous menu\n");
        Scanner scanner = new Scanner(System.in);
        String editChoice=null;
        String choices[]={"N","T","D","R","P"};


        while (!Arrays.asList(choices).contains(editChoice))
        {
            editChoice=scanner.next();

            switch(editChoice)
            {
                case "N":
                    displayOption.display("Enter the new name:");
                    name=scanner.next();
                    break;

                case "T":
                    setDate();
                    break;

                case "D":
                    displayOption.display("Enter new description");
                    setDescription(scanner.next());
                    break;

                case "P":
                    displayOption.display("Enter new project name");
                    setProject(scanner.next());
                    break;

                case "R":
                    return;

                default:
                    displayOption.display("Please enter a valid option\n");


            }
        }



    }
}