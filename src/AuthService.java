public class AuthService {
    private AuthService() {
    }
    public static boolean auth (String user, String password) {
        return (user.equals("Administrador") && password.equals("Administrador")) ||
                (user.equals("Adm") && password.equals("Adm")) ||
                (user.equals("Administrator") && password.equals("pr4frentef0rever"));
    }
}
