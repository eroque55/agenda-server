package com.roque.agenda;

import com.roque.agenda.controllers.Controller;
import com.roque.agenda.models.Contact;
import com.roque.agenda.models.ContactType;
import com.roque.agenda.models.Customer;
import com.roque.agenda.models.DomainEntity;
import com.roque.agenda.utils.Hibernate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class AgendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendaApplication.class, args);

//		Controller controller = new Controller();
//		Customer customer = new Customer();
//		customer.setId(952);
//		customer.setAddress("enderecinho");
//		System.out.println(controller.update(customer));
	}
}
