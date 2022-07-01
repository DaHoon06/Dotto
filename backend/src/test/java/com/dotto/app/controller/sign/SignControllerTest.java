package com.dotto.app.controller.sign;

import com.dotto.app.dto.sign.SignInRequest;
import com.dotto.app.dto.sign.SignUpRequest;
import com.dotto.app.factory.dto.SignInRequestFactory;
import com.dotto.app.factory.dto.SignInResponseFactory;
import com.dotto.app.factory.dto.SignUpRequestFactory;
import com.dotto.app.service.sign.SignService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.dotto.app.factory.dto.RefreshTokenResponseFactory.createRefreshTokenResponse;
import static com.dotto.app.factory.dto.SignUpRequestFactory.createSignUpRequest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class SignControllerTest {

    @InjectMocks SignController signController;
    @Mock
    SignService signService;

    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void beforeEach(){
        mockMvc = MockMvcBuilders.standaloneSetup(signController).build();
    }

    @Test
    void signUp() throws Exception{
        //given
        SignUpRequest req = createSignUpRequest();

        mockMvc.perform(
                post("/api/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
        )
                .andExpect(status().isCreated());

        verify(signService).SignUp(req);
    }

    @Test
    void signIn() throws Exception{
        //given
        SignInRequest req = SignInRequestFactory.createSignInRequest();
        given(signService.SignIn(req)).willReturn(SignInResponseFactory.createSignInResponse("access","refresh","nickname","roles"));

        //when
        mockMvc.perform(
                post("/api/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.data.accessToken").value("access"))
                .andExpect(jsonPath("$.result.data.refreshToken").value("refresh"));

        verify(signService).SignIn(req);
    }

    @Test
    void ignoreNullValueInJsonResponseTest() throws Exception{
        //given
        SignUpRequest req = createSignUpRequest();

        //when, then
        mockMvc.perform(
                post("/api/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.result").doesNotExist());
    }

    @Test
    void refreshTokenResponse() throws Exception{
        //given
        given(signService.refreshToken("refreshToken")).willReturn(createRefreshTokenResponse("accessToken"));

        //when, then
        mockMvc.perform(
                post("/api/refresh-token")
                .header("Authorization","refreshToken")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.data.accessToken").value("accessToken"));
    }
}