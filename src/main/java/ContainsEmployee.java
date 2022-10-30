import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class ContainsEmployee {


    private static final String SELECT_QUERY = "SELECT count(e) FROM Employee e WHERE e.firstName = :fn AND e.lastName = :ln";


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Constants.DATABASE_NAME);
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        String[] arguments = scanner.nextLine().split(Constants.SPLIT_DELIMITER);
        String firstName = arguments[0];
        String lastName = arguments[1];

        final Long countOfMatches = entityManager.createQuery(SELECT_QUERY, Long.class)
                .setParameter("fn", firstName)
                .setParameter("ln", lastName)
                .getSingleResult();


        if (countOfMatches == 0) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }


        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
