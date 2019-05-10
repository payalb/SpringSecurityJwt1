1)	JwtsecurityConfig: You defined your filter JwtAuthenticationTokenFilter in it.
2)	JwtAuthenticationTokenFilter: It will look for token in the header. If token is there, it will send the token to authentication Manager
3)	authentication Manager will send the token to provider to validate it
4)	provider will validate the token, if invalid-> throws error, this would be handled by exceptionTranslationFilter-> Forbidden
5)	If valid, it is returning UserDetails object
6)	Manager will extract user information and will set it in the securityContext (that means it is present in the currnt thread)
7)	You can do method level authorization


To generate jwt token I have exposed a url /token. You pass your user information and it will encrypt it with secret key and give u a token
Or should it set the jwt token by default in the response.

Once u have a token, you can make a hit to ur endpoint with that token in header

JwtAuthenticationTokenFilter -> No? UsernamePasswordAuthentication
/v1/hello -> 1st request ->token is there? No:  user data -> generate the token and send it with the response in the header
Angular can store this token in localstorage
2nd req->  Yes, parse the token….
3rd request -> token is expired -> JwtAuthenticationTokenFilter will extract the token, but token invalid-> exception -> token expired

