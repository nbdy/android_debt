package io.eberlein.debt;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class Persons {
    private List<Person> persons;

    public Persons(){
        persons = new ArrayList<>();
    }

    public static Persons get(){
        Persons p = new Persons();
        for(String k : Paper.book(Static.BOOK_PERSONS).getAllKeys()) {
            Log.d("Persons.get", k);
            p.add(Paper.book(Static.BOOK_PERSONS).read(k));
        }
        return p;
    }

    public boolean add(Person n){
        Log.d("Persons.add", n.toString());
        for(Person p : persons){
            if(p.getName().equals(n.getName()) && p.getFrom().equals(n.getFrom())) return false;
        }
        persons.add(n);
        return true;
    }

    public void archive(Person person){
        persons.remove(person);
        Paper.book(Static.BOOK_ARCHIVED_PERSONS).write(person.getKey(), person);
        Paper.book(Static.BOOK_PERSONS).delete(person.getKey());
    }

    public void save(){
        for(Person p : persons) p.save();
    }

    public Person get(int i) {
        return persons.get(i);
    }

    public int size() {
        return persons.size();
    }
}
