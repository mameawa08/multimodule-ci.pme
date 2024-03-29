package com.administration.controller;

import java.util.List;

import com.administration.dto.FieldValidationError;
import com.administration.payload.AddEntreprisePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.administration.dto.UserDTO;
import com.administration.exception.UserException;
import com.administration.payload.MessageResponse;
import com.administration.payload.UpdatePasswordBody;
import com.administration.payload.UserPaylaod;
import com.administration.service.IUserService;

@RestController
@RequestMapping("/api/auth/users")
public class UserController {
	
	@Autowired
	private IUserService userService;

	@GetMapping("")
	public ResponseEntity getUsers() {
		try {
			List<UserDTO> users = userService.getUsers();
			return ResponseEntity.ok(users);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity getUser(@PathVariable Long id) {
		try {
			UserDTO user = userService.getUser(id);
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("")
	public ResponseEntity createUser(@RequestBody UserPaylaod paylaod) {
		try {
			UserDTO user = userService.createUser(paylaod);
			return ResponseEntity.status(HttpStatus.CREATED).body(user);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new FieldValidationError(
					userService.getFieldName(e.getMessage()),
					e.getMessage()
			));
		}
	}

	@GetMapping(value = "/{id}/status")
	public ResponseEntity switchStatus(@PathVariable Long id) {
		try {
			boolean status = userService.switchStatus(id);
			return ResponseEntity.status(HttpStatus.CREATED).body(status);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity deleteUser(@PathVariable Long id) {
		try {
			boolean status = userService.deleteUser(id);
			return ResponseEntity.status(HttpStatus.OK).body(status);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity updateUser(@PathVariable Long id, @RequestBody UserPaylaod paylaod) {
		try {
			UserDTO user = userService.createUser(paylaod);
			return ResponseEntity.status(HttpStatus.CREATED).body(user);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new FieldValidationError(
					userService.getFieldName(e.getMessage()),
					e.getMessage()
			));
		}
	}

	@RequestMapping(value = "/{id}/update-password", method = {RequestMethod.PUT, RequestMethod.PATCH})
//	@PreAuthorize("hasRole('ADD_USER')")
//	@Operation(security = @SecurityRequirement(name = "bearerAuth"), summary = "Update user password", description = "Update user password by providing the old, new and the new confirmation password", tags = {"User"})
	public ResponseEntity<?> updatePassword(@PathVariable("id") Long id, @RequestBody UpdatePasswordBody updatePasswordBody){
		try {
			userService.updatePassword(id, updatePasswordBody);
			return ResponseEntity.ok(new MessageResponse("Mot de passe modifié avec succès.", true));
		}
		catch (UserException uex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(uex.getMessage(), false));
		}
	}

	@RequestMapping(value = "/{id}/entreprises", method = {RequestMethod.PUT, RequestMethod.PATCH})
//	@PreAuthorize("hasRole('ADD_USER')")
//	@Operation(security = @SecurityRequirement(name = "bearerAuth"), summary = "Update user password", description = "Update user password by providing the old, new and the new confirmation password", tags = {"User"})
	public ResponseEntity<?> addEntreprise(@PathVariable("id") Long id, @RequestBody AddEntreprisePayload payload){
		try {
			UserDTO user = userService.addEntrepriseToUser(payload.getUserId(), payload.getEntrepriseId());
			return ResponseEntity.ok(user);
		}
		catch (UserException uex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(uex.getMessage(), false));
		}
	}


	@GetMapping("/profils/{idProfil}")
	public ResponseEntity getUsersByProfil(@PathVariable Long idProfil){
		try {
			List<UserDTO> users = userService.getUsersByProfil(idProfil);
			return ResponseEntity.ok(users);
		}
		catch (UserException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
