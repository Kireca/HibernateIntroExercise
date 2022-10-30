import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class ChangeCasing {

//    <property name="hibernate.show_sql" value="true"/>

   private static final String UPDATE_ALL_TOWNS_WITH_LENGTH_NAME_MORE_THEN_5 = "update Town as t SET t.name = upper(t.name) where length(t.name) <= 5";

    public static void main(String[] args) {

        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Constants.DATABASE_NAME);
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.createQuery(UPDATE_ALL_TOWNS_WITH_LENGTH_NAME_MORE_THEN_5).executeUpdate();

//        Query query = entityManager.createQuery("SELECT t FROM Town t", Town.class);
//
//        List<Town> resultList = query.getResultList();
//
//        for (Town town : resultList) {
//            final String townName = town.getName();
//
//            if (townName.length() <= 5){
//               town.setName(townName.toUpperCase());
//                entityManager.persist(town);
//            }
//        }
//

        entityManager.getTransaction().commit();
        entityManager.close();

    }

}
