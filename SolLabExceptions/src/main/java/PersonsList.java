import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonsList {
    private List<Person> people;

    public PersonsList() {
        this.people = new ArrayList<>();
    }

    public void addPerson(Person person) {
        people.add(person);
    }

    public Person findByName(String name) {
        // Verificar formato del nombre
        if (name == null || !name.matches("^[A-Za-z]+ [A-Za-z]+$")) {
            throw new IllegalArgumentException("El nombre debe tener formato 'firstName lastName'");
        }

        // Buscar la persona por nombre
        for (Person person : people) {
            if (person.getName().equals(name)) {
                return person;
            }
        }

        // Si no se encuentra la persona
        throw new RuntimeException("No se encontró a la persona con nombre: " + name);
    }

    public Person clone(Person person) {
        // Generar un nuevo ID
        int newId = (int) (Math.random() * 10000);
        return new Person(newId, person.getName(), person.getAge(), person.getOccupation());
    }

    public void writeToFile(Person person) {
        String fileName = person.getName().replace(" ", "_") + ".txt";

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(person.toString());
            System.out.println("Información escrita en el archivo: " + fileName);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public List<Person> getPeople() {
        return people;
    }
}