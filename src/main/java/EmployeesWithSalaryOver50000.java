import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EmployeesWithSalaryOver50000 {

    private static final String SELECT_QUERY = "SELECT e.firstName FROM Employee e WHERE e.salary > 50000";

    public static void main(String[] args) {

        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Constants.DATABASE_NAME);
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.createQuery(SELECT_QUERY, String.class)
                .getResultList()
                .forEach(System.out::println);

        entityManager.close();

    }
}
