package com.administration.controller;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.UnrecoverableKeyException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.security.auth.callback.ConfirmationCallback;
import javax.servlet.http.HttpServletRequest;

import com.administration.dto.FieldValidationError;
import com.administration.dto.UserDTO;
import com.administration.exception.UserException;
import com.administration.mapping.DTOFactory;
import com.administration.model.User;
import com.administration.payload.*;
import com.administration.repository.UserRepository;
import com.administration.service.IMailService;
import com.administration.service.IUserService;
import com.administration.utils.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import freemarker.template.TemplateException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
    private UserRepository userRepository;

	@Autowired
    private Environment environment;

	@Autowired
    private DTOFactory dtoFactory;

	@Autowired
    private IMailService mailService;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
    PasswordEncoder encoder;

	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Autowired
    private IUserService userServcie;

	@PostMapping(value="/forgot_password")
    // @Operation(summary = "Send reset password mail", description = "Send a link to reset a password", tags = {"auth"})
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotBody forgotBody,  HttpServletRequest request) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
        try {
            User user = userRepository.findByEmailOrUsername(forgotBody.getEmailOrUsername(), forgotBody.getEmailOrUsername())
                .orElseThrow(() -> new UserException("User doesn't exist."));
            UserDTO userDTO = dtoFactory.createUser(user);

            StringBuilder sb = new StringBuilder();
            String token = jwtUtils.generateJwtToken(user.getUsername(), 36_000_000);
            user.setResetPasswordToken(token);
            sb.append("http://").append(environment.getProperty("front.host"))
                    .append("/").append(environment.getProperty("front.context")).append("/")
                    .append("account").append("/")
                    .append("reset_password")
                    .append("?token=")
                    .append(token);
            String url = sb.toString();
            userRepository.save(user);
            mailService.sendForgotPasswordMail(userDTO, url);

            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(token, true));
        }
        catch (UserException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User or Email doesn't exist.");
        }
        catch (IOException | TemplateException | MessagingException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Unable to send the mail : "+e.getMessage()));
        }
    }

	@PostMapping(value = "/check_token")
    // @Operation(summary = "Check the reset password token ", description = "Check the token in link of the reset password mail", tags = {"auth"})
    public ResponseEntity<?> checkToken(@RequestBody CheckTokenBody checkTokenBody){
        try {
            User user = userRepository.findByResetPasswordToken(checkTokenBody.getToken())
                    .orElseThrow(()-> new UserException("Token not found."));
            if (user.getResetPasswordToken().equals(checkTokenBody.getToken())){
                if(jwtUtils.validateJwtToken(checkTokenBody.getToken())){
                    return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Token is valid.", true));
                }
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Token invalid."));
            }
        }
        catch (UserException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Error :"+e.getMessage()));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Token is invalid.", false));
        } 
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Token is valid.",true));
    }


	@PostMapping("/reset_password")
    // @Operation(summary = "Reset password", description = "Reset user password by providing password and password confirm", tags = {"auth"})
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordBoby resetPasswordBoby){
        try {
            User user = userRepository.findByResetPasswordToken(resetPasswordBoby.getToken())
                    .orElseThrow(()-> new UserException("Token not found."));
            String oldPassword = user.getPassword();
            if(resetPasswordBoby.getNewPassword().equals(resetPasswordBoby.getNewPasswordConfirm())){
                String password = encoder.encode(resetPasswordBoby.getNewPassword());
                if(encoder.matches(resetPasswordBoby.getNewPassword(), oldPassword)){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("L'ancien mot de passe et le nouveau mot de passe sont identiques."));
                }
                if(!userServcie.validatePassword(resetPasswordBoby.getNewPassword())){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Le nouveau mot de passe n'est pas valide."));
                }
                 user.setPassword(password);
                 user.setMotDePassePrecedent(oldPassword);
                 user.setMdpModifie(1);
                 user.setDateModificationMdp(new Date());
                 user.setResetPasswordToken(null);
                 userRepository.save(user);

                 return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Password Updated.", true));
            }
            else {
                return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Password and confirmation are different.", false));
            }
        }
        catch (UserException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Error :"+e.getMessage()));
        }

    }

	@GetMapping(value = "/me")
    // @Operation(security = @SecurityRequirement(name = "bearerAuth"), summary = "User infos", description = "Return connected user informations", tags = {"auth"})
    public ResponseEntity<?> me(Principal principal){
        try {
            User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new UserException("User not found."));

            UserDTO userDTO = dtoFactory.createUser(user);

            String sql = "SELECT DISTINCT h.code FROM habilitations h " +
                "INNER JOIN habilitation_par_profil hp ON h.id = hp.habilitation_id " +
                "INNER JOIN profils r ON r.profil_id = hp.role_id " +
                "INNER JOIN users u ON u.profil_id = r.profil_id " +
                "WHERE u.username = ?";

            List<String> habilitations = jdbcTemplate.queryForList(sql, new String[]{user.getUsername()}, String.class);

            userDTO.setHabilitations(habilitations);

            return ResponseEntity.ok(userDTO);
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(e.getMessage(), false));
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserPaylaod paylaod){
	    try {
	        UserDTO user = userServcie.register(paylaod);
	        return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }
	    catch (UserException e){
	        return ResponseEntity.badRequest().body(new FieldValidationError(
	                userServcie.getFieldName(e.getMessage()),
                    e.getMessage()
            ));
        }
    }

    @GetMapping("/users/confirm")
    public ResponseEntity confirm(@RequestParam String token){
	    try{
	        int rs = userServcie.confirm(token);
	        return ResponseEntity.ok(rs);
        }
	    catch (UserException e){
	        return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/users/confirmation_mail")
    public ResponseEntity sendConfirmationMail(@RequestBody ConfirmationPayload payload){
	    try{
	        boolean rs = userServcie.sendConfirmationToken(payload);
	        return ResponseEntity.ok(rs);
        }
	    catch (UserException e){
	        return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
