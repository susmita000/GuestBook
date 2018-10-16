package com.nt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.nt.model.GuestBook;

public interface GuestBookRepository extends JpaRepository<GuestBook, Integer> {
	@Transactional
	long deleteByFirstName(String firstName);

	List<GuestBook> findByEmail(String email);

	List<GuestBook> findByFirstName(String firstName);

	List<GuestBook> findByLastName(String lastName);

	@Query("from GuestBook")
	List<GuestBook> getGuestCustomQuuery();

	GuestBook findByEmailOrFirstNameOrLastName(String email, String firstName, String lastName);

}
