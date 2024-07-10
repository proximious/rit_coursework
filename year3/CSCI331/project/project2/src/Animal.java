/**
 * @author: Alex Iacob ai9388@rit.edu
 * @filename: Animal.java
 * <p>
 * Helper class for the animal conflict logic that holds an individual animal.
 */
public class Animal {
    private int number; // the number of the respective animal
    private String status; // the animal's status whether it is a hawk, dove, or dead
    private int resource = 0; // the animal's resource

    public Animal(int number, String status, int resource) {
        this.number = number;
        this.status = status;
        this.resource = resource;
    }

    public String getStatus() {
        return status;
    }

    public int getResource() {
        return resource;
    }

    public boolean checkDead() {
        return this.resource < 0;
    }

    public void changeStatusToDead() {
        this.status = "DEAD";
    }

    public void addResource(int resource) {
        this.resource += resource;
    }

    public void subtractResource(int resource) {
        this.resource -= resource;
        if (this.checkDead()) {
            this.status = "DEAD";
        }
    }

    public String getStatusString() {
        return "Individual " + this.number + " : " + this.status;
    }

    public String getResourceString() {
        return "Individual " + this.number + " = " + this.resource;
    }

    public String getDisplayString() {
        return "Individual [" + this.number + "] = " +
                this.status + " : " + this.resource;
    }

}
