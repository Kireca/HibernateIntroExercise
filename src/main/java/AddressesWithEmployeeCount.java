import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AddressesWithEmployeeCount {

    private static final String SELECT_QUERY = "SELECT a FROM Address a order by a.employees.size desc";

    public static void main(String[] args) {

        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Constants.DATABASE_NAME);
        final EntityManager entityManager = entityManagerFactory.createEntityManager();


        entityManager.createQuery(SELECT_QUERY, Address.class)
                .setMaxResults(10)
                .getResultList()
                .forEach(System.out::println);

        entityManager.close();
    }
}
