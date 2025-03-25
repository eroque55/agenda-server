package com.roque.agenda;

import com.roque.agenda.controllers.Controller;
import com.roque.agenda.models.Contact;
import com.roque.agenda.models.ContactType;
import com.roque.agenda.models.Customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;

@CrossOrigin(maxAge = 3600)
@SpringBootApplication
public class AgendaApplication {
	public static void main(String[] args) {
		SpringApplication.run(AgendaApplication.class, args);

		Controller controller = new Controller();

		Customer customer1 = new Customer();
		customer1.setName("João Silva");
		customer1.setCpf("82186748029");
		customer1.setBirthday(LocalDate.of(1990, 5, 20));
		customer1.setAddress("Rua das Flores, 123, São Paulo");

		Customer customer2 = new Customer();
		customer2.setName("Maria Oliveira");
		customer2.setCpf("68434827085");
		customer2.setBirthday(LocalDate.of(1985, 8, 15));

		Customer customer3 = new Customer();
		customer3.setName("Carlos Souza");
		customer3.setCpf("96322094009");

		Contact contact1 = new Contact();
		contact1.setCustomer(customer1);
		contact1.setType(ContactType.EMAIL);
		contact1.setValue("joao.silva@email.com");

		Contact contact2 = new Contact();
		contact2.setCustomer(customer1);
		contact2.setType(ContactType.PHONE);
		contact2.setValue("11987654321");

		Contact contact3 = new Contact();
		contact3.setCustomer(customer2);
		contact3.setType(ContactType.EMAIL);
		contact3.setValue("maria.oliveira@email.com");

		Contact contact4 = new Contact();
		contact4.setCustomer(customer2);
		contact4.setType(ContactType.PHONE);
		contact4.setValue("21912345678");

		Contact contact5 = new Contact();
		contact5.setCustomer(customer3);
		contact5.setType(ContactType.EMAIL);
		contact5.setValue("carlos.souza@email.com");

		Contact contact6 = new Contact();
		contact6.setCustomer(customer3);
		contact6.setType(ContactType.PHONE);
		contact6.setValue("31987651234");

		controller.create(customer1);
		controller.create(customer2);
		controller.create(customer3);

		controller.create(contact1);
		controller.create(contact2);
		controller.create(contact3);
		controller.create(contact4);
		controller.create(contact5);
		controller.create(contact6);
	}
}
