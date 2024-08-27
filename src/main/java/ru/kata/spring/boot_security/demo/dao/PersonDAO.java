package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class PersonDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public Person getPersonById(int id) {
        return entityManager.find(Person.class, id);
    }

    public List<Person> getPeople() {
        return entityManager.createQuery("from Person", Person.class).getResultList();
    }

    @Transactional
    public void addPerson(Person person) {
        entityManager.persist(person);
    }

    @Transactional
    public void deletePerson(Person person) {
        Person managedPerson = entityManager.merge(person);
        entityManager.remove(managedPerson);
    }

    @Transactional
    public void updatePerson(Person person) {
        entityManager.merge(person);
    }
}
