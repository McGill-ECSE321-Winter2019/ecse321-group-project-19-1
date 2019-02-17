package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Employer {
	private Integer employerID;
	private CooperatorManager cooperatorManager;
	private Set<EmployerContract> employerContract;

	public void setEmployerID(Integer value) {
		this.employerID = value;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getEmployerID() {
		return this.employerID;
	}

	@ManyToOne(optional = false)
	public CooperatorManager getCooperatorManager() {
		return this.cooperatorManager;
	}

	public void setCooperatorManager(CooperatorManager cooperatorManager) {
		this.cooperatorManager = cooperatorManager;
	}

	@OneToMany(mappedBy = "employer")
	public Set<EmployerContract> getEmployerContract() {
		return this.employerContract;
	}

	public void setEmployerContract(Set<EmployerContract> employerContracts) {
		this.employerContract = employerContracts;
	}

}
