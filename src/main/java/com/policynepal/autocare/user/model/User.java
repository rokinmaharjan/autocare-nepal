package com.policynepal.autocare.user.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.Collections;

@Document(collection = "users")
public class User {
	@Id
	private String id;
	private String firstname;
	private String lastname;
	@Indexed
	private String username;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private List<String> roles;
	private String address;
	private String email;
	private Boolean active = false;
	private Organization organization;
	@JsonProperty(access = Access.READ_ONLY)
	@CreatedDate
	private Date createdDate;
	@JsonProperty(access = Access.READ_ONLY)
	@LastModifiedDate
	private Date modifiedDate;

	public User() {
		super();
	}

	private User(Builder builder) {
		this.id = builder.id;
		this.firstname = builder.firstname;
		this.lastname = builder.lastname;
		this.username = builder.username;
		this.password = builder.password;
		this.roles = builder.roles;
		this.address = builder.address;
		this.email = builder.email;
		this.active = builder.active;
		this.organization = builder.organization;
		this.createdDate = builder.createdDate;
		this.modifiedDate = builder.modifiedDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ ", password=" + password + ", roles=" + roles + ", address=" + address + ", email=" + email
				+ ", active=" + active + ", organization=" + organization + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String id;
		private String firstname;
		private String lastname;
		private String username;
		private String password;
		private List<String> roles = Collections.emptyList();
		private String address;
		private String email;
		private Boolean active;
		private Organization organization;
		private Date createdDate;
		private Date modifiedDate;

		private Builder() {
		}

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder firstname(String firstname) {
			this.firstname = firstname;
			return this;
		}

		public Builder lastname(String lastname) {
			this.lastname = lastname;
			return this;
		}

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder roles(List<String> roles) {
			this.roles = roles;
			return this;
		}

		public Builder address(String address) {
			this.address = address;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder active(Boolean active) {
			this.active = active;
			return this;
		}

		public Builder organization(Organization organization) {
			this.organization = organization;
			return this;
		}

		public Builder createdDate(Date createdDate) {
			this.createdDate = createdDate;
			return this;
		}

		public Builder modifiedDate(Date modifiedDate) {
			this.modifiedDate = modifiedDate;
			return this;
		}

		public User build() {
			return new User(this);
		}
	}

}
