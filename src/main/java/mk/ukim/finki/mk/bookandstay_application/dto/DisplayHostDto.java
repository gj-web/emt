package mk.ukim.finki.mk.bookandstay_application.dto;

import mk.ukim.finki.mk.bookandstay_application.model.domain.Country;
import mk.ukim.finki.mk.bookandstay_application.model.domain.Host;

import java.util.List;
import java.util.stream.Collectors;


public record DisplayHostDto (
        String name,
        String surname,
        Long country
){
  public static DisplayHostDto from(Host host){
      return new DisplayHostDto(

              host.getName(),
              host.getSurname(),
              host.getCountry().getId()

      );}

    public static List<DisplayHostDto> from(List<Host> hots) {
        return hots.stream().map(DisplayHostDto::from).collect(Collectors.toList());
    }

    public Host toHost(Country country) {
        return new Host(name,country,surname);
    }
}
