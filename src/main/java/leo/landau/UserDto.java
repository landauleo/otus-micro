package leo.landau;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User object")
public class UserDto {
    @Schema(description = "User ID", example = "1")
    private Long id;

    @Schema(description = "Username", example = "johndoe589")
    private String username;

    @Schema(description = "First Name", example = "John")
    private String firstname;

    @Schema(description = "Last Name", example = "Doe")
    private String lastname;

    @Schema(description = "Email", example = "bestjohn@doe.com")
    private String email;

    @Schema(description = "Phone", example = "+71002003040")
    private String phone;

    public UserDto(Long id, String username, String firstname, String lastname, String email, String phone) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
    }

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    static UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getFirstname(), user.getLastname(), user.getEmail(),
                user.getPhone());
    }

}


