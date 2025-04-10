public class PersonsTest {
    public static void main(String[] args) {
        // Crear algunas personas para las pruebas
        Person person1 = new Person(1, "Juan Perez", 30, "Ingeniero");
        Person person2 = new Person(2, "Maria Lopez", 25, "Doctora");
        Person person3 = new Person(3, "Carlos Rodriguez", 40, "Abogado");

        // Crear la lista de personas
        PersonsList list = new PersonsList();
        list.addPerson(person1);
        list.addPerson(person2);
        list.addPerson(person3);

        testSetAgeWithNegativeValue();

        testFindByNameWithCorrectFormat(list);

        testFindByNameWithIncorrectFormat(list);

        testCloneMethod(list, person1);

        System.out.println("Todas las pruebas completadas.");
    }

    // Test 1: Prueba que setAge lance una excepción si la edad es negativa
    private static void testSetAgeWithNegativeValue() {
        System.out.println("\n----- Test 1: setAge con valor negativo -----");
        try {
            Person person = new Person(1, "Test Person", 30, "Tester");
            person.setAge(-5);
            System.out.println("ERROR: No se lanzó excepción al establecer edad negativa");
        } catch (IllegalArgumentException e) {
            System.out.println("ÉXITO: Se lanzó la excepción esperada: " + e.getMessage());
        }
    }

    // Test 2: Prueba que findByName encuentre correctamente una persona con formato correcto
    private static void testFindByNameWithCorrectFormat(PersonsList list) {
        System.out.println("\n----- Test 2: findByName con formato correcto -----");
        try {
            Person found = list.findByName("Maria Lopez");
            System.out.println("ÉXITO: Se encontró a la persona: " + found);
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo encontrar a la persona: " + e.getMessage());
        }
    }

    // Test 3: Prueba que findByName lance excepción con formato incorrecto
    private static void testFindByNameWithIncorrectFormat(PersonsList list) {
        System.out.println("\n----- Test 3: findByName con formato incorrecto -----");
        try {
            // Formato incorrecto: sin apellido
            Person found = list.findByName("Maria");
            System.out.println("ERROR: No se lanzó excepción con formato incorrecto");
        } catch (IllegalArgumentException e) {
            System.out.println("ÉXITO: Se lanzó la excepción esperada: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: Se lanzó una excepción inesperada: " + e.getMessage());
        }
    }

    // Test 4: Prueba que clone cree una nueva persona con los mismos datos excepto el ID
    private static void testCloneMethod(PersonsList list, Person original) {
        System.out.println("\n----- Test 4: método clone -----");
        Person cloned = list.clone(original);

        System.out.println("Original: " + original);
        System.out.println("Clonado: " + cloned);

        boolean sameId = original.getId() == cloned.getId();
        boolean sameProperties = original.getName().equals(cloned.getName()) &&
                original.getAge() == cloned.getAge() &&
                original.getOccupation().equals(cloned.getOccupation());

        if (!sameId && sameProperties) {
            System.out.println("ÉXITO: El clon tiene los mismos datos pero diferente ID");
        } else {
            System.out.println("ERROR: El clon no se creó correctamente");
            if (sameId) System.out.println("  - Los IDs son iguales cuando deberían ser diferentes");
            if (!sameProperties) System.out.println("  - Las propiedades no son iguales cuando deberían serlo");
        }
    }
}
