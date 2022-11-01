import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class FindEmployeesByFirstName {

    private static final String SELECT_QUERY = "SELECT e FROM Employee e WHERE lower(SUBSTRING(e.firstName,1,2)) = lower(:givenString)";
    private static final String PATTERN_FORMAT = "givenString";
    private static final String PRINT_FORMAT = "%s %s - %s - ($%.2f)%n";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final String pattern = scanner.nextLine();

        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Constants.DATABASE_NAME);
        final EntityManager entityManager = entityManagerFactory.createEntityManager();


        List<Employee> employeeList = entityManager.createQuery(SELECT_QUERY, Employee.class)
                .setParameter(PATTERN_FORMAT, pattern)
                .getResultList();

        for (Employee e : employeeList) {
            System.out.printf(PRINT_FORMAT,e.getFirstName(),e.getLastName(),e.getJobTitle(),e.getSalary());
        }


    }
}
