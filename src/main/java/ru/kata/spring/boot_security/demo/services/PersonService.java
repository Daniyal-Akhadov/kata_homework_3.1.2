package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Person;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repositories.PersonRepository;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, RoleRepository roleRepository) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
    }

    public List<Person> getUsers() {
        return personRepository.findAll();
    }

    public Person getById(int id) {
        return personRepository.getById(id);
    }

    public Person getByName(String name) {
        return personRepository.findByName(name).orElse(null);
    }

    @Transactional
    public void add(Person person) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        person.getRoles().add(roleUser);

        personRepository.save(person);
        roleUser.getPeople().add(person);
    }

    @Transactional
    public void delete(int id) {
        personRepository.delete(getById(id));
    }

    @Transactional
    public void update(Person updatedPerson) {
        Person person = getById(updatedPerson.getId());
        person.setName(updatedPerson.getName());
        person.setAge(updatedPerson.getAge());

        personRepository.save(person);
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public List<String> getRolesStrings() {
        List<Role> roles = getRoles();
        List<String> rolesStrings = new ArrayList<>();

        for (Role role : roles) {
            rolesStrings.add(role.getName());
        }
        return rolesStrings;
    }
}
