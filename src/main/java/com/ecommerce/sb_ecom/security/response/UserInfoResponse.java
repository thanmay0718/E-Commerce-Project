package com.ecommerce.sb_ecom.security.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"id", "jwtToken", "username", "roles"})
public class UserInfoResponse {
    private Long id;
    private String jwtToken;   // ✅ Moved to 2nd position
    private String username;   // ✅ Moved to 3rd position
    private List<String> roles;

    public UserInfoResponse(Long id, String username, List<String> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        // jwtToken defaults to null
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getJwtToken() { return jwtToken; }
    public void setJwtToken(String jwtToken) { this.jwtToken = jwtToken; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public List<String> getRoles() { return roles; }
    public void setRoles(List<String> roles) { this.roles = roles; }
}