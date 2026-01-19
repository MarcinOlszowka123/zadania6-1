public class Student {

    private String name;
    private int age;
    private String birthDate;

    public Student(String name, int age, String birthDate) {
        this.name = name;
        this.age = age;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return name + " " + age + " " + birthDate;
    }

    public static Student parse(String str) {
        String[] data = str.split(" ");
        if (data.length != 3) {
            return new Student("ParseError", -1, "Unknown");
        }
        return new Student(data[0], Integer.parseInt(data[1]), data[2]);
    }
}
