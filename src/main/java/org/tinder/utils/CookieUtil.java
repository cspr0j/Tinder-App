package org.tinder.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class CookieUtil {

    public static Long getValue(HttpServletRequest request){
        Cookie[] cookiesFromRequest = request.getCookies();

        return Arrays.stream(cookiesFromRequest)
                .filter(cookie -> cookie.getName().equals("user_id"))
                .map(cookie -> Long.parseLong(cookie.getValue()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No such id. Try again"));
    }
}
