import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class RemoveTowns {

    private static final String SELECT_TOWN_NAME_QUERY = "SELECT t FROM Town AS t WHERE t.name = :tName";
    private static final String SELECT_ADDRESS_QUERY = "SELECT a FROM Address AS a WHERE a.town.name = :tName";
    private static final String TOWN_NAME = "tName";

    private final static String PRINT_FORMAT = "%d address%s in %s deleted%n";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Constants.DATABASE_NAME);
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        final String townName = scanner.nextLine();

        final Town currentTown = entityManager.createQuery(SELECT_TOWN_NAME_QUERY, Town.class)
                .setParameter(TOWN_NAME, townName)
                .getSingleResult();

        final List<Address> addressList = entityManager
                .createQuery(SELECT_ADDRESS_QUERY, Address.class)
                .setParameter(TOWN_NAME, townName)
                .getResultList();

        entityManager.getTransaction().begin();

        addressList.forEach(address -> {
            for (Employee employee : address.getEmployees()) {
                employee.setAddress(null);
            }
            address.setTown(null);
            entityManager.remove(address);
        });

        entityManager.remove(currentTown);

        System.out.printf(PRINT_FORMAT,
                addressList.size(),
                addressList.size() != 1 ? "es" : "",
                currentTown.getName());


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
