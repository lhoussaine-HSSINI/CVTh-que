package cvthque.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String tel;

//    role {0, 1, 2}
    private int role;
    private String password;
}
