import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class GetEmployeeWithProject {
    private static final String SELECT_QUERY = "SELECT e FROM Employee e WHERE e.id = :id";
   private static final String ID_PARAMETER = "id";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Constants.DATABASE_NAME);
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final int empId = scanner.nextInt();

        String employee = entityManager.createQuery(SELECT_QUERY, Employee.class)
                .setParameter(ID_PARAMETER, empId)
                .getSingleResult()
                .toString();

        System.out.println(employee);
    }
}
