package com.example.demo.api.user;

import com.example.demo.api.Business.Business;
import com.example.demo.api.Business.BusinessService;
import com.example.demo.api.user.registrationToken.ConfirmationToken;
import com.example.demo.api.user.registrationToken.ConfirmationTokenService;
import com.example.demo.email.EmailSender;
import com.example.demo.security.jwt.JwtConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class UserServiceImp implements UserService, UserDetailsService {
    private final static String User_Not_Found_Msg ="user with username %s not found";
    private final AppUserRepo appUserRepo;
    private EmailValidator emailValidator;
    private final PasswordEncoder passwordEncoder;

    private final BusinessService businessService;
    private final EmailSender emailSender;
    private final ConfirmationTokenService confirmationTokenService;
    private final JwtConfig jwtConfig;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepo.findByUsername(username)
                .orElseThrow(()->
                        new UsernameNotFoundException(String.format(User_Not_Found_Msg,username)));
    }

    @Override
    @Transactional
    public AppUser saveUser(AppUser user) {
        boolean userEmailExists=appUserRepo.findByEmail(user.getEmail())
                        .isPresent();
        boolean userNameExists=appUserRepo.findByUsername(user.getUsername())
                .isPresent();
        boolean userPhoneNumberExists=appUserRepo.findByPhoneNumber(user.getPhoneNumber())
                .isPresent();

        if(userEmailExists){
            throw new IllegalStateException("Email already taken");
        } else if (userNameExists) {
            throw new IllegalStateException("UserName already taken");
        } else if (userPhoneNumberExists) {
            throw new IllegalStateException("Phone Number already taken");
        }
        if(!emailValidator.test((user.getEmail()))){
            throw new IllegalStateException("Email not valid");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getUsername()+" || "+user.getPassword());

        appUserRepo.save(user); //create the User

        //create confirmation Token For the Email Activation
        String token =UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        String link=String.format(jwtConfig.getEmailVerificationUrl(),token);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        System.out.println(link);
        emailSender.send(user.getEmail(),buildEmail(user.getName(),link));
        return user;
    }
    @Transactional
    @Override
    public String confirmToken(String token){
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserRepo.enableAppUser(
                confirmationToken.getAppUser().getEmail());
        return "confirmed";

    }
    @Override
    public boolean addRoleToUser(String userName, Role roleName) {
        int i = appUserRepo.setUserRole(userName, roleName, new Date());
        return (i==1);
    }

    @Override
    public boolean addBusinessToUser(String userName, Long businessId) {
        Business business= businessService.getBusinessById(businessId).orElseThrow();
        return (appUserRepo.userBusiness(userName,business,new Date())==1);
    }

    @Override
    public Optional<AppUser> getUser(String userName) {
        return appUserRepo.findByUsername(userName);
    }

    @Override
    public boolean checkIfUserExists(String userName, String email, String phoneNumber) {
      AtomicBoolean flag= new AtomicBoolean(false);
        appUserRepo.findByUsername(userName).ifPresent((appUser -> flag.set(true)));
        if(flag.get()) return true;
        appUserRepo.findByEmail(email).ifPresent((appUser -> flag.set(true)));
        if(flag.get()) return true;
        appUserRepo.findByPhoneNumber(phoneNumber).ifPresent((appUser -> flag.set(true)));
        if(flag.get()) return true;
        return false;
    }

    @Override
    public List<AppUser> getUsers() {
        return appUserRepo.findAll();
    }

    @Override
    public List<AppUser> getUsersWhereUserRoleIsNull() {
//        Role role=null;
        return appUserRepo.findAllByRoles(null).orElseThrow();
    }

    @Override
    public List<AppUser> getUsersWhereUserRoleIsBusiness() {
        return appUserRepo.findAllByRoles(Role.BUSINESS).orElseThrow();
    }

    @Override
    public List<AppUser> getUsersWhereUserRoleIsUser() {
        return appUserRepo.findAllByRoles(Role.USER).orElseThrow();
    }

    @Override
    public List<AppUser> getUsersWhereUserRoleIsCouriers() {
        return appUserRepo.findAllByRoles(Role.COURIER).orElseThrow();
    }

    @Override
    public List<AppUser> getUsersWhereBusinessIsNull() {
        return appUserRepo.findAllByBusiness(null).orElseThrow();
    }

    @Override
    public List<AppUser> getUsersWhereBusinessIs(Long Id) {

        return appUserRepo.findAllByBusiness(
                businessService.getBusinessById(Id).orElseThrow()
                ).orElseThrow();
    }

    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}
