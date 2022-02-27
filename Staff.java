package CZ2002_Assignment;

import java.util.Scanner;
/**
* Entity Class for Staff in the Restaurant
* Stores information on staff particulars
*/
public class Staff {
	private String name, gender, jobTitle;
	private int emp_id;
	/**
	*Constructor for Class
	* @param name Name of Staff
	* @param gender Gender of Staff
	* @param jobTitle Job Title of Staff
	* @param emp_id Staff's employee ID
	*/
	public Staff(String name, String gender, String jobTitle, int emp_id) {
		super();
		this.name = name;
		this.gender = gender;
		this.jobTitle = jobTitle;
		this.emp_id = emp_id;
	}
	
	/**
	* Returns the name of the Staff
	*/
	public String getName() {
		return name;
	}
	
	/**
	* Returns the gender of the Staff
	*/
	public String getGender() {
		return gender;
	}
	
	/**
	* Returns the job title of the Staff
	*/
	public String getJobTitle() {
		return jobTitle;
	}
	
	/**
	* Returns the employee id of the Staff
	*/
	public int getEmp_id() {
		return emp_id;
	}
	
	/**
	* Sets the name of the Staff to the input
	* @para name name of Staff 
	*/
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	* Sets the gender of the Staff to the input
	* @para name gender of Staff 
	*/
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	* Sets the job title of the Staff to the input
	* @para jobTitle job title of Staff 
	*/
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	/**
	* Sets the employee id of the Staff to the input
	* @para emp_id employee id of Staff 
	*/
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	
}
