# Spring Security - Beginner's Journey 🛡️

Let's start from **absolute zero**. No jargon, no overwhelm.

---

## Lesson 1: What Problem Does Spring Security Solve?

Imagine you built your BookMySeat app. It has pages like:

- `/movies` → anyone can see this
- `/booking` → only **logged-in users** can access
- `/admin` → only **admins** can access

Without any security, **anyone** can just type `/admin` in the browser and get in. That's a disaster.

**Spring Security** is the bouncer at the door. It:
1. Checks **who you are** (Authentication)
2. Checks **what you're allowed to do** (Authorization)

---

### Two Core Concepts (just remember these two words for now)

| Concept | Simple meaning | Real example |
|---|---|---|
| **Authentication** | *Who are you?* | Login with username + password |
| **Authorization** | *What can you do?* | Only admins can delete movies |

---

### What happens when you add Spring Security to your project?

The moment you add the dependency — **every single endpoint gets locked**. You'll see a login page automatically. Spring Security protects everything by default.

That's intentional. *Secure by default, open what you want.*

---

> ✅ That's it for Lesson 1. Just one concept to sit with.

**Before we go to Lesson 2** — tell me: does this make sense? And do you want me to next cover **how Spring Security actually works behind the scenes** (the filter chain), or jump to **how to add it to your project and see it in action**?


---
## Lesson 2: How Spring Security Actually Works Behind the Scenes 🔍

   Imagine every HTTP request your app receives is a **person walking into a building.**

   Spring Security sets up a series of **security checkpoints** before that request ever reaches your actual code (your controllers).

   ---

   ### The Filter Chain

   ```
   HTTP Request
        ↓
   [ Filter 1 ] → check something
        ↓
   [ Filter 2 ] → check something else
        ↓
   [ Filter 3 ] → check something else
        ↓
   Your Controller (finally reaches your code)
   ```

   Each **filter** does one specific job. Some examples:

   | Filter | What it does |
   |---|---|
   | `UsernamePasswordAuthenticationFilter` | Handles `/login` form submission |
   | `BasicAuthenticationFilter` | Handles username/password sent in request headers |
   | `JwtAuthenticationFilter` | (custom) Validates JWT token — **you built this in BookMySeat!** |
   | `ExceptionTranslationFilter` | Catches security errors, returns 401/403 |

   If any filter says **"NO"** → request is rejected right there. Never reaches your controller.

   If all filters say **"YES"** → request goes through. ✅

   ---

   ### The Security Context

   Once a user is verified, Spring Security stores their identity in something called the **SecurityContext**.

   Think of it like a **visitor badge** given at the entrance. Every part of your app can check this badge to know who the current user is.

   ```
   User logs in → identity stored in SecurityContext
        ↓
   Any part of your app can ask:
   "Who is currently logged in?"
   SecurityContextHolder.getContext().getAuthentication()
   ```

   ---

   ### One-line summary

   > Spring Security is a **chain of filters** that sits in front of your app. Each filter checks something. If all pass, your code runs. The verified identity is stored in **SecurityContext**.

   ---

   > ✅ Lesson 2 done. This is the mental model everything else builds on.

   Ready for **Lesson 3**? We'll actually **add Spring Security to a Spring Boot project** and see what happens the moment you include the dependency. 🚀
   ----
   ## Lesson 3: Adding Spring Security to Your Project 🚀

   Let's see it in action. Hands-on now.

   ---

   ### Step 1: Add the Dependency

   In your `pom.xml`:

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-security</artifactId>
   </dependency>
   ```

   That's it. Just this one line.

   ---

   ### What Happens The Moment You Add This?

   Run your app and open any endpoint — say `http://localhost:8080/movies`

   You'll see **this instead:**

   ```
   http://localhost:8080/login
   ```

   A login page appears **automatically**. You didn't write a single line of security code.

   Spring Boot auto-configured everything for you.

   ---

   ### Default Credentials

   Spring Security creates a **default user** for you:

   - **Username:** `user`
   - **Password:** printed in your console on startup 👇

   ```
   Using generated security password: a3f9c2b1-xxxx-xxxx-xxxx-xxxxxxxxxxxx
   ```

   You copy that password, enter it on the login page → you're in.

   ---

   ### What Got Locked Automatically?

   | Endpoint | Status |
   |---|---|
   | Every single endpoint | 🔒 Locked |
   | `/login` | ✅ Public (Spring created it) |
   | `/logout` | ✅ Public (Spring created it) |

   ---

   ### The Key Takeaway

   You added **zero configuration** and Spring Security:
   - Created a login page
   - Locked every endpoint
   - Created a default user
   - Handles login/logout flow

   This is what **"secure by default"** means.

   ---

   > ✅ Lesson 3 done.

   In **Lesson 4** we'll learn how to **take control** — write your own Security Configuration class so you can decide which endpoints are public, which are protected, and set your own username/password.

   Ready? 👇
   ----
   ## Lesson 4: Taking Control — Your Own Security Configuration 🎛️

   Until now Spring Security was making all the decisions. Now **you** take the wheel.

   ---

   ### The Main Class You Need

   You create a regular class, annotate it with `@Configuration`, and define a **SecurityFilterChain** bean.

   ```java
   @Configuration
   @EnableWebSecurity
   public class SecurityConfig {

       @Bean
       public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

           http
               .authorizeHttpRequests(auth -> auth
                   .requestMatchers("/movies", "/home").permitAll()  // public
                   .anyRequest().authenticated()                      // everything else → login required
               )
               .formLogin(Customizer.withDefaults()); // use default login page

           return http.build();
       }
   }
   ```

   ---

   ### Breaking It Down Line By Line

   ```java
   .requestMatchers("/movies", "/home").permitAll()
   ```
   > Anyone can access `/movies` and `/home`. No login needed.

   ```java
   .anyRequest().authenticated()
   ```
   > Every other endpoint → must be logged in.

   ```java
   .formLogin(Customizer.withDefaults())
   ```
   > Use Spring's built-in login page for now.

   ---

   ### Setting Your Own Username & Password

   No more random generated passwords. Define your own:

   ```java
   @Bean
   public UserDetailsService userDetailsService() {

       UserDetails user = User.builder()
           .username("dikshant")
           .password(passwordEncoder().encode("mypassword123"))
           .roles("USER")
           .build();

       return new InMemoryUserDetailsManager(user);
   }

   @Bean
   public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
   }
   ```

   Now you can login with `dikshant` / `mypassword123`.

   ---

   ### Why BCryptPasswordEncoder?

   You should **never store plain text passwords.** Ever.

   BCrypt turns `"mypassword123"` into something like:
   ```
   $2a$10$7QvYqKd1fGz8aHnL3mXp2uWvRtN9sPkJdLeM4xBcAoEfZiYhGwCq1
   ```

   Even if your database leaks → passwords are unreadable. ✅

   ---

   ### Full Picture So Far

   ```
   Request comes in
         ↓
   SecurityFilterChain checks your rules
         ↓
   Is this endpoint public? → Let through
   Is user logged in?       → Let through
   Neither?                 → Redirect to /login
   ```

   ---

   > ✅ Lesson 4 done. You now know how to write real security rules.

   **Lesson 5** is where it gets very relevant for you — we'll cover **how Spring Security works with REST APIs and JWT** (no session, no login page — just tokens). This is exactly what you used in BookMySeat.

   Ready? 👇
   ---
   ## Lesson 5: Spring Security with REST APIs & JWT 🔑

   This is the most important lesson for you — because this is **exactly what BookMySeat uses.**

   ---

   ### First — Why REST APIs Are Different

   In Lesson 4 we used a **login page** (form login). That works for websites.

   But REST APIs are different. Your frontend (React) or mobile app talks to your backend via HTTP requests. There's **no browser session, no login page.**

   So the question is — how does the backend know **who is making each request?**

   ---

   ### The Old Way vs The JWT Way

   | | Session Based | JWT Based |
   |---|---|---|
   | After login | Server stores session in memory | Server gives you a **token** |
   | Next request | Browser sends session cookie | Client sends the **token** |
   | Server checks | Looks up session in memory | **Verifies the token itself** |
   | Scalability | ❌ Server must remember everyone | ✅ Stateless, no memory needed |

   ---

   ### How JWT Flow Works — Step by Step

   ```
   1. User sends POST /login  (username + password)
             ↓
   2. Server verifies credentials
             ↓
   3. Server creates a JWT token and sends it back
             ↓
   4. Client stores the token (localStorage etc.)
             ↓
   5. Every future request includes the token in header:
      Authorization: Bearer <token>
             ↓
   6. Server validates token on every request → allows or rejects
   ```

   ---

   ### What Does a JWT Token Look Like?

   ```
   eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkaWtzaGFudCIsImlhdCI6MTcw.xK9mP2qR
   ```

   It has **3 parts** separated by dots:

   ```
   HEADER . PAYLOAD . SIGNATURE
   ```

   | Part | Contains |
   |---|---|
   | Header | Algorithm used (HS256 etc.) |
   | Payload | User data (username, role, expiry) |
   | Signature | Proves token wasn't tampered with |

   The payload is **base64 encoded** — not encrypted. Don't put passwords in it.

   ---

   ### What You Need to Build for JWT in Spring Security

   Three things:

   ```
   1. JwtUtil class          → generate token, validate token, extract username

   2. JwtFilter class        → intercepts every request, reads token from header,
                               validates it, sets user in SecurityContext

   3. SecurityConfig update  → disable sessions, register your JwtFilter,
                               make /login public
   ```

   ---

   ### The JwtFilter — This Is The Heart

   ```java
   @Component
   public class JwtFilter extends OncePerRequestFilter {

       @Override
       protected void doFilterInternal(HttpServletRequest request,
                                       HttpServletResponse response,
                                       FilterChain filterChain)
                                       throws ServletException, IOException {

           // 1. Read the Authorization header
           String authHeader = request.getHeader("Authorization");

           // 2. Check if token exists and starts with "Bearer "
           if (authHeader != null && authHeader.startsWith("Bearer ")) {

               String token = authHeader.substring(7); // remove "Bearer "

               // 3. Validate token and extract username
               String username = jwtUtil.extractUsername(token);

               // 4. Set user identity in SecurityContext
               UsernamePasswordAuthenticationToken authToken =
                   new UsernamePasswordAuthenticationToken(
                       username, null, authorities);

               SecurityContextHolder.getContext().setAuthentication(authToken);
           }

           // 5. Move to next filter
           filterChain.doFilter(request, response);
       }
   }
   ```

   ---

   ### SecurityConfig Update for JWT

   ```java
   http
       .csrf(csrf -> csrf.disable())              // REST APIs don't need CSRF
       .sessionManagement(session -> session
           .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // no sessions
       .authorizeHttpRequests(auth -> auth
           .requestMatchers("/auth/login", "/auth/register").permitAll()
           .anyRequest().authenticated()
       )
       .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
                                                  // your filter runs before default
   ```

   ---

   ### The Complete Picture

   ```
   Request → Authorization: Bearer <token>
                  ↓
             JwtFilter runs
                  ↓
          token valid? → extract username
                  ↓
          set in SecurityContext
                  ↓
          Controller runs → can access current user
   ```

   ---

   > ✅ Lesson 5 done. This is the full JWT flow you already have in BookMySeat — now you know exactly **why** each piece exists.

   **Lesson 6** will cover **Roles & Authorities** — how to say "only ADMIN can delete a movie, only USER can book a seat." Ready? 👇
   ---

   ## Lesson 6: Roles & Authorities — Who Can Do What 🎭

   Authentication says **who you are.**
   This lesson is about **what you're allowed to do.**

   ---

   ### Role vs Authority — Simple Difference

   People often confuse these two. Here's the clearest way to think about it:

   | | Meaning | Example |
   |---|---|---|
   | **Role** | A broad label for a user | `ADMIN`, `USER`, `MODERATOR` |
   | **Authority** | A specific permission | `DELETE_MOVIE`, `VIEW_BOOKING` |

   Think of it like a job:
   - **Role** = your job title (Manager)
   - **Authority** = specific things you can do (approve leave, view reports)

   In most beginner/mid projects → **Roles are enough.** Authorities are for fine-grained control.

   ---

   ### How to Assign Roles

   When creating a user, you assign a role:

   ```java
   UserDetails user = User.builder()
       .username("dikshant")
       .password(passwordEncoder().encode("pass123"))
       .roles("USER")           // regular user
       .build();

   UserDetails admin = User.builder()
       .username("admin")
       .password(passwordEncoder().encode("admin123"))
       .roles("ADMIN")          // admin
       .build();
   ```

   > ⚠️ Internally Spring saves roles as `ROLE_USER` and `ROLE_ADMIN` — it adds the `ROLE_` prefix automatically.

   ---

   ### Protecting Endpoints By Role

   **Option 1 — In SecurityConfig (URL based)**

   ```java
   http
       .authorizeHttpRequests(auth -> auth
           .requestMatchers("/auth/**").permitAll()
           .requestMatchers("/admin/**").hasRole("ADMIN")     // only admins
           .requestMatchers("/booking/**").hasRole("USER")    // only users
           .anyRequest().authenticated()
       )
   ```

   Clean and centralized. All rules in one place.

   ---

   **Option 2 — On the method directly (Method Security)**

   First enable it:

   ```java
   @Configuration
   @EnableMethodSecurity          // add this
   public class SecurityConfig {
   ```

   Then use it on any method:

   ```java
   @RestController
   public class MovieController {

       @DeleteMapping("/movies/{id}")
       @PreAuthorize("hasRole('ADMIN')")        // only admin can delete
       public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
           // delete logic
       }

       @GetMapping("/movies")
       @PreAuthorize("hasAnyRole('USER', 'ADMIN')")   // both can view
       public ResponseEntity<?> getAllMovies() {
           // fetch logic
       }
   }
   ```

   ---

   ### Which Option Should You Use?

   | | URL Based (SecurityConfig) | Method Based (@PreAuthorize) |
   |---|---|---|
   | Best for | Broad endpoint protection | Fine-grained method control |
   | Where rules live | One central place | Scattered across controllers |
   | Flexibility | Less flexible | Very flexible |

   **In real projects → use both together.** URL rules for broad protection, `@PreAuthorize` for specific cases.

   ---

   ### How Roles Flow With JWT

   When you generate a JWT token → include the role in the payload:

   ```java
   // while generating token
   claims.put("role", "ROLE_ADMIN");
   ```

   When JwtFilter validates the token → extract the role and set it in SecurityContext:

   ```java
   // inside JwtFilter
   String role = jwtUtil.extractRole(token);

   List<GrantedAuthority> authorities =
       List.of(new SimpleGrantedAuthority(role));

   UsernamePasswordAuthenticationToken authToken =
       new UsernamePasswordAuthenticationToken(
           username, null, authorities);   // authorities passed here ✅

   SecurityContextHolder.getContext().setAuthentication(authToken);
   ```

   Now Spring Security knows **both who the user is AND what role they have.**

   ---

   ### Full Picture

   ```
   User logs in → JWT token created with role inside
           ↓
   Next request comes with token
           ↓
   JwtFilter extracts username + role → sets in SecurityContext
           ↓
   Request hits /admin/delete
           ↓
   Spring checks: does this user have ROLE_ADMIN?
           ↓
   YES → allow ✅       NO → 403 Forbidden ❌
   ```

   ---

   > ✅ Lesson 6 done. You now understand the full authentication + authorization picture.

   **Lesson 7** will cover **how to get the currently logged-in user inside your controller** — something you'll use in almost every real feature. Ready? 👇
   ---
   ## Lesson 7: Getting the Currently Logged-In User 👤

   This is something you'll use **in almost every feature** you build.

   Think about it — when a user books a seat in BookMySeat, you need to know **which user** is making that booking. You can't just trust what they send in the request body. You need to get it from the **SecurityContext** — the verified identity Spring Security set for you.

   ---

   ### 3 Ways to Do It

   ---

   ### Way 1 — SecurityContextHolder (manual way)

   ```java
   @GetMapping("/profile")
   public ResponseEntity<?> getProfile() {

       Authentication authentication =
           SecurityContextHolder.getContext().getAuthentication();

       String username = authentication.getName();  // gets logged-in username

       return ResponseEntity.ok("Hello " + username);
   }
   ```

   Works everywhere — inside controllers, services, anywhere in your code.

   ---

   ### Way 2 — @AuthenticationPrincipal (clean way ✅)

   Spring can **inject** the logged-in user directly into your method:

   ```java
   @GetMapping("/profile")
   public ResponseEntity<?> getProfile(
           @AuthenticationPrincipal UserDetails userDetails) {

       String username = userDetails.getUsername();

       return ResponseEntity.ok("Hello " + username);
   }
   ```

   > This is the **recommended way.** Cleaner, no boilerplate, Spring handles everything.

   ---

   ### Way 3 — Principal (simplest way)

   If you just need the username and nothing else:

   ```java
   @GetMapping("/profile")
   public ResponseEntity<?> getProfile(Principal principal) {

       String username = principal.getName();

       return ResponseEntity.ok("Hello " + username);
   }
   ```

   `Principal` is a basic Java interface. Simple but limited.

   ---

   ### Which Way to Use When?

   | Situation | Use |
   |---|---|
   | Need full user details (roles, etc.) | `@AuthenticationPrincipal` |
   | Just need the username | `Principal` |
   | Inside a Service class (not controller) | `SecurityContextHolder` |

   ---

   ### Real World Example — BookMySeat Style

   Imagine your booking endpoint. You never trust the userId from the request body — you get it from the logged-in user:

   ```java
   @PostMapping("/bookings")
   public ResponseEntity<?> createBooking(
           @RequestBody BookingRequest request,
           @AuthenticationPrincipal UserDetails userDetails) {

       String username = userDetails.getUsername();

       // fetch the actual user from DB using username
       User currentUser = userRepository.findByUsername(username)
                               .orElseThrow(() -> new RuntimeException("User not found"));

       // now create booking for this user
       Booking booking = new Booking();
       booking.setUser(currentUser);
       booking.setShowId(request.getShowId());
       booking.setSeats(request.getSeats());

       bookingRepository.save(booking);

       return ResponseEntity.ok("Booking confirmed!");
   }
   ```

   > ✅ The user can't fake their identity. It comes from the **verified JWT token** → SecurityContext → your controller.

   ---

   ### The Flow

   ```
   JWT token in request header
             ↓
   JwtFilter validates → sets user in SecurityContext
             ↓
   Controller method runs
             ↓
   @AuthenticationPrincipal pulls user OUT of SecurityContext
             ↓
   You use it safely ✅
   ```

   ---

   > ✅ Lesson 7 done. Short but extremely practical — you'll use this in every secured feature.

   **Lesson 8** will cover **common errors you'll face** — 401 vs 403, CORS issues, and why your token gets rejected. The debugging lesson. Ready? 👇
   ---
   ## Lesson 8: Common Errors & How to Debug Them 🐛

   Every developer hits these. Knowing what they mean saves you **hours** of frustration.

   ---

   ### Error 1 — 401 Unauthorized

   ```json
   {
     "status": 401,
     "error": "Unauthorized"
   }
   ```

   **Means:** You are not logged in / token is missing or invalid.

   **Common causes:**

   | Cause | Fix |
   |---|---|
   | No token sent in request | Add `Authorization: Bearer <token>` header |
   | Token expired | Login again to get a fresh token |
   | Token sent without "Bearer " prefix | Make sure it's `Bearer <space> token` |
   | JwtFilter not reading header correctly | Check `request.getHeader("Authorization")` |

   ---

   ### Error 2 — 403 Forbidden

   ```json
   {
     "status": 403,
     "error": "Forbidden"
   }
   ```

   **Means:** You ARE logged in — but you **don't have permission** for this.

   **Common causes:**

   | Cause | Fix |
   |---|---|
   | User has `ROLE_USER` but endpoint needs `ROLE_ADMIN` | Check role assigned to user |
   | Role not included in JWT token | Add role to token claims while generating |
   | Role not extracted in JwtFilter | Make sure you extract and set authorities |
   | Spring expects `ROLE_` prefix | Use `ROLE_ADMIN` not just `ADMIN` |

   ---

   > 💡 **Easy way to remember:**
   > - **401** = "I don't know who you are" → Authentication problem
   > - **403** = "I know who you are, but NO" → Authorization problem

   ---

   ### Error 3 — CORS Error

   You'll see this in your browser console:

   ```
   Access to fetch at 'http://localhost:8080/movies' from origin
   'http://localhost:3000' has been blocked by CORS policy
   ```

   **Means:** Your React frontend (port 3000) is trying to talk to your backend (port 8080). Browser blocks this by default.

   **Fix — Add CORS config in SecurityConfig:**

   ```java
   @Bean
   public CorsConfigurationSource corsConfigurationSource() {

       CorsConfiguration config = new CorsConfiguration();
       config.setAllowedOrigins(List.of("http://localhost:3000")); // your frontend URL
       config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
       config.setAllowedHeaders(List.of("*"));
       config.setAllowCredentials(true);

       UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
       source.registerCorsConfiguration("/**", config);
       return source;
   }
   ```

   And enable it in your filter chain:

   ```java
   http.cors(cors -> cors.configurationSource(corsConfigurationSource()))
   ```

   ---

   ### Error 4 — 403 on POST/PUT/DELETE (CSRF)

   You're sending a POST request and getting 403 — even with a valid token.

   **Cause:** Spring Security enables **CSRF protection by default.** It blocks state-changing requests without a CSRF token.

   **For REST APIs — just disable it:**

   ```java
   http.csrf(csrf -> csrf.disable())
   ```

   > CSRF protection is for browser-based apps with sessions. REST APIs using JWT don't need it.

   ---

   ### Error 5 — White Label Error Page / Filter Not Running

   Your JwtFilter exists but it's not being called at all.

   **Common causes:**

   | Cause | Fix |
   |---|---|
   | Filter not registered in SecurityConfig | Add `.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)` |
   | Filter registered as `@Component` AND in SecurityConfig | Pick one — doing both runs it twice |
   | Wrong filter order | Make sure JWT filter runs BEFORE `UsernamePasswordAuthenticationFilter` |

   ---

   ### Your Debugging Checklist 🔍

   When something goes wrong, go through this:

   ```
   1. Is it 401 or 403?
            ↓
   2. 401 → Is the token being sent? Is it valid? Is it expired?
            ↓
   3. 403 → Is the role correct? Is it in the token? Is it extracted in JwtFilter?
            ↓
   4. CORS? → Add CorsConfigurationSource bean
            ↓
   5. POST giving 403? → Disable CSRF
            ↓
   6. Filter not running? → Check registration in SecurityConfig
   ```

   ---

   ### One Tip That Saves Hours

   Add this to `application.properties` while debugging:

   ```properties
   logging.level.org.springframework.security=DEBUG
   ```

   Spring Security will **log every decision it makes** — which filter ran, why a request was rejected, which rule matched. Turn it off in production.

   ---

   > ✅ Lesson 8 done. You now know how to read and fix the most common Spring Security errors.

   **Lesson 9** will be the **final big picture lesson** — putting everything together end to end, and how to think about Spring Security in interviews. Ready? 👇
   ---
   ## Lesson 9: The Big Picture — Everything Together 🗺️

   This is your **consolidation lesson.** No new concepts. Just connecting every dot.

   ---

   ### Everything You Learned — In One Flow

   ```
   User sends POST /auth/login  (username + password)
                 ↓
   AuthController receives it
                 ↓
   AuthenticationManager verifies credentials
                 ↓
   UserDetailsService loads user from DB        ← Lesson 4
                 ↓
   BCrypt checks password matches               ← Lesson 4
                 ↓
   JwtUtil generates a token with role inside   ← Lesson 5, 6
                 ↓
   Token sent back to client
   _____________________________________________

   Next Request → GET /booking/my-bookings
   Authorization: Bearer <token>
                 ↓
   JwtFilter intercepts                         ← Lesson 5
                 ↓
   Token valid? Extract username + role
                 ↓
   Set in SecurityContext                       ← Lesson 2
                 ↓
   SecurityFilterChain checks rules             ← Lesson 4
   Does user have correct role?                 ← Lesson 6
                 ↓
   Controller runs
   @AuthenticationPrincipal gets current user   ← Lesson 7
                 ↓
   Response sent ✅
   ```

   ---

   ### All 8 Lessons As One Table

   | Lesson | What You Learned |
   |---|---|
   | 1 | What problem Spring Security solves — Authentication vs Authorization |
   | 2 | Filter chain + SecurityContext — the mental model |
   | 3 | Adding Spring Security — secure by default |
   | 4 | SecurityConfig — your own rules, BCrypt, UserDetailsService |
   | 5 | JWT flow — stateless REST API security |
   | 6 | Roles — protecting endpoints by who you are |
   | 7 | Getting logged-in user — SecurityContextHolder, @AuthenticationPrincipal |
   | 8 | Debugging — 401 vs 403, CORS, CSRF |

   ---

   ### How To Answer Spring Security In Interviews

   You'll get asked this. Here's how to structure your answer naturally:

   **"How does Spring Security work in your project?"**

   > *"Spring Security sits in front of my application as a filter chain. When a user logs in, the credentials are verified using UserDetailsService and BCrypt. On success, a JWT token is generated with the user's role embedded in it. For every subsequent request, my custom JwtFilter intercepts it, validates the token, extracts the username and role, and sets it in the SecurityContext. The SecurityFilterChain then checks if the user has the required role for that endpoint. If yes, the request reaches the controller where I use @AuthenticationPrincipal to access the current user."*

   That answer covers — filters, JWT, roles, SecurityContext, UserDetailsService. **Exactly what interviewers want to hear.**

   ---

   ### How BookMySeat Maps To All Of This

   | BookMySeat Feature | Spring Security Concept |
   |---|---|
   | `/auth/register`, `/auth/login` are public | `permitAll()` in SecurityConfig |
   | Booking endpoints need login | `authenticated()` in SecurityConfig |
   | JWT token in every request | JwtFilter + JwtUtil |
   | Getting current user for booking | `@AuthenticationPrincipal` |
   | Password stored securely | BCryptPasswordEncoder |
   | React frontend calling backend | CORS configuration |

   > You didn't just learn Spring Security theory — **you already built it.** Now you know the name of every piece you used.

   ---

   ### What To Learn Next (Your Roadmap)

   Now that you have the foundation, here's what to explore next:

   ```
   Spring Security Foundation ✅  ← You are here
             ↓
   OAuth2 & Social Login (Login with Google)
             ↓
   Refresh Tokens (JWT expiry handling)
             ↓
   Method Security (@PreAuthorize deep dive)
             ↓
   Spring Security Testing (mocking auth in tests)
   ```

   ---

   ### One Last Thing

   Spring Security feels overwhelming at first because it does a lot **invisibly.** But now you have the mental model:

   > **Filter chain intercepts → Identity verified → Stored in SecurityContext → Rules checked → Controller runs.**

   Everything else is just details on top of this.

   ---

   > ✅ All 9 Lessons Complete. 🎉
   >
   > You went from knowing nothing about Spring Security to understanding the full JWT authentication flow, roles, debugging, and how to explain it in interviews.

   **You've got this, Dikshant.** Go connect these dots in BookMySeat and own it in your next interview. 💪