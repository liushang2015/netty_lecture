package com.liushang.thtoft;

import org.apache.thrift.TException;
import thrift.generated.DataExceotion;
import thrift.generated.Person;
import thrift.generated.PersonService;

public class PersonServiceImpl implements PersonService.Iface {
    @Override
    public Person getPersonByUsername(String username) throws DataExceotion, TException {
        System.out.println("Got Client param£º"+username);
        Person person = new Person();
        person.setUsername(username);
        person.setAge(20);
        person.setMarried(false);
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataExceotion, TException {
        System.out.println("Got Client param:");
        System.out.println(person.getUsername());
        System.out.println(person.getAge());
        System.out.println(person.isMarried());
    }
}
