
package com.porfolio.LeoCastillo.Interface;

import com.porfolio.LeoCastillo.Entity.Person;
import java.util.List;


public interface IPersonService {
    // Get a list of people
    public List<Person> getPerson();
    
    // Save an object of type person
    public void savePerson(Person person);
    
    // Delete an object, searched for ID
    public void deletePerson(Long id);
    
    // Search a person by id
    public Person findPerson(Long id);
}
