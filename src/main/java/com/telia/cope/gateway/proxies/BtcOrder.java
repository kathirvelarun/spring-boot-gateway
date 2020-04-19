package com.telia.cope.gateway.proxies;

public class BtcOrder {

    public BtcOrder() {

    }

    public BtcOrder(Integer id, String firstName, String lastName, String email, String processType) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.processType = processType;
    }
 
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String processType;
    
    

    public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", processType=\" + processType + ]";
    }
}
