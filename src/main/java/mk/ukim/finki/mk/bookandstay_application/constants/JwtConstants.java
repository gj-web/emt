package mk.ukim.finki.mk.bookandstay_application.constants;


public class JwtConstants {
    public static final String SECRET_KEY = "7ac51f34954d17b2c22a1a95ea44f1f61c72a0b87d186c8e2c607edb4db99ffd"; //ova da bide na 256Bytes treba
    public static final Long EXPIRATION_TIME = 864000000L;
    public static final String HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
}