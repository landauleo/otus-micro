package leo.landau;

import jakarta.inject.Singleton;
import org.mindrot.jbcrypt.BCrypt;


@Singleton
public class BCryptPasswordEncoder {

    public String encode(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}