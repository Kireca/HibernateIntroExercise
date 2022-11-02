import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmployeesMaximumSalaries {

    private static final String SELECT_QUERY = "SELECT d.name , MAX(e.salary)" +
            " FROM Employee AS e" +
            " JOIN Department AS d ON d.id = e.department.id" +
            " GROUP BY d.id" +
            " HAVING MAX(e.salary) < 30000 OR MAX(e.salary) > 70000";
    private static final String PRINT_FORMAT = "%s %s%n";


    public static void main(String[] args) {

        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Constants.DATABASE_NAME);
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

      entityManager.createQuery(SELECT_QUERY, Object[].class)
              .getResultList()
              .forEach(object -> System.out.printf(PRINT_FORMAT,object[0],object[1]));

        entityManager.close();

    }
}
