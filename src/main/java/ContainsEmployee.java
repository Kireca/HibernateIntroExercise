import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class ContainsEmployee {


    private static final String SELECT_QUERY = "SELECT count(e) FROM Employee e WHERE e.firstName = :fn AND e.lastName = :ln";
    private static final String FIRST_NAME_PARAMETER = "fn";
    private static final String LAST_NAME_PARAMETER = "ln";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Constants.DATABASE_NAME);
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        String[] arguments = scanner.nextLine().split(Constants.SPLIT_DELIMITER);
        String firstName = arguments[0];
        String lastName = arguments[1];

        final Long countOfMatches = entityManager.createQuery(SELECT_QUERY, Long.class)
                .setParameter(FIRST_NAME_PARAMETER, firstName)
                .setParameter(LAST_NAME_PARAMETER, lastName)
                .getSingleResult();


        print(countOfMatches);

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    private static void print(Long countOfMatches) {
        if (countOfMatches == 0) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }
    }
}
