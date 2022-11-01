import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class AddingANewAddressAndUpdatingEmployee {


    private static final String NEW_ADDRESS = "Vitoshka 15";
    private static final String UPDATE_QUERY = "UPDATE  Employee e SET e.address = :newAddress WHERE e.lastName = :ln";
    private static final String ADDRESS_PARAMETER = "newAddress";
    private static final String NAME_PARAMETER = "ln";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Constants.DATABASE_NAME);
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final String lastName = scanner.nextLine().trim();
        final Address newAddress = new Address();
        newAddress.setText(NEW_ADDRESS);


        entityManager.getTransaction().begin();
        entityManager.persist(newAddress);

        int count = entityManager
                .createQuery(UPDATE_QUERY)
                .setParameter(ADDRESS_PARAMETER, newAddress)
                .setParameter(NAME_PARAMETER, lastName)
                .executeUpdate();


        exitOfProgram(entityManager, count);

        System.out.println(count);
    }

    private static void exitOfProgram(EntityManager entityManager, int count) {
        if (count > 0) {
            entityManager.getTransaction().commit();
        } else {
            entityManager.getTransaction().setRollbackOnly();
        }
        entityManager.close();
    }
}
