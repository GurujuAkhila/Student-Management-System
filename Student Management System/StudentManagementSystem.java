import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Student {
    private String name;
    private int rollNumber;
    private String course;
    private int[] marks;

    public Student(String name, int rollNumber, String course, int[] marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.course = course;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getCourse() {
        return course;
    }

    public int[] getMarks() {
        return marks;
    }

    public int getTotalMarks() {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }
}

public class StudentManagementSystem extends JFrame {
    private List<Student> students;
    private DefaultTableModel tableModel;
    private JTable studentTable;

    public StudentManagementSystem() {
        students = new ArrayList<>();

        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create table model and table
        String[] columnNames = {"Name", "Roll Number", "Course", "Total Marks"};
        tableModel = new DefaultTableModel(columnNames, 0);
        studentTable = new JTable(tableModel);

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(studentTable);

        // Add scroll pane to frame
        getContentPane().add(scrollPane);

        // Create buttons
        JButton addButton = new JButton("Add");
        JButton viewButton = new JButton("View");
        JButton searchButton = new JButton("Search");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton statisticsButton = new JButton("Statistics");

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(statisticsButton);

        // Add button panel to frame
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Set frame size and make it visible
        setSize(500, 400);
        setVisible(true);

        // Add button listeners

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to add a new student
                try {
            String name = JOptionPane.showInputDialog("Enter student name:");
            int rollNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter roll number:"));
            String course = JOptionPane.showInputDialog("Enter course:");
            int numSubjects = Integer.parseInt(JOptionPane.showInputDialog("Enter number of subjects:"));
            int[] marks = new int[numSubjects];
            
            for (int i = 0; i < numSubjects; i++) {
                marks[i] = Integer.parseInt(JOptionPane.showInputDialog("Enter marks for subject " + (i + 1) + ":"));
            }
            
            Student student = new Student(name, rollNumber, course, marks);
            students.add(student);
            tableModel.addRow(new Object[]{student.getName(), student.getRollNumber(), student.getCourse(), student.getTotalMarks()});
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
        }
            }
        });

        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to view student details
                try {
            for (Student student : students) {
                System.out.println("Name: " + student.getName());
                System.out.println("Roll Number: " + student.getRollNumber());
                System.out.println("Course: " + student.getCourse());
                System.out.println("Total Marks: " + student.getTotalMarks());
                System.out.println("----------------------");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
        }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to search for a student
                try {
            String searchTerm = JOptionPane.showInputDialog("Enter roll number or name to search:");
            boolean found = false;
            
            for (Student student : students) {
                if (student.getRollNumber() == Integer.parseInt(searchTerm) || student.getName().equalsIgnoreCase(searchTerm)) {
                    System.out.println("-----searched student--------------------");
                    System.out.println("Name: " + student.getName());
                    System.out.println("Roll Number: " + student.getRollNumber());
                    System.out.println("Course: " + student.getCourse());
                    System.out.println("Total Marks: " + student.getTotalMarks());
                    System.out.println("------------------------------------------");
                    found = true;
                    break;
                }

            }
            
            if (!found) {
                System.out.println("Student not found.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
        }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to update student details
                try {
            int rollNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter roll number of student to update:"));
            boolean found = false;
            
            for (Student student : students) {
                if (student.getRollNumber() == rollNumber) {
                    String newName = JOptionPane.showInputDialog("Enter updated name:");
                    String newCourse = JOptionPane.showInputDialog("Enter updated course:");
                    int numSubjects = Integer.parseInt(JOptionPane.showInputDialog("Enter number of subjects:"));
                    int[] newMarks = new int[numSubjects];
                    
                    for (int i = 0; i < numSubjects; i++) {
                        newMarks[i] = Integer.parseInt(JOptionPane.showInputDialog("Enter updated marks for subject " + (i + 1) + ":"));
                    }
                    Student stu = new Student(newName, rollNumber, newCourse, newMarks);
                    students.add(stu);
                    int rowIndex = students.indexOf(stu);
                    tableModel.setValueAt(stu.getName(), rowIndex, 0);
                    tableModel.setValueAt(stu.getRollNumber(), rowIndex, 1);
                    tableModel.setValueAt(stu.getCourse(), rowIndex, 2);
                    tableModel.setValueAt(stu.getTotalMarks(), rowIndex, 3);
                    found = true;
                    break;
                }
            }
            
            if (!found) {
                System.out.println("Student not found.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
        }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to delete a student
                try {
                int rollNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter roll number of student to delete:"));
                boolean found = false;
            
            for (Student student : students) {
                if (student.getRollNumber() == rollNumber) {
                    int rowIndex = students.indexOf(student);
                    students.remove(student);
                    tableModel.removeRow(rowIndex);
                    found = true;
                    break;
                }
            }
            
            if (!found) {
                System.out.println("-----------Searched Student-------------------");
                System.out.println("Student not found.");
                System.out.println("--------------------------------------------");

            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
        }
            }
        });

        statisticsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to calculate and display statistics
                try {
            int totalStudents = students.size();
            int totalMarks = 0;
            int highestMarks = Integer.MIN_VALUE;
            int lowestMarks = Integer.MAX_VALUE;
            int passCount = 0;
            
            for (Student student : students) {
                int studentTotalMarks = student.getTotalMarks();
                totalMarks += studentTotalMarks;
                
                if (studentTotalMarks > highestMarks) {
                    highestMarks = studentTotalMarks;
                }
                
                if (studentTotalMarks < lowestMarks) {
                    lowestMarks = studentTotalMarks;
                }
                
                if (studentTotalMarks >= 40) {
                    passCount++;
                }
            }
            double averageMarks = (double) totalMarks / totalStudents;
            double passPercentage = (double) passCount / totalStudents * 100;
            System.out.println("--------Statistics----------------------");
            System.out.println("Total Students: " + totalStudents);
            System.out.println("Average Marks: " + averageMarks);
            System.out.println("Highest Marks: " + highestMarks);
            System.out.println("Lowest Marks: " + lowestMarks);
            System.out.println("Pass Percentage: " + passPercentage + "%");
            System.out.println("-------------------------------------------");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
        }
            }

        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentManagementSystem();
            }
        });
    }
}
