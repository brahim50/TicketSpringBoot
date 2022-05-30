package com.ticket.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.IsNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.ticket.model.Client;
import com.ticket.model.Ticket;
import com.ticket.repository.ClientRepository;
import com.ticket.repository.TicketRepository;
import com.ticket.sevice.TicketService;

@Controller
public class ClientController {
	
	@Autowired
	private ClientRepository repo;
	@Autowired
	private TicketRepository tiko;
	
	
	
	
	
	@GetMapping("/login")
	public String login(Model model) {
		Client user = new Client();
		model.addAttribute("user",user);
		return "login";
	}
	
	
	@PostMapping("/userLogin")
	public String loginUser(@ModelAttribute("user") Client user,HttpSession session,Model model) {
		String userEmail = user.getEmail();
		Client userdata = repo.findByEmail(userEmail);
		if(user.getPassword().equals(userdata.getPassword())) {
			session.setAttribute("nom", userdata.getNom());
			session.setAttribute("id", userdata.getId());
			//private TicketService ticketService;
			//List<Ticket> tickets = ticketService.getAllTicket();
			//model.addAttribute("tickets",ticketRepository.findAll());
			model.addAttribute("ticket", new Ticket());
			return "redirect:/client";
		}
		
		else {
			session.setAttribute("messagewarning", "Email Or Password Invalid...");
			return "login";
		}
		
		
		
	}
	
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new Client());
		return "register";
	}
	
	
	@PostMapping("/process_register")
	public String processRegistration(Client client ,HttpSession session ) {
		
		repo.save(client);
		session.setAttribute("message", "You have Create Account Successfuly...");
		return "redirect:/login";
		
	}
	
	@GetMapping("/client")
	public String viewClient(Model model) {
		//model.addAttribute("tickets",ticketRepository.findAll());
		model.addAttribute("ticket", new Ticket());
		model.addAttribute("tickets",tiko.findAll());
		//<input type="hidden" value="th:text=${session.id}" th:field="*{client}"> 
		return "client";
	}
	
	
	@PostMapping("/addticket")
	public String AjouterTicket(Ticket ticket,HttpSession session) {
		tiko.save(ticket);
		return "redirect:/client";
		
	}
	
	
	

}
