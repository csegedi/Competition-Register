package Projects.Competitions_Register.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="all_competitions")
public class Competition {

	@Id
	@Column (name="id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (name="firm")
	private String firm; 
	
	@Column (name="date")
	private Date date;
	
	@Column (name="technologies")
	private String technologies; 
	
	@Column (name="language")
	@Enumerated(EnumType.STRING)
	private Language_Level language; 
	
	@Column (name="status")
	@Enumerated(EnumType.STRING)
	private Status status; 
	
	@Column (name="round")
	@Enumerated(EnumType.STRING)
	private Round round; 
	
	@Column (name="round_type")
	@Enumerated(EnumType.STRING)
	private Round_Type round_type; 
	
	@Column (name="descritption")
	private String description; 
	
	@Column(name="feedback")
	private boolean feedback; 
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirm() {
		return firm;
	}

	public void setFirm(String firm) {
		this.firm = firm;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTechnologies() {
		return technologies;
	}

	public void setTechnologies(String technologies) {
		this.technologies = technologies;
	}

	public Language_Level getLanguage() {
		return language;
	}

	public void setLanguage(Language_Level language) {
		this.language = language;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Round getRound() {
		return round;
	}

	public void setRound(Round round) {
		this.round = round;
	}

	public Round_Type getRound_type() {
		return round_type;
	}

	public void setRound_type(Round_Type round_type) {
		this.round_type = round_type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFeedback() {
		return feedback;
	}

	public void setFeedback(boolean feedback) {
		this.feedback = feedback;
	}
	
	
	

}
