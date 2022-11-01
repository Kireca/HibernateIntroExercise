import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class FindLatest10Projects {

    private static final String SELECT_QUERY = "SELECT p FROM Project p ORDER BY p.name ,p.startDate desc";
    private static final String PRINT_FORMAT = "Project name:%s%n" +
            "Project Description:%s%n" +
            "Project Start Date:%s%n" +
            "Project End Date:%s%n";

    public static void main(String[] args) {

        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Constants.DATABASE_NAME);
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Project> projectList = entityManager.createQuery(SELECT_QUERY, Project.class)
                .setMaxResults(10).getResultList();


        projectList.forEach(project -> System.out.printf(PRINT_FORMAT,
                project.getName(),
                project.getDescription(),
                project.getStartDate(),
                project.getEndDate()));

        entityManager.close();
    }
}
