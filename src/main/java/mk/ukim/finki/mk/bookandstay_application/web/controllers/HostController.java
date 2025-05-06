package mk.ukim.finki.mk.bookandstay_application.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.mk.bookandstay_application.dto.CreateHostDto;
import mk.ukim.finki.mk.bookandstay_application.dto.DisplayHostDto;
import mk.ukim.finki.mk.bookandstay_application.model.projections.HostProjection;
import mk.ukim.finki.mk.bookandstay_application.model.views.HostsPerCountryView;
import mk.ukim.finki.mk.bookandstay_application.repository.HostRepository;
import mk.ukim.finki.mk.bookandstay_application.repository.HostsPerCountryViewRepository;
import mk.ukim.finki.mk.bookandstay_application.service.application.HostApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
@Tag(name = "Hosts", description = "Hosts API")
public class HostController {
    private final HostApplicationService hostApplicationService;
    private final HostsPerCountryViewRepository hostsPerCountryViewRepository;

    private final HostRepository hostRepository;

    public HostController(HostApplicationService hostApplicationService, HostsPerCountryViewRepository hostsPerCountryViewRepository, HostRepository hostRepository) {
        this.hostApplicationService = hostApplicationService;
        this.hostsPerCountryViewRepository = hostsPerCountryViewRepository;
        this.hostRepository = hostRepository;
    }


    @GetMapping
    @Operation(summary = "List all of the hosts")
    public List<DisplayHostDto> findAll() {
        return this.hostApplicationService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a host by an ID")
    public ResponseEntity<DisplayHostDto> findById(@PathVariable Long id) {
        return this.hostApplicationService.findById(id)
                .map(m -> ResponseEntity.ok().body(m))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Add a host")
    public ResponseEntity<DisplayHostDto> save(@RequestBody CreateHostDto host) {
        return this.hostApplicationService.save(host)
                .map(m -> ResponseEntity.ok().body(m))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Update a host")
    public ResponseEntity<DisplayHostDto> update(@PathVariable Long id, @RequestBody CreateHostDto host) {
        return this.hostApplicationService.update(id, host)
                .map(m -> ResponseEntity.ok().body(m))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a host by an ID")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (this.hostApplicationService.findById(id).isPresent()) {
            this.hostApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-country")
    public List<HostsPerCountryView> getHostsByCountry() {
        return hostsPerCountryViewRepository.findAll();
    }

    @GetMapping("/names")
    public List<HostProjection> getHostNames() {
        return hostRepository.findAllBy();
    }
}
