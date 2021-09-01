package Projects.Competitions_Register.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Projects.Competitions_Register.db.Database;
import Projects.Competitions_Register.model.Competition;
import Projects.Competitions_Register.model.Status;
import Projects.Competitions_Register.model.User;

@Controller
public class AppController {

	@GetMapping ("/")
	public String index () {
		
		return "index.html";
		
	}
	
	@PostMapping ("/mainPage")
	public String mainPage (Model model, 
			@RequestParam (name="username") String username, 
			@RequestParam (name="password") String password) {
		
		String returnPage=null; 
		
		Database db=new Database(); 
		
		List<User> users=db.getUsers();  
		List<Competition> allCompetitions=null; 
		ArrayList<Competition> actualCompetitions=null; 
		ArrayList<Competition> 
		
		User user=new User (username, password); 
		
		for (int usersIndex=0; usersIndex<users.size(); usersIndex++) {
			
			if ( (user.getUsername().equals(users.get(usersIndex).getUsername())) && 
					(user.getPassword().equals(users.get(usersIndex).getPassword())) ) {
			
				allCompetitions=db.getAllCompetition(); 
				actualCompetitions=db.getTheActualCompetitions(); 
				
				int counter_All=allCompetitions.size(); 
				int counter_Actual=actualCompetitions.size(); 
			 
				
				
				
				
				model.addAttribute("list", actualCompetitions); 
				model.addAttribute("allCounter", counter_All); 
				model.addAttribute("actualCounter", counter_Actual); 
				
				
				returnPage="main.html"; 
				break; 
			}
			
			else {
				
				returnPage="redirect:/"; 
			}
		}
		
	
		return returnPage;
		
	}
}
