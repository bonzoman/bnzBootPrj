SSL 적용을 위해 인증서 만들기(java bin 폴더의 keyytool 이용)
keytool -genkey -alias spring -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 4000