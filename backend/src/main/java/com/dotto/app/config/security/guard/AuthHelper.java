package com.dotto.app.config.security.guard;

import com.dotto.app.config.security.CustomAuthenticationToken;
import com.dotto.app.config.security.CustomUserDetails;
import com.dotto.app.entity.member.RoleType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthHelper {


    public static boolean isAuthenticated(){
        return getAuthenticatiion() instanceof CustomAuthenticationToken &&
                getAuthenticatiion().isAuthenticated();
    }

    public static Long extractMemberNo(){
        return Long.valueOf(getUserDetails().getUserId());
    }

    public static Set<RoleType> extractMemberRoles(){
        return getUserDetails().getAuthorities()
                .stream()
                .map(authhority -> authhority.getAuthority())
                .map(strAuth -> RoleType.valueOf(strAuth))
                .collect(Collectors.toSet());
    }


    private static CustomUserDetails getUserDetails(){
        return (CustomUserDetails) getAuthenticatiion().getPrincipal();
    }
    private static Authentication getAuthenticatiion(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
