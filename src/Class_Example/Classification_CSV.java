package Class_Example;

import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Classification_CSV {
    public static void main(String[] args) {
    	//Path for reading your file
        String inputPath = "C://Users//email//Downloads//score.csv ";  
        //Path for saving your file
        String outputPath = "C://Users//email//Downloads//classified_scores.csv"; 

        // Create List to save something to the outputPath
        List<String> lineoutput = new ArrayList<>();
        
        // Read CSV file using the inputFilePath
        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {
            
        	// Read line
        	String line;
            boolean firstLine = true;
            
            // Read line Until line is not null
            while ((line = reader.readLine()) != null) {
            	// If firstLine 
                if (firstLine) {
                    firstLine = false;
                    continue;  // Skip the header line
                }

                String[] columns = line.split(",");
                if (columns.length == 2) {
                    String name = columns[0];
                    int score = Integer.parseInt(columns[1]);

                    String grade;
                    if (score >= 90) {
                        grade = "A";
                    } else if (score >= 80) {
                        grade = "B";
                    } else if (score >= 70) {
                        grade = "C";
                    } else if (score >= 60) {
                        grade = "D";
                    } else {
                        grade = "F";
                    }

                    String outputLine = name + "," + score + "," + grade;
                    lineoutput.add(outputLine);

                    String message = "Student: " + name + "\nScore: " + score + "\nGrade: " + grade;
                    JOptionPane.showMessageDialog(null, message);
                }
            }

            // Save the classified scores to the output CSV file
            try (FileWriter writer = new FileWriter(outputPath)) {
                for (String outputLine : lineoutput) {
                    writer.write(outputLine + "\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
