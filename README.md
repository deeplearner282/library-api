## Steps to Setup

1) run: docker-compose -up
2) Start LibraryApiApplication.main
3) OpenApi: http://localhost:8080/swagger-ui/

Test them using postman or any other rest client.

## Sample Valid JSON Request Bodys for auth

##### <a id="signup">Sign Up -> /api/auth/signup</a>
```json
{
	"username": "leanne",
	"password": "password",
	"email": "leanne.graham@gmail.com"
}
```

##### <a id="signin">Log In -> /api/auth/signin</a>
```json
{
	"usernameOrEmail": "leanne",
	"password": "password"
}
```

Other endpoints can be found by OpenApi: http://localhost:8080/swagger-ui/