package WorkoutAssistant;
import java.util.*;
import java.awt.*;
import javax.swing.*;


public class WorkoutAssistantGUI {


    private JFrame mainFrame;
    private JPanel sideMenuPanel;
    private JPanel wpsMainPanel;
    private WorkoutProgramManager wpm;

    public WorkoutAssistantGUI() {
        wpm = new WorkoutProgramManager();
        createMainFrame();
        createSideMenuPanel();
        createWPsMainPanel();
        compileMainFrame();
    }


    public void createMainFrame() {
        mainFrame = new JFrame("Workout Assistant");
        mainFrame.setSize(1150, 700);
    }


    public void compileMainFrame() {
        mainFrame.setVisible(true);
    }


    public void createSideMenuPanel() {
        sideMenuPanel = new JPanel();
        sideMenuPanel.setLayout(new BoxLayout(sideMenuPanel,
        BoxLayout.Y_AXIS));
        sideMenuPanel.setPreferredSize(new Dimension(190, 700));
        sideMenuPanel.setBackground(Color.BLACK);

        JLabel programTitle =
        new JLabel("<html>Workout<br>Assistant<html>");
        programTitle.setBorder(BorderFactory.createEmptyBorder(25, 45,
        100, 10));
        programTitle.setFont(new Font("Comic Sans", Font.BOLD, 25));
        programTitle.setForeground(Color.WHITE);
        sideMenuPanel.add(programTitle);


        JButton wpsMenuButton = new JButton("Workout Programs");
        wpsMenuButton.setBackground(Color.BLACK);
        wpsMenuButton.setForeground(Color.WHITE);
        wpsMenuButton.setBorder(BorderFactory.createEmptyBorder(0, 35,
        50, 0));
        wpsMenuButton.addActionListener(e ->
        wpsMainPanel.setVisible(true));
        sideMenuPanel.add(wpsMenuButton);


        JButton mpsMenuButton = new JButton("Meal Programs");
        mpsMenuButton.setBackground(Color.BLACK);
        mpsMenuButton.setForeground(Color.WHITE);
        mpsMenuButton.setBorder(BorderFactory.createEmptyBorder(0, 40, 50,
        0));
        sideMenuPanel.add(mpsMenuButton);

        sideMenuPanel.setVisible(true);
        mainFrame.add(sideMenuPanel, BorderLayout.WEST);
    }


    public void createWPsMainPanel() {
        wpsMainPanel = new JPanel();
        SpringLayout wpsMainPanelLayout = new SpringLayout();
        wpsMainPanel.setLayout(wpsMainPanelLayout);
        wpsMainPanel.setPreferredSize(new Dimension(960, 0));

        JButton createWPButon = new JButton("Create New Workout " +
        "Program");
        createWPButon.addActionListener(e -> createNewWP());
        createWPButon.setBackground(Color.WHITE);
        createWPButon.setForeground(Color.BLACK);
        wpsMainPanel.add(createWPButon);
        wpsMainPanelLayout.putConstraint(SpringLayout.WEST,
        createWPButon, 744,
        SpringLayout.WEST, wpsMainPanel);
        wpsMainPanelLayout.putConstraint(SpringLayout.NORTH, createWPButon,
        0, SpringLayout.NORTH, wpsMainPanel);

        JLabel savedWPsLabel = new JLabel("Saved Workout Programs");
        savedWPsLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        wpsMainPanel.add(savedWPsLabel);
        wpsMainPanelLayout.putConstraint(SpringLayout.WEST,
        savedWPsLabel, 355, SpringLayout.WEST, wpsMainPanel);
        wpsMainPanelLayout.putConstraint(SpringLayout.NORTH,
        savedWPsLabel, 220, SpringLayout.NORTH, wpsMainPanel);

        JPanel savedWPsPanel = new JPanel();
        ArrayList<HashMap<Object, Object>> savedWPs =
        wpm.loadSavedWPs();
        for (HashMap<Object, Object> savedWP : savedWPs) {
            JButton wp = new JButton((String) savedWP.get("wpName"));
            wp.setBackground(Color.WHITE);
            savedWPsPanel.add(wp);
        }

        JScrollPane savedWPsPane = new JScrollPane(savedWPsPanel);
        savedWPsPane.setPreferredSize(new Dimension(111, 100));
        savedWPsPane.createHorizontalScrollBar();
        savedWPsPane.setBackground(Color.WHITE);
        wpsMainPanel.add(savedWPsPane);
        wpsMainPanelLayout.putConstraint(SpringLayout.WEST,
        savedWPsPane, 425,SpringLayout.WEST, wpsMainPanel);
        wpsMainPanelLayout.putConstraint(SpringLayout.NORTH,
        savedWPsPane, 250, SpringLayout.NORTH, wpsMainPanel);


        wpsMainPanel.setVisible(false);
        mainFrame.add(wpsMainPanel);
    }

    private void createNewWP() {
        JTextField wpNameField = new JTextField(5);
        JTextField wpDescriptionField = new JTextField(5);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Name:"));
        myPanel.add(wpNameField);
        myPanel.add(new JLabel("Description (Optional):"));
        myPanel.add(wpDescriptionField);


        int result = JOptionPane.showConfirmDialog(null, myPanel,
        "Create A New Workout Program", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION)
            wpm.createWP(wpNameField.getText(),
            wpDescriptionField.getText(), "");
            wpsMainPanel.doLayout();
    }


    public static void main(String args[]) {
        WorkoutAssistantGUI workoutAssistant =
        new WorkoutAssistantGUI();
    }
}
