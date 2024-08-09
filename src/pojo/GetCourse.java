package pojo;

public class GetCourse {
	
	private String instructor;
	private String url;
	private String services;
	private String expertise;
	
	// since courses has nested json and inside that also nested json is there webAutomation, API and Mobile
	private Courses courses;
	private String linkedIn;
	
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	
	// Return type will be Courses here
	public Courses getCourses() {
		return courses;
	}
	
	// Return type will be Courses here
	public void setCourses(Courses courses) {
		this.courses = courses;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	
	

}
