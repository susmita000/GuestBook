package com.nt.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.boot.GuestBookApplication;
import com.nt.exception.GuestNotFoundException;
import com.nt.exception.GuestServiceException;
import com.nt.model.GuestBook;
import com.nt.model.Responce;
import com.nt.service.GuestBookService;

@RestController
@RequestMapping("/controller")
public class GuestBookController {
	@Autowired
	private GuestBookService service;
	Logger logger = LoggerFactory.getLogger(GuestBookController.class);

	@GetMapping("/getAll")
	public Responce getAllGuest() {
		logger.debug("data fetched from db on date:" + new Date());
		List<GuestBook> guest = service.getAllGuest();
		logger.debug("no of guests are:" + guest.size());
		return new Responce("Total no of guest" + guest.size(), Boolean.TRUE);
	}

	@DeleteMapping("/deleteOne/{name}")
	public long deleteAGuest(@PathVariable String name)throws GuestServiceException{
		System.out.println("controller");
		return service.deleteAGuest(name);

	}

	@PutMapping("/updateOne/{number}")
	public String updateAGuest(@RequestBody GuestBook guest, @PathVariable int number) {
		return service.updateAGuest(guest, number);
	}

	@PostMapping("/add")
	public Responce addGuest(@RequestBody GuestBook guest) {
		service.saveGuest(guest);
		return new Responce(guest.getPhoneNumber() + "inserted", Boolean.TRUE);

	}

	@GetMapping("/getByEmail")
	public List<GuestBook> getGuestByEmail(@RequestParam("email") String email) {
		return service.getByEmail(email);
	}

	@GetMapping("/getByFirstName")
	public List<GuestBook> getGuestByFirstNme(@RequestParam("firstName") String firstName)throws GuestNotFoundException {
		return service.getByFirstName(firstName);
	}

	@GetMapping("/getByLastName")
	public List<GuestBook> getGuestByLastName(@RequestParam("lastName") String lastName) {
		return service.getByEmail(lastName);
	}

	@GetMapping("/getByAnyValue")
	public Responce getGuestByAnyValue(@RequestParam("email") String email, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		GuestBook guest = service.getByEmailOrFirstNameOrLastName(email, firstName, lastName);
		return new Responce("Guest found with phonenmber:" + guest.getPhoneNumber(), Boolean.TRUE);
	}

}
