package com.biblioteka.Biblioteka.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.biblioteka.Biblioteka.dto.JwtResponse;
import com.biblioteka.Biblioteka.dto.LoginDTO;
import com.biblioteka.Biblioteka.dto.PasswordUpdate;
import com.biblioteka.Biblioteka.dto.RegistracijaDTO;
import com.biblioteka.Biblioteka.enumeration.TipRole;
import com.biblioteka.Biblioteka.model.Korisnik;
import com.biblioteka.Biblioteka.model.Role;
import com.biblioteka.Biblioteka.repository.KorisnikRepository;
import com.biblioteka.Biblioteka.security.JwtProvider;

@Service
@Transactional
public class KorisnikService {

	@Autowired
	KorisnikRepository korisnikRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	ThreadPoolTaskExecutor taskExecutor;

	@Autowired
	EmailService emailService;

// dobavi sve
	@Transactional
	public List<Korisnik> findAll() {

		return korisnikRepository.findAllByDeletedIsFalse();
	}

// dobavi odredjenu
	@Transactional
	public Korisnik findOne(Long id) {

		Korisnik korisnik = korisnikRepository.findByIdAndDeletedIsFalse(id);
		if (korisnik != null) {
			return korisnik;
		}

		throw new IllegalArgumentException("Nepostojeci korisnik");
	}

// Dobaavi po emailu
	@Transactional
	Korisnik findByEmail(String email) {

		Korisnik korisnik = korisnikRepository.findByEmailAndDeletedIsFalse(email);
		if (korisnik != null) {
			return korisnik;
		}

		throw new IllegalArgumentException("Nepostojeci korisnik");
	}

// Dobaavi po usernameu
	@Transactional
	Korisnik findByUsername(String username) {

		Korisnik korisnik = korisnikRepository.findByUsernameAndDeletedIsFalse(username);
		if (korisnik != null) {
			return korisnik;
		}

		throw new IllegalArgumentException("Nepostojeci korisnik");
	}

// Dobaavi po linku
	@Transactional
	Korisnik findByLink(String link) {

		Korisnik korisnik = korisnikRepository.findByRegistracioniLink(link);
		if (korisnik != null) {
			return korisnik;
		}

		throw new IllegalArgumentException("Nepostojeci korisnik");
	}

//sacuvaj
	@Transactional
	public Korisnik registracija(RegistracijaDTO dto) {

		if (!isValidEmail(dto.getEmail())) {
			throw new IllegalArgumentException("Neodgovarajuca email adresa");
		}
		if (dto.getPassword().length() < 8) {
			throw new IllegalArgumentException("Lozinka ne sme biti kraca od 8 karaktera");
		}

		List<Korisnik> korisnici = findAll();
		for (Korisnik korisnik : korisnici) {
			if (korisnik.getEmail().equals(dto.getEmail())) {

				throw new IllegalArgumentException("Postojeca email adresa");
			}
			if (korisnik.getUsername().equals(dto.getUsername())) {

				throw new IllegalArgumentException("Postojeci username");
			}
		}

		Korisnik korisnik = new Korisnik();
		korisnik.setEmail(dto.getEmail());
		korisnik.setUsername(dto.getUsername());
		korisnik.setPassword(encoder.encode(dto.getPassword()));
		korisnik.setIme(dto.getIme());
		korisnik.setPrezime(dto.getPrezime());
		korisnik.setAdresa(dto.getAdresa());
		String regLink = "link_" + dto.getUsername();
		korisnik.setRegistracioniLink(regLink);

		Role role = new Role(TipRole.ROLE_KORISNIK);
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		korisnik.setRoles(roles);

		mailSend(dto.getEmail(), regLink);

		return korisnikRepository.save(korisnik);
	}

	@Transactional
	public JwtResponse login(@RequestBody LoginDTO dto) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		JwtResponse response = new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities());

		return response;
	}

// Aktivacija korisnika
	@Transactional
	public boolean aktivirajKorisnika(String registracioniLink) {

		Korisnik korisnik = korisnikRepository.findByRegistracioniLink(registracioniLink);

		if (korisnik != null) {
			korisnik.setAktiviran(true);
			korisnikRepository.save(korisnik);

			return true;
		}

		throw new IllegalArgumentException("Korisnik ne postoji");
	}

// Slanje mejla	
	@Transactional
	private void mailSend(String mailTo, String registracioniLink) {

		String title = "Potvrda naloga";
		String content = "Molimo vas da aktivirate nalog klikom na link:\nhttp://localhost:8080/api/korisnik/activate/"
				+ registracioniLink;
		emailService.setup(mailTo, title, content);
		taskExecutor.execute(emailService);
	}

// Azuriranje korisnika
	@Transactional
	public Korisnik update(Korisnik korisnik) {

		Korisnik noviKorisnik = korisnikRepository.findByIdAndDeletedIsFalse(korisnik.getId());

		if (noviKorisnik == null) {
			throw new IllegalArgumentException("Korisnik ne postoji");
		}
		noviKorisnik.setAdresa(korisnik.getAdresa());
		noviKorisnik.setIme(korisnik.getIme());
		noviKorisnik.setPrezime(korisnik.getPrezime());

		return korisnikRepository.save(noviKorisnik);
	}

	@Transactional
	public boolean updatePassword(PasswordUpdate pu) {

		Korisnik korisnik = korisnikRepository.findByIdAndDeletedIsFalse(pu.getKorisnikId());

		if (korisnik == null) {
			throw new IllegalArgumentException("Korisnik ne postoji");
		}

		if (!encoder.encode(pu.getOldPassword()).equals(korisnik.getPassword())) {
			throw new IllegalArgumentException("Lozinke se ne poklapaju!");
		}

		if (pu.getNewPassword().length() < 8) {
			throw new IllegalArgumentException("Lozinka ne sme biti kraca od 8 karaktera");
		}

		korisnik.setPassword(encoder.encode(pu.getNewPassword()));
		korisnikRepository.save(korisnik);

		return true;
	}

//logicko brisanje
	@Transactional
	public boolean delete(Long id) {

		Korisnik korisnik = findOne(id);
		korisnik.setDeleted(true);
		korisnikRepository.save(korisnik);

		return true;
	}

	public boolean isValidEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pattern = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pattern.matcher(email).matches();
	}

}
