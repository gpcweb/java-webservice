package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@Controller
public class UserController {
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userDAO.list();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
	
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public String getUser(Model model, @PathVariable("id") Long id) {
        User user = userDAO.get(id);
        model.addAttribute("name", user.getUserName());
        model.addAttribute("image", user.getImage());
        return "users/show";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody User user, UriComponentsBuilder ucBuilder, BindingResult result) {
        UserValidator userValidator = new UserValidator();
        userValidator.validate(user, result);

        if (result.hasErrors()){
        	HttpStatus 	   status      = HttpStatus.BAD_REQUEST;
        	CustomApiError customError = new CustomApiError();
        	for (FieldError error: result.getFieldErrors()) {
        		if (error.getDefaultMessage().equals("required")) {
        			customError.setMessage("Bad request");
        			status      = HttpStatus.BAD_REQUEST;
        		} else if (error.getDefaultMessage().equals("not_allowed")) {
        			customError.setMessage("Unauthorized");
        			status = HttpStatus.UNAUTHORIZED;
        		}	
        	}
        	return new ResponseEntity(customError, status);
        }
        else {
            user = userDAO.create(user);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
            return new ResponseEntity(headers, HttpStatus.CREATED);
        }
    }
}
