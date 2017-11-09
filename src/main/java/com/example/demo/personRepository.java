package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface personRepository extends CrudRepository<Person,Long> {
}
