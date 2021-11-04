package com.administration.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.administration.dto.ProfilDTO;
import com.administration.dto.UserDTO;
import com.administration.exception.ProfilException;
import com.administration.exception.UserException;
import com.administration.mapping.DTOFactory;
import com.administration.mapping.ModelFactory;
import com.administration.model.User;
import com.administration.payload.UpdatePasswordBody;
import com.administration.payload.UserPaylaod;
import com.administration.repository.ProfilRepository;
import com.administration.repository.UserRepository;
import com.administration.service.IProfilService;
import com.administration.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

	 private final String REGEX_IDENTIFIANT = "^(\\d{6})([a-zA-Z]{1})$";

    public final static String REGEX_EMAIL = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
	
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

		if(payload.getIdentifiant() == null || (payload.getIdentifiant() != null && payload.getIdentifiant().equals("")))
			throw new UserException("L'identifiant est obligatoire.");
		
		if(payload.getEmail() == null || (payload.getEmail() != null && payload.getEmail().equals("")))
			throw new UserException("L'email est obligatoire.");
		

		if(payload.getNom() == null || (payload.getNom() != null && payload.getNom().equals("")))
			throw new UserException("Le nom est obligatoire.");
		
		if(payload.getPrenom() == null || (payload.getPrenom() != null && payload.getPrenom().equals("")))
			throw new UserException("Le prenom est obligatoire.");
		
		if(payload.getProfil() == 0 )
			throw new UserException("Le profil est obligatoire.");

		if(payload.getId() == null){
			if(payload.getMotDePasse() == null || (payload.getMotDePasse() != null && payload.getMotDePasse().equals("")))
				throw new UserException("Le mot de passe est obligatoire.");

			if(payload.getConfirmationMotDePasse() == null || (payload.getConfirmationMotDePasse() != null && payload.getConfirmationMotDePasse().equals("")))
				throw new UserException("La confirmation du mot de passe est obligatoire.");

			if(!payload.getMotDePasse().equals(payload.getConfirmationMotDePasse()))
				throw new UserException("Le mot de passe et la confirmation ne sont pas identiques.");

			// validate password

			if(!validatePassword(payload.getMotDePasse()))
				throw new UserException("Le mot de passe et la confirmation ne sont pas identiques.Le mot de passe doit avoir minimum 8 caractères,"
						+ " composé de majuscules, de minuscules, de chiffres et de caractères spéciaux");

			UserDTO exist = findUserByEmail(payload.getEmail());
			if(exist != null)
				throw new UserException("Cet mail " + payload.getEmail() + " est déjà associé à un compte.");
		}

		// validate mail
		if(!Pattern.compile(REGEX_EMAIL).matcher(payload.getEmail()).matches())
			throw new UserException("Le format de l'email est invalide.");


		user = dtoFactory.createUser(payload);
		if (user.getId() == null) {
			try {
				String passwordToEncode = encoder.encode(user.getMotDePasse());
				user.setMotDePasse(passwordToEncode);
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

		User usr = modelFactory.createUser(user);
		user.setId(usr.getId());
		try {
			userRepository.save(usr);
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
		user.setActif(user.getActif() == 1 ? 0 : 1);
		try {
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
		String oldPassword = user.getMotDePasse();
		String newPassword ;

		if(!encoder.matches(updatePasswordBody.getPassword(), user.getMotDePasse())){
			throw new UserException("L'ancien mot de passe n'est pas valide.");
		}

		if((!updatePasswordBody.getNewPassword().trim().equals("") && !updatePasswordBody.getNewPasswordConfirm().trim().equals("")) ){
			if(!updatePasswordBody.getNewPassword().trim().equals(updatePasswordBody.getNewPasswordConfirm().trim())) {
				throw new UserException("Les mots de passe ne sont pas identiques.");
			}
			if(!validatePassword(updatePasswordBody.getNewPassword())){
				throw new UserException("Le nouveau mot de passe n'est pas valide.");
			}
			if(encoder.matches(updatePasswordBody.getNewPassword(), user.getMotDePasse())){
				throw new UserException("L'ancien mot de passe et le nouveau mot de passe sont identiques.");
			}
			newPassword = encoder.encode(updatePasswordBody.getNewPassword());
			user.setMotDePassePrecedent(oldPassword);
			user.setMotDePasse(newPassword);
			user.setMdpModifie(1);
			user.setActif(1);
			userRepository.save(user);
		}
		else{
			throw new UserException("Le mot de passe est obligatoire.");
		}
	}

}
