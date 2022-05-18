package Projects.Competitions_Register.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
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
	public String index (HttpServletRequest request,
			HttpServletResponse response) {
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
			}

		}
		
		return "index.html";
		
	}
	
	@PostMapping ("/mainPage")
	public String mainPage (Model model, 
			@RequestParam (required = false, name="username") String username, 
			@RequestParam (required=false, name="password") String password,
			@CookieValue (required =false, value="cookie_user") String cookie_name,
			@CookieValue (required =false, value="cookie_password") String cookie_password,
			HttpServletResponse response
			) {
		
		String returnPage=null; 
		
		Database db=new Database(); 
		
		List<User> users=db.getUsers();  
		
		List<Competition> allCompetitions=null; 
		ArrayList<Competition> actualCompetitions=new ArrayList<Competition>(); 
		
		double allFeedbackCounter=0; 
		double feedback_Ratio=0; 
		int actualCounter=0; 
		int actualActiveCounter=0; 
		
		User user=null; 
		
		if (username!=null && password!=null) {
			 user=new User (username, password); 
			 Cookie cookie1=new Cookie("cookie_user", username);
			 Cookie cookie2=new Cookie ("cookie_password", password); 
			 response.addCookie(cookie1); 
			 response.addCookie(cookie2);  
			
		}
		
		else {
			
			user=new User (cookie_name, cookie_password); 
		 
		}
		
		for (int usersIndex=0; usersIndex<users.size(); usersIndex++) {
			
			if ( (user.getUsername().equals(users.get(usersIndex).getUsername())) && 
					(user.getPassword().equals(users.get(usersIndex).getPassword())) ) {
				
				allCompetitions=db.getAllCompetition(); 
				double allSize=allCompetitions.size(); 
				
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
				feedback_Ratio= allFeedbackCounter/allSize; 
			
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
		
		db.close(); 
		
		return returnPage;
		
	}
	
	@PostMapping("/mainPage/add")
	public String add () {
	
		return "add.html";
		
	}
	
	@PostMapping("/mainPage/add/completed")
	public String insert(Model model,
			@RequestParam (required = false, name="firm") String firm,
			@RequestParam (required = false, name="date")@DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			@RequestParam (required = false, name="technologies") String technologies,
			@RequestParam (name="language") String language,
			@RequestParam (name="round") String round,
			@RequestParam (name="round_type") String round_type,
			@RequestParam (name="status") String status,
			@RequestParam (required = false, name="feedback") Integer feedback,
			@RequestParam (required = false, name="description") String description) {
		
		Database db=new Database(); 
		String returnPage=null; 
		
		if ( (firm!=null) && (date!=null) && (technologies!=null) && ( (feedback!=null) && ( (feedback==0) || (feedback==1) ) ) && description!=null ) {
			db.insertNewCompetition(firm, date, technologies, language, round, round_type,  status,  feedback, description  ); 
			returnPage="additionCompleted.html"; 
		}
		else {
			returnPage="wrongInput.html"; 
		}
		
		
		db.close(); 
		
		return returnPage;
		
	}
	
	@PostMapping ("/mainPage/update")
	public String update (Model model,
			@RequestParam (name="id") int id,
			HttpServletResponse response) {
		
		String cookieValue=String.valueOf(id); 
		 
		Cookie cookie=new Cookie ("id", cookieValue); 
		response.addCookie (cookie); 
		
		Database db=new Database(); 
		Competition comp=db.getCompetitionById(id); 
	
		model.addAttribute("competition",comp); 
		
		db.close(); 
		
		return "update.html";
			
	}
	
	@PostMapping("/mainPage/update/completed")
	public String updateCompleted(Model model,
			@CookieValue (value="id") String id,
			@RequestParam (required =false, name="round") String round,
			@RequestParam (required =false, name="round_type") String round_type,
			@RequestParam (required =false, name="status") String status,
			@RequestParam (required =false, name="feedback") Integer feedback,
			@RequestParam (required =false, name="description") String description) {
		
		Database db=new Database(); 
		
		Cookie cookie=new Cookie ("id", id); 
		String input=cookie.getValue(); 
		int formatted_id=Integer.valueOf(input);
		String text="Updated information: "; 
		
		String info=null; 
	
		if (round!=null) {
			db.updateRound(formatted_id, round); 
			info=text.concat(round); 
			
		}
		
		else if (round_type!=null) {
			db.updateRound_type(formatted_id, round_type); 
			info=text.concat(round_type); 
		}
		
		else if (status!=null) {
			db.updateStatus(formatted_id, status); 
			info=text.concat(status); 
		}
		else if( feedback!=null) {
			db.updateFeedback(formatted_id, feedback);
			if (feedback==1) {
				info=text.concat("true");
			}
			else if(feedback==0) {
				info=text.concat("false"); 
			}
			else {
				info="NO CHANGES! Check the input field!";
			}
		}
		else if (description!=null) {
			db.updateDescription(formatted_id, description); 
			info=text.concat(description); 
		}
		
		Competition comp=db.getCompetitionById(formatted_id); 
		
		model.addAttribute("info", info); 
		model.addAttribute("competition", comp); 
		
		db.close(); 
		
		return "updateCompleted.html";
		
	}
}
