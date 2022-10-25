package org.tinder.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class CookieUtil {
    public static Long getValue(HttpServletRequest request) {
        Cookie[] cookiesFromRequest = request.getCookies();
        if (cookiesFromRequest == null) return null;

        return Arrays.stream(cookiesFromRequest)
                .filter(cookie -> cookie.getName().equalsIgnoreCase("user_id"))
                .map(cookie -> Long.parseLong(cookie.getValue()))
                .findFirst()
                .orElse(null);
    }

    public static void delete(HttpServletRequest request, HttpServletResponse response) {
        Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equalsIgnoreCase("user_id"))
                .map(cookie ->
                        new Cookie(cookie.getName(), cookie.getValue()) {{
                            setMaxAge(0);
                        }}
                ).forEach(response::addCookie);
    }
}