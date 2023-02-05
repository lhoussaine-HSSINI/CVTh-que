package cvthque.auth;

import cvthque.config.JwtService;
import cvthque.model.Entity.Role;
import cvthque.model.Entity.User;
import cvthque.model.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

//

    public AuthenticationResponse register_APPRENANATE(RegisterRequest request) {
        var user = User.builder()
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .email(request.getEmail())
                .tel(request.getTel())
                .adresse(request.getAdresse())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.APPRENANATE)
                .build();
        repository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .is_login(true)
                .role(user.getRole())
                .build();
    }

    public AuthenticationResponse register_FORMATEUR(RegisterRequest request) {
        var user = User.builder()
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .email(request.getEmail())
                .tel(request.getTel())
                .adresse(request.getAdresse())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.FORMATEUR)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .is_login(true)
                .role(user.getRole())
                .build();
    }

    public AuthenticationResponse register_CME(RegisterRequest request) {
        var user = User.builder()
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .email(request.getEmail())
                .tel(request.getTel())
                .adresse(request.getAdresse())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CME)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .is_login(true)
                .role(user.getRole())
                .build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .is_login(true)
                .role(user.getRole())
                .build();
    }
}
