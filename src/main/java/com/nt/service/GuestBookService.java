package com.nt.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nt.dao.GuestBookRepository;
import com.nt.exception.GuestNotFoundException;
import com.nt.exception.GuestServiceException;
import com.nt.model.GuestBook;

@Service
public class GuestBookService {
	@Autowired
	private GuestBookRepository repository;

	@PostConstruct
	public void init() {
		ArrayList list = new ArrayList<>(Arrays.asList(new Object[] { new GuestBook(1234, "abc", "xyz", "ab@gmail.com"),
				new GuestBook(1234, "def", "jkl", "de@gmail.com") }));

		repository.save(list);
	}

	public void saveGuest(GuestBook guest) {
		repository.save(guest);
	}

	public List<GuestBook> getAllGuest() {
		return repository.findAll();
	}

	public long deleteAGuest(String firstName) throws GuestServiceException {
		try {
			System.out.println("service");
			return repository.deleteByFirstName(firstName);
		} catch (Exception e) {
			System.out.println("service2");
			System.out.println(e.getMessage());
			throw new GuestServiceException("guest not found with name:" + firstName);
		}

	}

	public String updateAGuest(GuestBook guest, int phoneNumber) {
		GuestBook guest1 = repository.findOne(phoneNumber);
		guest1 = guest;
		repository.save(guest1);
		return "Guest " + guest1.getFirstName() + "has been updated";

	}

	public List<GuestBook> getByEmail(String email) {
		return repository.findByEmail(email);
	}

	public List<GuestBook> getByLastName(String lastName) {
		return repository.findByLastName(lastName);
	}

	public List<GuestBook> getByFirstName(String firstName)throws GuestNotFoundException {
		try{
			return repository.findByLastName(firstName);
		}catch(Exception e) {
			throw new GuestNotFoundException("guest with the requested name " +firstName+ "not found");
		}
	}

	public Page<GuestBook> getPaginatedUser(String name) {
		return repository.findAll(new PageRequest(0, 3, new Sort(name)));
	}

	public List<GuestBook> getByCustomisedquery() {
		return repository.getGuestCustomQuuery();

	}

	public GuestBook getByEmailOrFirstNameOrLastName(String email, String firstName, String lastName) {
		return repository.findByEmailOrFirstNameOrLastName(email, firstName, lastName);
	}

}
