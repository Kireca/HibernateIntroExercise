import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class IncreaseSalaries {

    private static final String SELECT_QUERY = "SELECT e FROM Employee e WHERE e.department.name in ('Engineering','Tool Design','Marketing','Information Services')";

    public static void main(String[] args) {

        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Constants.DATABASE_NAME);
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.createQuery(SELECT_QUERY, Employee.class)
                .getResultList()
                .forEach(employee -> employee.setSalary(employee.getSalary()
                         .multiply(BigDecimal.valueOf(1.12))));


        entityManager.createQuery(SELECT_QUERY,Employee.class)
                .getResultList()
                .forEach(e ->  System.out.printf("%s %s ($%.2f)%n",e.getFirstName(), e.getLastName(), e.getSalary()));


        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
