package WorkoutAssistant;
import java.io.*;
import java.util.*;

public class WorkoutProgramManager {


    /******************************************************************
     * The default constructor
     */
    public WorkoutProgramManager() {
    }


    /******************************************************************
     * Creates and saves a new workout program
     * @param wpName The name of the workout program
     * @param wpDescription (Optional) The description of the workout
     * program
     * @param wpTargetMuscles (Optional) The muscles targeted in the
     * workout program
     * @throws IllegalArgumentException Thrown if "wpName" is blank
     */
    public void createWP(String wpName, String wpDescription,
    String wpTargetMuscles) throws IllegalArgumentException {
        HashMap<String, String> wp = new HashMap<String, String>();
        if (wpName.isBlank()) {
            throw new IllegalArgumentException();
        }
        wp.put("wpName", wpName);
        wp.put("wpDescription", wpDescription);
        wp.put("wpTargetMuscles", wpTargetMuscles);
        saveWP(wp);
    }


    /******************************************************************
     * Saves the specified workout program to wps.txt
     * @param wpToSave The workout program to be saved
     */
    public void saveWP(HashMap<String, String> wpToSave) {
        try {
            ArrayList savedWPs = loadSavedWPs();
            savedWPs.add(wpToSave);
            FileOutputStream fileOut =
                    new FileOutputStream("wps.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(savedWPs);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }


    /******************************************************************
     * Returns the ArrayList of saved workout programs from wps.txt
     * @return The ArrayList of saves workout programs from wps.txt
     */
    public ArrayList loadSavedWPs() {
        ArrayList<HashMap<String,String>> savedWPs = null;
        try {
            FileInputStream fileIn = new FileInputStream("wps.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            savedWPs =
                    (ArrayList<HashMap<String, String>>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
        return savedWPs;
    }


    /******************************************************************
     * Removes the specified workout program from pre-saved workout
     * programs and then saves the remaining workout programs to wps.txt
     * @param wpToDeleteName The name of the workout program to delete
     */
    public void deleteSavedWP(String wpToDeleteName) {
        ArrayList<HashMap<String,String>> savedWPs = loadSavedWPs();
        for(int i = 0; i < savedWPs.size(); i++){
            HashMap<String, String> savedWP = savedWPs.get(i);
            if (savedWP.get("wpName").equals(wpToDeleteName)) {
                savedWPs.remove(savedWP);
            }
        }
        try {
            FileOutputStream fileOut = new FileOutputStream("wps.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(savedWPs);
            out.close();
            fileOut.close();
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
