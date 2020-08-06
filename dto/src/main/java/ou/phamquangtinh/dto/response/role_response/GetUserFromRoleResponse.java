package ou.phamquangtinh.dto.response.role_response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class GetUserFromRoleResponse {
    private Long id;
    private String code;
    private String name;

    private List<user> UserList;

    public static class user {
        private Long id;
        private String username;
        private String firstName;
        private String phone;
        private String email;

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

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public user(Long id, String username, String firstName, String phone, String email) {
            this.id = id;
            this.username = username;
            this.firstName = firstName;
            this.phone = phone;
            this.email = email;

        }
    }
}
