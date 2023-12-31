package com.ivoronline.spring_boot_db_multitenant_filter.controllers;

import com.ivoronline.spring_boot_db_multitenant_filter.config.TenantIdentifierResolver;
import com.ivoronline.spring_boot_db_multitenant_filter.entities.Person;
import com.ivoronline.spring_boot_db_multitenant_filter.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

  //PROPERTIES
  @Autowired PersonRepository         personRepository;
  @Autowired TenantIdentifierResolver tenantIdentifierResolver;

  //================================================================
  // ADD PERSON
  //================================================================
  @RequestMapping("AddPerson")
  String addPerson(@RequestParam String schema, @RequestParam String name, @RequestParam Integer age) {

    //SET TENANT
    //tenantIdentifierResolver.setCurrentTenant(schema);

    //CREATE ENTITY OBJECT
    Person person      = new Person();
           person.name = name;
           person.age  = age;

    //STORE ENTITY OBJECT INTO DB
    personRepository.save(person);

    //RETURN SOMETHING TO BROWSER
    return person.name + " was stored into DB";

  }

}


