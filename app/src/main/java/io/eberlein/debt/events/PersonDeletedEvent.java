package io.eberlein.debt.events;

import io.eberlein.debt.Person;

public class PersonDeletedEvent {
    private Person person;

    public PersonDeletedEvent (Person p){
        person = p;
    }

    public Person getPerson() {
        return person;
    }
}
