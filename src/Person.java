public class Person {
    private String name;
    private Integer age;

    public Person(String name, Integer age) throws InvalidAgeException {
        if (age < 0) {
            throw new InvalidAgeException("Wiek nie może być mniejszy niż 0");
        } else if (age > 100) {
            throw new InvalidAgeException("Wiek nie może być większy niż 100");
        }

        this.name = name;
        this.age = age;
    }

    public Person(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(Integer age) throws InvalidAgeException {
        if (age < 0) {
            throw new InvalidAgeException("Wiek nie może być mniejszy niż 0");
        } else if (age > 100) {
            throw new InvalidAgeException("Wiek nie może być większy niż 100");
        }
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Osoba: Person{" + "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
