import org.w3c.dom.ls.LSOutput;

import javax.management.ImmutableDescriptor;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

public class Main {
    public static void main(String[] args) {
        Person person = null;

        try {
            person = new Person("Marek", 20);
        } catch (InvalidAgeException e) {
            System.out.println("Złapano wyjątek: " + e.getMessage());
        }

//        try {
//            Person person2 = new Person("Adam", -5); // Wiek nie może być mniejszy od 0
//        } catch (InvalidAgeException e) {
//            System.out.println("Złapano wyjątek: " + e.getMessage());
//        }

        try {
            person.setAge(-10);
        } catch (InvalidAgeException e) {
            System.out.println("Złapano wyjątek: " + e.getMessage());
        }

        System.out.println("Osoba: " + person);

        Person person2 = new Person("Magda", 35);
        Person person3 = new Person("Andrzej", 28);

        List<Person> immutablePersonList = List.of(person, person2, person3); // do tej kolekcji nie można dodawać nowych elementów
//        immutablePersonList.add(person) -- Rzuca wyjątek ponieważ nie możemy zmieniać zawartości kolekcji niemutowalnej
        // lista pozwala na powielanie tych samych obiektów
        System.out.println("Lista niemutowalna: " + immutablePersonList);

        List<Person> mutablePersonList = new ArrayList<>();
        mutablePersonList.add(person);
        mutablePersonList.add(person2);
        mutablePersonList.add(person3);
        System.out.println("Lista mutowalna: " + mutablePersonList);

        Set<Person> immutablePersonSet = Set.of(person, person2, person3);
//        immutablePersonSet.add(person) -- rzuca wyjątkiem bo nie można zmieniać zawartości niemutowalnej
        // set nie pozwala na powielanie tych samych wartosci
        System.out.println("Set niemutowalny: " + immutablePersonSet);

        Set<Person> mutablePersonSet = new HashSet<>();
        mutablePersonSet.add(person);
        mutablePersonSet.add(person2);
        mutablePersonSet.add(person3);
        mutablePersonSet.add(person); // sprawdza, że ten obiekt już istnieje w tym secie i go ignoruje
        System.out.println("Set mutowalny: " + mutablePersonSet);

        Map<Integer, Person> immutablePersonMap = Map.of(1, person, 2, person2, 3,person3); // Klucz musi być unikalny
        System.out.println("Mapa niemutowalna: " + immutablePersonMap);

        Map<String, Person> mutablePersonMap = new HashMap<>();
        mutablePersonMap.put("klucz1", person);
        mutablePersonMap.put("klucz2", person2);
        mutablePersonMap.put("klucz3", person3);
        mutablePersonMap.put("klucz1", person2);
        System.out.println("Mapa mutowalna: " + mutablePersonMap);

        // Streamy
        // Operacja Reduce

//        List<Integer> ageList = mutablePersonList.stream()
//                .map(Person::getAge)
//                .collect(Collectors.toList());
//
//        Integer ageSum = ageList.stream()
//                .reduce(0, (sum, value) -> sum + value);
//
//        System.out.println("Suma lat: " + ageSum);
//
//        double averageAge = (double) ageSum / ageList.size();
//
//        System.out.println("Średnia wieku: " + averageAge);

        Integer ageSum1 = immutablePersonList.stream() // Uproszczona wersja powyższego kodu
                .map(p -> p.getAge())
                .reduce(0, (sum, value) -> sum + value);

        System.out.println("Suma stream chain: " + ageSum1);

        List<String> onlyNames = mutablePersonList.stream()
                .map(Person::getName)
                .toList();
        System.out.println("Lista imion: " + onlyNames);

        List<Person> ageMoreThan25 = mutablePersonList
                .stream()
                .filter(c -> c.getAge() > 25 )
                .toList();
        System.out.println("Osoby starsze niż 25 lat: " + ageMoreThan25);

        List<Person> sortedList = mutablePersonList
                .stream()
                .sorted(Comparator.comparing(Person::getName))
                .toList();
        System.out.println("Kolejność alfabetyczna: " + sortedList);

        immutablePersonList
                .forEach(System.out::println);

        List<Person> emptyList = Collections.emptyList();

        Optional<Person> min = emptyList // immutablePersonList
                .stream()
                .min(Comparator.comparingInt(Person::getAge));

        min.ifPresentOrElse(p -> System.out.println("Minimalny wiek to: " + p.getAge()), () -> System.out.println("Nie odnaleziono żadnej osoby"));

        Optional<Person> max = immutablePersonList // emptyList
                .stream()
                .max(Comparator.comparingInt(Person::getAge));

        max.ifPresentOrElse(p -> System.out.println("Maksymalny wiek to: " + p.getAge()), () -> System.out.println("Nie odnaleziono żadnej osoby"));
    }
}