package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person getFirstByFirstNameAndLastName(String firstName, String lastName);

    List<Person> getByFirstNameAndLastName(String firstName, String lastName);
}

