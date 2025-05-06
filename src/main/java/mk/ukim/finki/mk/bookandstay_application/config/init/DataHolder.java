package mk.ukim.finki.mk.bookandstay_application.config.init;

import jakarta.annotation.PostConstruct;

import mk.ukim.finki.mk.bookandstay_application.model.domain.Country;
import mk.ukim.finki.mk.bookandstay_application.model.domain.Host;
import mk.ukim.finki.mk.bookandstay_application.model.domain.Housing;
import mk.ukim.finki.mk.bookandstay_application.model.domain.User;
import mk.ukim.finki.mk.bookandstay_application.model.enumerations.Category;
import mk.ukim.finki.mk.bookandstay_application.model.enumerations.Role;
import mk.ukim.finki.mk.bookandstay_application.repository.CountryRepository;
import mk.ukim.finki.mk.bookandstay_application.repository.HostRepository;
import mk.ukim.finki.mk.bookandstay_application.repository.HousingRepository;
import mk.ukim.finki.mk.bookandstay_application.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Country> countries = null;
    public static List<Host> hosts = null;
    public static List<Housing> housings = null;

    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final HousingRepository housingRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataHolder(CountryRepository countryRepository, HostRepository hostRepository, HousingRepository housingRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.housingRepository = housingRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        // Countries
        countries = new ArrayList<>();
        if (this.countryRepository.count() == 0) {
            countries.add(new Country("Spain", "Europe"));
            countries.add(new Country("Canada", "North America"));
            countries.add(new Country("Australia", "Oceania"));
            countries.add(new Country("India", "Asia"));
            countries.add(new Country("South Africa", "Africa"));
            countries.add(new Country("Cape Cod", "North America"));

            this.countryRepository.saveAll(countries);
        }

        // Hosts
        hosts = new ArrayList<>();
        if (this.hostRepository.count() == 0) {
            hosts.add(new Host("Maria",countries.get(0),"Garcia"));
            hosts.add(new Host("Liam",  countries.get(1),"Smith"));
            hosts.add(new Host("Chloe", countries.get(2), "Brown"));
            hosts.add(new Host("Raj",  countries.get(3),"Kumar"));
            hosts.add(new Host("Zanele", countries.get(4), "Mokoena"));
            this.hostRepository.saveAll(hosts);
        }

        // Housings
        housings = new ArrayList<>();
        if (this.housingRepository.count() == 0) {
            housings.add(new Housing("Seaside Villa", Category.HOUSE, hosts.get(0),5));
            housings.add(new Housing("Downtown Loft",Category.FLAT, hosts.get(1),2));
            housings.add(new Housing("Country Cabin", Category.ROOM, hosts.get(2), 3));
            housings.add(new Housing("Urban Studio", Category.APARTMENT, hosts.get(3), 1));
            housings.add(new Housing("Safari Lodge", Category.HOTEL, hosts.get(4), 6));
            this.housingRepository.saveAll(housings);
        }
        userRepository.save(new User(
                "gj.eva",
                passwordEncoder.encode("gj.eva"),
                "Eva",
                "Gjorgjova",
                Role.ROLE_ADMIN
        ));

        userRepository.save(new User(
                "daci",
                passwordEncoder.encode("daci"),
                "Daci",
                "Minova",
                Role.ROLE_USER
        ));
    }
}
