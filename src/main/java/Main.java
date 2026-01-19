import java.util.*;
import java.io.IOException;

public class Main {

  public static void main(String[] args) {
    Service s = new Service();
    Scanner sc = new Scanner(System.in);

    while (true) {
      System.out.println("\n1. Dodaj studenta");
      System.out.println("2. Pokaż studentów");
      System.out.println("3. Szukaj studenta");
      System.out.println("4. Usuń studenta");
      System.out.println("5. Sortuj studentów");
      System.out.println("0. Wyjście");
      System.out.print("Wybór: ");

      int choice = sc.nextInt();
      sc.nextLine();

      try {
        switch (choice) {
          case 1:
            System.out.print("Imię: ");
            String name = sc.nextLine();

            System.out.print("Wiek: ");
            int age = sc.nextInt();
            sc.nextLine();

            System.out.print("Data urodzenia: ");
            String birthDate = sc.nextLine();

            s.addStudent(new Student(name, age, birthDate));
            System.out.println("Dodano studenta");
            break;

          case 2:
            for (Student st : s.getStudents()) {
              System.out.println(st);
            }
            break;

          case 3:
            System.out.print("Podaj imię: ");
            String search = sc.nextLine();
            Student found = s.findStudentByName(search);
            System.out.println(found != null ? found : "Nie znaleziono");
            break;

          case 4:
            System.out.print("Podaj imię do usunięcia: ");
            String del = sc.nextLine();
            System.out.println(
                s.deleteStudentByName(del)
                    ? "Usunięto"
                    : "Nie znaleziono");
            break;

          case 5:
            for (Student st : s.getStudentsSortedByName()) {
              System.out.println(st);
            }
            break;

          case 0:
            System.out.println("Koniec");
            return;

          default:
            System.out.println("Zły wybór");
        }
      } catch (IOException e) {
        System.out.println("Błąd IO: " + e.getMessage());
      }
    }
  }
}
