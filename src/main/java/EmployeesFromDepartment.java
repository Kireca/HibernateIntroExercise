import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.stream.Collectors;

public class EmployeesFromDepartment {

    private static final String SELECT_QUERY = "SELECT e FROM Employee e" +
            " WHERE e.department.name = :dp" +
            " ORDER BY e.salary , e.id";

    private static final String PRINT_FORMAT = "%s %s from %s - $%.2f%n";
    private static final String DEPARTMENT = "Research and Development";


    public static void main(String[] args) {

        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Constants.DATABASE_NAME);
        final EntityManager entityManager = entityManagerFactory.createEntityManager();


        entityManager.createQuery(SELECT_QUERY, Employee.class)
                .setParameter("dp", DEPARTMENT)
                .getResultList()
                .forEach(e -> System.out.printf(PRINT_FORMAT, e.getFirstName(), e.getLastName(), DEPARTMENT, e.getSalary()));






        entityManager.close();
    }
}
