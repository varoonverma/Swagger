package com.trial.swagger.controller;

import com.trial.swagger.dao.BookDao;
import com.trial.swagger.model.Book;
import com.wordnik.swagger.annotations.ApiError;
import com.wordnik.swagger.annotations.ApiErrors;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookDao bookDao;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ApiOperation(value = "Retrieves a book based on their id")
    @ApiErrors(value = {@ApiError(code=404, reason = "No book corresponding to the id was found")})
    @ResponseBody
    public ResponseEntity<String> showJson(@ApiParam(name = "id" , required = true, value = "The id of the book that needs to be retrieved")@PathVariable("id") Long id) {
        Book book = bookDao.findBook(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        if (book == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(book.toJson(), headers, HttpStatus.OK);
    }


}
