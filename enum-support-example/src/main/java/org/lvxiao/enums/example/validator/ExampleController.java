package org.lvxiao.enums.example.validator;

import org.lvxiao.enums.example.entity.Person;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/example")
public class ExampleController {

    @PostMapping("testValidator")
    public Person testValidator(@Valid @RequestBody Person person) {
        System.out.println(person);
        return person;
    }

}
