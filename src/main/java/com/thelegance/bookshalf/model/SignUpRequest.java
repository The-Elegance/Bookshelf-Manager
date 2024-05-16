//package com.thelegance.bookshalf.model;
//
//import io.swagger.v3.oas.annotations.media.Schema;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Data
//@Schema(description = "Запрос на регистрацию")
//public class SignUpRequest {
//
//    @Size(min = 5, max = 50, message = "Имя пользователя должно содержать от 5 до 50 символов")
//    @NotBlank(message = "Имя пользователя не может быть пустыми")
//    private String username;
//
//    @Size(min = 5, max = 255, message = "Адрес электронной почты должен содержать от 5 до 255 символов")
//    @NotBlank(message = "Адрес электронной почты не может быть пустыми")
//    @Email(message = "Email адрес должен быть в формате user@example.com")
//    private String email;
//
//    @Schema(description = "Пароль", example = "my_1secret1_password")
//    @Size(max = 255, message = "Длина пароля должна быть не более 255 символов")
//    private String password;
//}
//
//@Data
//@Schema(description = "Запрос на аутентификацию")
//public class SignInRequest {
//
//    @Schema(description = "Имя пользователя", example = "Jon")
//    @Size(min = 5, max = 50, message = "Имя пользователя должно содержать от 5 до 50 символов")
//    @NotBlank(message = "Имя пользователя не может быть пустыми")
//    private String username;
//
//    @Schema(description = "Пароль", example = "my_1secret1_password")
//    @Size(min = 8, max = 255, message = "Длина пароля должна быть от 8 до 255 символов")
//    @NotBlank(message = "Пароль не может быть пустыми")
//    private String password;
//}
//
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Schema(description = "Ответ c токеном доступа")
//public class JwtAuthenticationResponse {
//    @Schema(description = "Токен доступа", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYyMjUwNj...")
//    private String token;
//}
//@Service
//public class JwtService {
//    @Value("${token.signing.key}")
//    private String jwtSigningKey;
//
//    /**
//     * Извлечение имени пользователя из токена
//     *
//     * @param token токен
//     * @return имя пользователя
//     */
//    public String extractUserName(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    /**
//     * Генерация токена
//     *
//     * @param userDetails данные пользователя
//     * @return токен
//     */
//    public String generateToken(UserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//        if (userDetails instanceof User customUserDetails) {
//            claims.put("id", customUserDetails.getId());
//            claims.put("email", customUserDetails.getEmail());
//            claims.put("role", customUserDetails.getRole());
//        }
//        return generateToken(claims, userDetails);
//    }
//
//    /**
//     * Проверка токена на валидность
//     *
//     * @param token       токен
//     * @param userDetails данные пользователя
//     * @return true, если токен валиден
//     */
//    public boolean isTokenValid(String token, UserDetails userDetails) {
//        final String userName = extractUserName(token);
//        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
//    }
//
//    /**
//     * Извлечение данных из токена
//     *
//     * @param token           токен
//     * @param claimsResolvers функция извлечения данных
//     * @param <T>             тип данных
//     * @return данные
//     */
//    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolvers.apply(claims);
//    }
//
//    /**
//     * Генерация токена
//     *
//     * @param extraClaims дополнительные данные
//     * @param userDetails данные пользователя
//     * @return токен
//     */
//    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
//        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 100000 * 60 * 24))
//                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
//    }
//
//    /**
//     * Проверка токена на просроченность
//     *
//     * @param token токен
//     * @return true, если токен просрочен
//     */
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    /**
//     * Извлечение даты истечения токена
//     *
//     * @param token токен
//     * @return дата истечения
//     */
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    /**
//     * Извлечение всех данных из токена
//     *
//     * @param token токен
//     * @return данные
//     */
//    private Claims extractAllClaims(String token) {
//        return Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
//                .getBody();
//    }
//
//    /**
//     * Получение ключа для подписи токена
//     *
//     * @return ключ
//     */
//    private Key getSigningKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//}
//
//
