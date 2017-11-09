package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class personController {
    @Autowired
    personRepository perRepository;

    @RequestMapping("/")
    public String listPersons(Model model)
    {

        model.addAttribute ( "persons",perRepository.findAll () );

        //return "list"

        return "mainpage";
    }


    @GetMapping("/add")
    public String personForm(Model model)
    {

        model.addAttribute ( "person",new Person() );

        return "personform";
    }



    @PostMapping("/process")
    public String processForm(@Valid Person person, BindingResult result)
    {
        System.out.println (result);
        if(result.hasErrors ())
        {
            return "personform";
        }
        perRepository.save ( person );

        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showPerson(@PathVariable("id") long id, Model model)
    {
        model.addAttribute ( "person" ,perRepository.findOne ( id ));

        return "show";
    }

    @RequestMapping("/update/{id}")
    public String modifyPerson(@PathVariable("id") long id,Model model)
    {
        model.addAttribute ( "person",perRepository.findOne ( id ) );

        return "personform";
    }

    @RequestMapping("/delete/{id}")
    public String delPerson(@PathVariable("id") long id)
    {
        perRepository.delete( id );

        return "redirect:/";
    }


}
