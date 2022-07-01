package learn;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegexpTest {

    //아이디 패턴 : 4 ~ 20글자 영문 숫자만 가능
    Pattern idPattern = Pattern.compile("^[a-zA-Z0-9]{4,20}+$");
    //패스워드 패턴 : 8 ~ 20글자 이내 영문 , 숫자, 특수문자 1개씩 포함
    Pattern pwdPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z0-9\\d@$!%*#?&]{8,20}$");
    //닉네임 패턴 : 특수문자 안되고 1~12글자 이내
    Pattern nickNamePattern = Pattern.compile("^[A-Za-z0-9가-힣]{1,12}+$");
    //핸드폰 패턴 : 핸드폰 형식
    Pattern phonePattern = Pattern.compile("^01([016789])-?([0-9]{3,4})-?([0-9]{4})$");

    @Test
    void idExpTest(){
        //정상
        String id ="as231asda";
        assertTrue(idPattern.matcher(id).find());
        //특수문자 포함시
        String id1 = "A@asd1232as";
        assertFalse(idPattern.matcher(id1).find());

        //소문자만
        String id2 = "asdadadasdasd";
        assertTrue(idPattern.matcher(id2).find());

        //대문자만
        String id3 = "ASDADASDSADSA";
        assertTrue(idPattern.matcher(id3).find());

        //숫자로만
        String id4 = "12312313123";
        assertTrue(idPattern.matcher(id4).find());

        //최대길이 초과
        String id5 = "1234567890123456789011";
        assertFalse(idPattern.matcher(id5).find());

        //최소길이 미만
        String id6 = "sda";
        assertFalse(idPattern.matcher(id6).find());

    }

    @Test
    void pwdTest(){

        //정상
        String pwd1 = "qwe!@#123a";
        assertTrue(pwdPattern.matcher(pwd1).find());

        //특수문자 제외시
        String pwd2 = "asdasd212da";
        assertFalse(pwdPattern.matcher(pwd2).find());

        //숫자 제외시
        String pwd3 = "!@#!@#asd";
        assertFalse(pwdPattern.matcher(pwd3).find());

        //영문 제외시
        String pwd4 = "!@#!@#123123";
        assertFalse(pwdPattern.matcher(pwd4).find());

        //최소길이
        String pwd5 = "!2a4@67";
        assertFalse(pwdPattern.matcher(pwd5).find());

        //최대길이
        String pwd6 = "!@#ASASDasdasdw43414123";
        assertFalse(pwdPattern.matcher(pwd6).find());
    }

    @Test
    void nickTest(){
        //정상
        String nick1 = "A한글123";
        assertTrue(nickNamePattern.matcher(nick1).find());

        //특수문자 포함시
        String nick2 = "A한글12#";
        assertFalse(nickNamePattern.matcher(nick2).find());

        //최소길이
        String nick3 = "a";
        assertTrue(nickNamePattern.matcher(nick3).find());

        //최대길이
        String nick4 = "123사오육789십11끝";
        assertFalse(nickNamePattern.matcher(nick4).find());
    }

    @Test
    void phoneTest(){
        //하이픈 있을경우
        String p1 = "010-1234-5678";
        assertTrue(phonePattern.matcher(p1).find());

        //하이픈 없을경우
        String p2 ="01012345678";
        assertTrue(phonePattern.matcher(p2).find());

        // 01x로 시작시
        String p3 = "01212345678";
        assertFalse(phonePattern.matcher(p3).find());

        //가운데 3, 4 자리 테스트
        String p4 ="0101231234";
        assertTrue(phonePattern.matcher(p4).find());

    }

}
