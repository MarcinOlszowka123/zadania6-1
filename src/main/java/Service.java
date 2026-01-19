import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Service {

    private final String FILE = "db.txt";

    public void addStudent(Student student) throws IOException {
        BufferedWriter b = new BufferedWriter(new FileWriter(FILE, true));
        b.write(student.toString());
        b.newLine();
        b.close();
    }

    public ArrayList<Student> getStudents() throws IOException {
        ArrayList<Student> ret = new ArrayList<Student>();
        File file = new File(FILE);
        if (!file.exists())
            return ret;

        BufferedReader reader = new BufferedReader(new FileReader(FILE));
        String line;
        while ((line = reader.readLine()) != null) {
            ret.add(Student.parse(line));
        }
        reader.close();
        return ret;
    }

    public Student findStudentByName(String name) throws IOException {
        for (Student s : getStudents()) {
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }

    public boolean deleteStudentByName(String name) throws IOException {
        ArrayList<Student> students = getStudents();
        boolean removed = students.removeIf(
                s -> s.getName().equalsIgnoreCase(name));

        if (removed) {
            saveAll(students);
        }
        return removed;
    }

    public ArrayList<Student> getStudentsSortedByName() throws IOException {
        ArrayList<Student> students = getStudents();
        Collections.sort(students, Comparator.comparing(Student::getName));
        return students;
    }

        public void updateStudentAge(String name, int newAge) throws IOException {
            ArrayList<Student> students = getStudents();
            boolean found = false;

            for (Student s : students) {
                if (s.getName().equalsIgnoreCase(name)) {
                    s = new Student(s.getName(), newAge, s.getBirthDate());
                    students.set(students.indexOf(s), s);
                     System.out.println("Zaktualizowano wiek studenta: " + s);
                     System.out.println("Nowy wiek studenta: " + newAge);
                    found = true;
                    break;
                }
            }

            if (found) {
                saveAll(students);
            } else {
                throw new IOException("Nie znaleziono studenta");
            }
        }


    private void saveAll(ArrayList<Student> students) throws IOException {
        BufferedWriter b = new BufferedWriter(new FileWriter(FILE, false));
        for (Student s : students) {
            b.write(s.toString());
            b.newLine();
        }
        b.close();
    }
}
