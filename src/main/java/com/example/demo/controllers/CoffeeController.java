package com.example.demo.controllers;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.beans.Coffee;
import com.example.demo.beans.Search;
import com.example.demo.database.DatabaseAccess;


@Controller
public class CoffeeController {

	List<Coffee> coffeeList = new CopyOnWriteArrayList<Coffee>();

	@Autowired 
	private DatabaseAccess da;
	
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("search", new Search());
		model.addAttribute("coffeeList", da.getCoffees());
		model.addAttribute("coffee", new Coffee());
		return "index";
	}
	
	
	@PostMapping("/insertCoffee")
	public String insertCoffee(Model model, @ModelAttribute Coffee Coffee) {
		
		da.insertCoffee(Coffee.getName(), Coffee.getOrigin(), Coffee.getCoffeeType(), Coffee.getRoast(),
				Coffee.getQuantity());
		model.addAttribute("search", new Search());
		model.addAttribute("coffee", new Coffee());
		model.addAttribute("coffeeList", da.getCoffees());
		return "index";
	}

	@GetMapping("/deleteCoffee/{id}")
	public String deleteCoffee(Model model, @PathVariable Long id) {
		
		da.deleteCoffee(id);
		model.addAttribute("search", new Search());
		model.addAttribute("coffee", new Coffee());
		model.addAttribute("coffeeList", da.getCoffees());
		
		return "index";
	}
	
	@GetMapping("/searchCoffee")
	public String searchCoffee(Model model, @ModelAttribute Search search) {
		
		model.addAttribute("coffee", new Coffee());
		model.addAttribute("coffeeList", da.getCoffeeByName(search.getName()));
		model.addAttribute("search", new Search());

		return "index";
	}
	
	@GetMapping("/editCoffee/{id}")
	public String editCoffee(Model model, @PathVariable Long id) {
		
		Coffee Coffee = da.getCoffeeById(id).get(0);
		model.addAttribute("coffee", Coffee);

		da.deleteCoffee(id);
		model.addAttribute("coffeeList", da.getCoffees());
		model.addAttribute("search", new Search());
		
		return "index";
	}
	
	@GetMapping("/viewAll")
	public String viewAll(Model model) {
		model.addAttribute("search", new Search());
		model.addAttribute("coffeeList", da.getCoffees());
		model.addAttribute("coffee", new Coffee());
		return "index";
	}
}
