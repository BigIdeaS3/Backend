package com.rene.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface RESTCRUD<T,R> {

    @RequestMapping(value = "/",method = RequestMethod.POST)
    ResponseEntity create(@RequestBody T entity);

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    ResponseEntity read(@PathVariable("id") R id);

    @RequestMapping(value = "/",method = RequestMethod.GET)
    ResponseEntity readAll();

    @RequestMapping(value = "/",method = RequestMethod.PUT)
    ResponseEntity update(@RequestBody T entity);

    @RequestMapping(value = "/",method = RequestMethod.DELETE)
    ResponseEntity delete(R id);

}