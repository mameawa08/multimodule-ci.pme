package com.administration.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import com.administration.dto.DemandeScoringDTO;
import com.administration.exception.ScoringConnectException;
import com.administration.model.Profil;
import com.administration.payload.ConfirmationPayload;
import com.administration.repository.ProfilRepository;
import com.administration.service.IMailService;
import com.administration.service.IScoringConnectService;
import com.administration.utils.Constante;
import com.administration.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.administration.dto.ProfilDTO;
import com.administration.dto.UserDTO;
import com.administration.exception.ProfilException;
import com.administration.exception.UserException;
import com.administration.mapping.DTOFactory;
import com.administration.mapping.ModelFactory;
import com.administration.model.User;
import com.administration.payload.UpdatePasswordBody;
import com.administration.payload.UserPaylaod;
import com.administration.repository.UserRepository;
import com.administration.service.IProfilService;
import com.administration.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private IProfilService profilService;

	@Autowired
	private DTOFactory dtoFactory;

	@Autowired
	private ModelFactory modelFactory;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private IScoringConnectService scoringConnectService;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private IMailService mailService;

	@Autowired
	private Environment environment;

	 private final String REGEX_IDENTIFIANT = "^(\\d{6})([a-zA-Z]{1})$";

    public final static String REGEX_EMAIL = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";

    @Autowired
    private ProfilRepository profilRepository;

	@Override
	public List<UserDTO> getUsers() throws UserException{
		List<UserDTO> users = new ArrayList<UserDTO>();
		List<User> list = userRepository.findAll();
		users = dtoFactory.createListUser(list);

		return users;
	}

	@Override
	public UserDTO getUser(Long id) throws UserException{
		User user = userRepository.findById(id).orElseThrow(() -> new UserException("User :: "+id+" not found."));
		UserDTO userDTO = dtoFactory.createUser(user);
		return userDTO;
	}

	@Override
	public UserDTO createUser(UserPaylaod payload) throws UserException{
		UserDTO user;
		if (payload == null)
			throw new UserException("User :: aucune donnee a ajouter.");

		if(payload.getId() != null){
			user = getUser(payload.getId());
		}
		else {
			user = new UserDTO();
		}

		validatePayload(payload);

		if(payload.getProfil() == 0 )
			throw new UserException("Le profil est obligatoire.");

		user = dtoFactory.createUser(payload, user);
		user = createUser(user, payload);
		try {
			User usr = modelFactory.createUser(user);
			userRepository.save(usr);
			if(user.getId() == null){
				mailService.sendRegistrationMail(user, payload.getPassword());
				user.setId(usr.getId());
			}
			else {
				mailService.sendUpdateUserMail(user);
			}
		} catch (Exception e) {
			throw new UserException("User :: Impossible de creer l'utilisateur : "+e.getMessage(), e);
		}
		return user;

	}

	@Override
	public UserDTO findUserByEmail(String email){
		User user = userRepository.findByEmail(email).orElse(null);
		return dtoFactory.createUser(user);
	}

	@Override
	public boolean switchStatus(Long id) throws UserException{
		User user = userRepository.findById(id).orElseThrow(() -> new UserException("User :: "+id+" not found."));
		try {
			if(user.getProfil().getId().equals(Constante.ROLE_ENTR)){
				DemandeScoringDTO demande = scoringConnectService.getUserDemande(user.getId());
				if((demande != null && demande.getStatus() > 1)){
					throw new UserException("Impossible de désactiver l'utilisateur :: une demande de scoring est en cours.");
				}
			}
			user.setActif(user.getActif() == 1 ? 0 : 1);
			userRepository.save(user);
			return true;
		} catch (Exception e) {
			throw new  UserException(e.getMessage());
		}
	}

	//Private methodes
	@Override
    public boolean validatePassword(String password) {
        int min = 8;
        int max = 16;
        int digit = 0;
        int special = 0;
        int upCount = 0;
        int loCount = 0;
        if (password.length() >= min && password.length() <= max) {
            for (int i = 0; i < password.length(); i++) {
                char c = password.charAt(i);
                if (Character.isUpperCase(c)) {
                    upCount++;
                }
                if (Character.isLowerCase(c)) {
                    loCount++;
                }
                if (Character.isDigit(c)) {
                    digit++;
                }
                if (c >= 33 && c <= 46 || c == 64) {
                    special++;
                }
            }
            if (special >= 1 && loCount >= 1 && upCount >= 1 && digit >= 1) {
                return true;
            }
        }
        return false;
    }

	@Override
	public void updatePassword(Long id, UpdatePasswordBody updatePasswordBody) throws UserException {
		User user = userRepository.findById(id).orElseThrow(()-> new UserException("User not found."));
		String oldPassword = user.getPassword();
		String newPassword ;

		if(!encoder.matches(updatePasswordBody.getPassword(), user.getPassword())){
			throw new UserException("L'ancien mot de passe n'est pas valide.");
		}

		if((!updatePasswordBody.getNewPassword().trim().equals("") && !updatePasswordBody.getNewPasswordConfirm().trim().equals("")) ){
			if(!updatePasswordBody.getNewPassword().trim().equals(updatePasswordBody.getNewPasswordConfirm().trim())) {
				throw new UserException("Les mots de passe ne sont pas identiques.");
			}
			if(!validatePassword(updatePasswordBody.getNewPassword())){
				throw new UserException("Le nouveau mot de passe n'est pas valide.");
			}
			if(encoder.matches(updatePasswordBody.getNewPassword(), user.getPassword())){
				throw new UserException("L'ancien mot de passe et le nouveau mot de passe sont identiques.");
			}
			newPassword = encoder.encode(updatePasswordBody.getNewPassword());
			user.setMotDePassePrecedent(oldPassword);
			user.setPassword(newPassword);
			user.setMdpModifie(1);
			user.setActif(1);
			userRepository.save(user);
		}
		else{
			throw new UserException("Le mot de passe est obligatoire.");
		}
	}

	@Override
	public UserDTO register(UserPaylaod paylaod) throws UserException {
		UserDTO user = null;
		checkRegistrationRequirement(paylaod);
		user = dtoFactory.createUser(paylaod);

		user.setMobile(paylaod.getMobile());
		user.setFonction(paylaod.getFonction());
//		user.setEntrepriseLibelle(paylaod.getEntrepriseLibelle());

		paylaod.setProfil(Constante.ROLE_ENTREPRENEUR);
//		sent confirmation mail
		String confirmationUrl = "";
		try {
			String token = jwtUtils.generateJwtToken(user.getUsername(), 1_800_000);
			user.setConfirmationToken(token);
			confirmationUrl = getConfirmationUrl(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		user = createUser(user, paylaod);
		try {
			User usr = modelFactory.createUser(user);
			userRepository.save(usr);
			user.setId(usr.getId());
			mailService.sendActivationMail(user, confirmationUrl);
		} catch (Exception e) {
			throw new UserException("User :: Impossible de creer l'utilisateur : "+e.getMessage(), e);
		}
		return user;
	}

	@Override
	public UserDTO addEntrepriseToUser(Long id, int entrepriseId) throws UserException{
		UserDTO userDTO = null;
		User user = userRepository.findById(id).orElseThrow(() -> new UserException("User :: not found."));
		try {
			Long idEntreprise = scoringConnectService.getEntreprise((long) entrepriseId);
			user.setEntrepriseId(idEntreprise);

			System.out.println("User ID :: "+id);

			userRepository.saveAndFlush(user);
			userDTO = dtoFactory.createUser(user);
		} catch (ScoringConnectException e) {
			throw new UserException("L'entreprise id "+entrepriseId+" n'existe pas.");
		}
		return userDTO;
	}

	@Override
	public int confirm(String token) throws UserException {
		User user = userRepository.findByConfirmationToken(token).orElse(null);
		if (user == null){
			String username = jwtUtils.getUserNameFromJwtToken(token);
			user = userRepository.findByEmailOrUsername(username, username).get();
		}

		if (user.getConfirme() == 1){
			return Constante.CONFIRMED_ACCOUNT;
		}
		try {
		    if(jwtUtils.validateJwtToken(token) && jwtUtils.getUserNameFromJwtToken(token).equals(user.getUsername())){
			    user.setConfirmationToken(null);
			    user.setConfirme(1);
			    user.setActif(1);
			    userRepository.save(user);
            }
		}
		catch (Exception e){
			throw new UserException("Confirmation :: "+e.getMessage());
		}
		return Constante.CONFIRMING_ACCOUNT;
	}

	@Override
	public boolean sendConfirmationToken(ConfirmationPayload payload) throws UserException{
		User user = userRepository.findByEmailAndConfirmationToken(payload.getEmail(), payload.getToken()).orElseThrow(() -> new UserException("Confirmation :: user not found."));
		String email = jwtUtils.getUserNameFromJwtToken(payload.getToken());
		try {
			if (email.equals(user.getEmail())){
				String confirmationToken = jwtUtils.generateJwtToken(email, 1_800_000);
				String confirmationUrl = getConfirmationUrl(confirmationToken);

				user.setConfirmationToken(confirmationToken);
				userRepository.saveAndFlush(user);
				UserDTO userDTO = dtoFactory.createUser(user);
				mailService.sendActivationMail(userDTO, confirmationUrl);
				return true;
			}
			return false;
		}
		catch (Exception e){
			throw new UserException(e.getMessage(), e);
		}
	}

    @Override
    public List<UserDTO> getUsersByProfil(Long idProfil) throws UserException {
		Profil profil = profilRepository.findById(idProfil).orElseThrow(() -> new UserException("Profil :: "+idProfil+" not found"));
		List<User> users = userRepository.findByProfil(profil);
        return dtoFactory.createListUser(users);
    }

    @Override
	public String getFieldName(String message){
		String field = "";
		if(message.contains("username")){field = "Username";}
		if(message.contains("email")){field = "Email";}
		if(message.contains("nom")){field = "Nom";}
		if(message.contains("prenom")){field = "Prenom";}
		if(message.contains("mot de passe")){field = "Password";}
		if(message.contains("confirmation du mot de passe")){field = "ConfirmationPassword";}
		if(message.contains("mot de passe et la confirmation")){field = "ConfirmationPassword";}
		if(message.contains("format")){field = "Email";}
		return field;
	}
    // Private methods
	private void checkRegistrationRequirement(UserPaylaod payload) throws UserException{
		validatePayload(payload);

		if((payload.getMobile() != null && payload.getMobile().equals("")))
			throw new UserException("Le numero de telephone est obligatoire.");

//		if(payload.getEntrepriseLibelle() == null || (payload.getEntrepriseLibelle() != null && payload.getEntrepriseLibelle().equals("")))
//			throw new UserException("Le nom de l'entreprise est obligatoire.");

		if(payload.getFonction()  == null || (payload.getFonction()  != null && payload.getFonction() .equals("")))
			throw new UserException("La fonction occupe dans l'entreprise est obligatoire.");

	}

	private UserDTO createUser(UserDTO user, UserPaylaod payload) throws UserException {
		if (user.getId() == null) {
			try {
				String passwordToEncode = encoder.encode(user.getPassword());
				user.setPassword(passwordToEncode);
			} catch (Exception uee) {
				throw new UserException("Erreur init password ");
			}
		}

		ProfilDTO profil;
		try {
			profil = profilService.getProfil((long) payload.getProfil());
			user.setProfil(profil);
		} catch (ProfilException e) {
			throw new UserException("Le profil est obligatoire.");
		}

//		User usr = modelFactory.createUser(user);
//		try {
//			userRepository.save(usr);
//			user.setId(usr.getId());
//		} catch (Exception e) {
//			throw new UserException("User :: Impossible de creer l'utilisateur : "+e.getMessage(), e);
//		}

		return user;
	}

	private void validatePayload(UserPaylaod payload) throws UserException {
		UserDTO user;
		if(payload.getUsername() == null || (payload.getUsername() != null && payload.getUsername().equals("")))
			throw new UserException("L'username est obligatoire.");

		if(payload.getEmail() == null || (payload.getEmail() != null && payload.getEmail().equals("")))
			throw new UserException("L'email est obligatoire.");


		if(payload.getNom() == null || (payload.getNom() != null && payload.getNom().equals("")))
			throw new UserException("Le nom est obligatoire.");

		if(payload.getPrenom() == null || (payload.getPrenom() != null && payload.getPrenom().equals("")))
			throw new UserException("Le prenom est obligatoire.");

		if(payload.getId() == null){
			if(payload.getPassword() == null || (payload.getPassword() != null && payload.getPassword().equals("")))
				throw new UserException("Le mot de passe est obligatoire.");

			if(payload.getConfirmationPassword() == null || (payload.getConfirmationPassword() != null && payload.getConfirmationPassword().equals("")))
				throw new UserException("La confirmation du mot de passe est obligatoire.");

			if(!payload.getPassword().equals(payload.getConfirmationPassword()))
				throw new UserException("Le mot de passe et la confirmation ne sont pas identiques.");

			// validate password

			if(!validatePassword(payload.getPassword()))
				throw new UserException("Le mot de passe et la confirmation ne sont pas identiques.Le mot de passe doit avoir minimum 8 caractères,"
						+ " composé de majuscules, de minuscules, de chiffres et de caractères spéciaux");
		}

		// validate mail
		validateEmail(payload.getEmail(), payload.getId());

	}

	private void validateEmail(String email, Long id) throws UserException {
		if(id == null){
			Optional<User> user = userRepository.findByEmail(email);
			if(user.isPresent()){
				throw new UserException("L'email <"+email+"> est déjà associé à un compte.");
			}
		}
		else{
			User user = userRepository.findByEmail(email).orElse(null);
			if (user != null && !user.getId().equals(id)){
				throw new UserException("L'email <"+email+"> est déjà associé à un compte.");
			}
		}

		if(!Pattern.compile(REGEX_EMAIL).matcher(email).matches())
			throw new UserException("Le format de l'email est invalide.");
	}

	private String getConfirmationUrl(String token) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://").append(environment.getProperty("front.host"))
				.append("/").append(environment.getProperty("front.context")).append("/")
				.append("account").append("/")
				.append("confirm")
				.append("?token=")
				.append(token);
		return sb.toString();
	}

}
