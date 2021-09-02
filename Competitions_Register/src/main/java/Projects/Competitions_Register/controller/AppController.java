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
			@RequestParam (required = false, name="username") String username, 
			@RequestParam (required = false, name="password") String password) {
		
		String returnPage=null; 
		
		Database db=new Database(); 
		
		List<User> users=db.getUsers();  
		
		List<Competition> allCompetitions=null; 
		ArrayList<Competition> actualCompetitions=new ArrayList<Competition>(); 
		int allFeedbackCounter=0; 
		int feedback_Ratio=0; 
		int actualCounter=0; 
		int actualActiveCounter=0; 
		
		User user=new User (username, password); 
		
		for (int usersIndex=0; usersIndex<users.size(); usersIndex++) {
			
			if ( (user.getUsername().equals(users.get(usersIndex).getUsername())) && 
					(user.getPassword().equals(users.get(usersIndex).getPassword())) ) {
				
				allCompetitions=db.getAllCompetition(); 
				int allSize=allCompetitions.size(); 
				
				if (allCompetitions.isEmpty()==false) {
		
					for (int allCompIndex=0; allCompIndex<allSize; allCompIndex++) {
					
					if (allCompetitions.get(allCompIndex).isFeedback()==true) {
						allFeedbackCounter++;
					}
					
					if (allCompetitions.get(allCompIndex).getStatus()!=Status.DELETED) {
						Competition current=allCompetitions.get(allCompIndex); 
						actualCompetitions.add(current); 
						if (current.getStatus()==Status.ACTIVE) {
							actualActiveCounter++; 
						}
					}
				}
				
				actualCounter=actualCompetitions.size(); 
				feedback_Ratio= (allFeedbackCounter/allSize)*100; 
				
				model.addAttribute("allCounter", allSize);
				model.addAttribute("feedbackCounter",allFeedbackCounter ); 
				model.addAttribute("feedbackRatio", feedback_Ratio ); 
				
				model.addAttribute("list", actualCompetitions); 
				
				model.addAttribute("actualCounter", actualCounter); 
				model.addAttribute("activeCounter", actualActiveCounter); 
				
				
				returnPage="main.html"; 
				break; 
					
				}
				
				else {
					
					returnPage="noData.html"; 
					break; 
				}
				
			}
			
			else {
				
				returnPage="redirect:/"; 
			}
		}
	
		return returnPage;
		
	}
	
	@GetMapping("/add")
	public String add () {
		
		return "add.html";
		
	}
	
	@PostMapping("/insert")
	public String insert(Model model) {
		
		
		
		
		return "insert.html";
		
	}
}
