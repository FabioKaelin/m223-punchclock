
# Generate new JWT Token
mvn exec:java -Dexec.mainClass=ch.zli.m223.service.JWTService -Dsmallrye.jwt.sign.key.location=privateKey.pem
# mvn exec:java -Dexec.mainClass=ch.zli.m223.service.JWTService -Dexec.classpathScope=test -Dsmallrye.jwt.sign.key.location=privateKey.pem

export token="eyJ0..."


curl -v http://127.0.0.1:8080/secured/permit-all; echo

curl -v http://127.0.0.1:8080/secured/roles-allowed; echo
curl -H "Authorization: Bearer ${token}" http://127.0.0.1:8080/secured/roles-allowed; echo

curl -v http://127.0.0.1:8080/secured/roles-allowed-admin; echo
curl -H "Authorization: Bearer ${token}" http://127.0.0.1:8080/secured/roles-allowed-admin; echo


curl -v http://127.0.0.1:8080/secured; echo

