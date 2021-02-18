# Grant Type

Grant type은 애플리케이션이 access token을 얻는 방법에 대한 종류이다.

<img src="images/grant types.png">

Grant Type은 각 애플리케이션에 따라 권장되는 인증 방법이 다르다. 애플리케이션의 사용법과 상황이 다 다르기 때문에 비교적 UI가 없고 서버에서만 사용하거나 일반 사람들이 접근할 수 없는 애플리케이션일수록 안전하다. 그래서 덜 안전할 수록 더 안전한 인증방법을 써야한다. <br/>
현재는 권장하지 않는 유형도 있는데, 추가적인 인증코드의 교환없이 바로 토큰을 발급하는 Implicit Flow와 사용자의 비밀번호가 요구되는 Password 이다.<br/>
access token을 발급받을때 함께 받는 refresh token 타입도 있다. access token이 만료되면 refresh token으로 유효한 access token을 다시 발급받을 수 있다.