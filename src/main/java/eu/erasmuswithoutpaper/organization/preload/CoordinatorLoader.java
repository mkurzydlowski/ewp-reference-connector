
package eu.erasmuswithoutpaper.organization.preload;

import eu.erasmuswithoutpaper.internal.JsonHelper;
import eu.erasmuswithoutpaper.organization.entity.Coordinator;
import eu.erasmuswithoutpaper.organization.entity.Person;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class CoordinatorLoader {
    @PersistenceContext(unitName = "connector")
    EntityManager em;
    
    public void createDemoDataIkea() throws IOException {
        persistCoordinator("{'institutionId':'ikea.university.se','organizationUnitId':'ikea.ou1.se','header':'INSURANCE'}", getPerson("9001013344"));
        persistCoordinator("{'institutionId':'ikea.university.se','header':'ADMISSION'}", getPerson("9107146991"));
        persistCoordinator("{'institutionId':'ikea.university.se','organizationUnitId':'ikea.ou2.se','header':'COURSE'}", getPerson("8906093845"));
    }
    public void createDemoDataPomodoro() throws IOException {
        persistCoordinator("{'institutionId':'pomodoro.university.it','organizationUnitId':'pomodoro.ou1.it','header':'COURSE'}", getPerson("8810126789"));
        persistCoordinator("{'institutionId':'pomodoro.university.it','header':'ADMISSION'}", getPerson("8602181287"));
        persistCoordinator("{'institutionId':'pomodoro.university.it','organizationUnitId':'pomodoro.ou1.it','header':'INSURANCE'}", getPerson("9104125620"));
    }

    private void persistCoordinator(String coordinatorJson, Person person) throws IOException {
        Coordinator coordinator = JsonHelper.mapToObject(Coordinator.class, coordinatorJson);
        coordinator.setPerson(person);
        em.persist(coordinator);
    }
    
    private Person getPerson(String personId) throws IOException {
        Query query = em.createNamedQuery(Person.findByPersonId).setParameter("personId", personId);
        List<Person> personList = query.getResultList();
        if (personList.size() != 1) {
           throw new IllegalArgumentException("Person id " + personId + " doesn't return an unique person.");
        }
        
        return personList.get(0);
    }
}
