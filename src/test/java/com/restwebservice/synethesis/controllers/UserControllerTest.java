package com.restwebservice.synethesis.controllers;


import com.restwebservice.synethesis.beans.User;
import com.restwebservice.synethesis.services.UserService;
import com.restwebservice.synethesis.utils.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;



@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    @Test
     public void getUserTest() throws Exception{
      User user=  new User(1,"Saurabh","ssmoghe07@gmail.com"," 6904 knight street vancouver");
        when(userService.getUser(1)).thenReturn( Optional.of(user));
        RequestBuilder request= MockMvcRequestBuilders.get("/user/{id}",1).accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult= mockMvc.perform(request).
               andExpect(content().json("{userId:1,name:Saurabh}")).
               andReturn();
       mvcResult.getResponse();
    }
    /**
     * <p>  Following method test validation of  name.
     *  Name should be at least 2 character long
     * </p>
     * */
    @Test
    public void userNameValidationTest()  throws  Exception  {
        User user=new User(90,"s","ssm@gmail.com"," downtown");
        when(userService.createUser(user)).thenReturn(user);
        RequestBuilder request= MockMvcRequestBuilders.post("/user").
                accept(MediaType.APPLICATION_JSON).content(Utils.asJsonString(user)).
                 contentType(MediaType.APPLICATION_JSON).
                characterEncoding("utf-8");
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());

    }

    /**
     * <p>  Following method test validation of  email.
     *
     * </p>
     * */
    @Test
    public void userEmailValidationTest()  throws  Exception  {
        User user=new User(90,"saurabh","ssm.com"," downtown");
        when(userService.createUser(user)).thenReturn(user);
        RequestBuilder request= MockMvcRequestBuilders.post("/user").
                accept(MediaType.APPLICATION_JSON).content(Utils.asJsonString(user)).
                contentType(MediaType.APPLICATION_JSON).
                characterEncoding("utf-8");
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());

    }


    /**
     * <p>  Following method validate  address. Address can not be null.
     *
     * </p>
     * */
    @Test
    public void userAddressValidationTest()  throws  Exception  {
        User user=new User(90,"saurabh","ssmoghe07@gmail.com",null);
        when(userService.createUser(user)).thenReturn(user);
        RequestBuilder request= MockMvcRequestBuilders.post("/user").
                accept(MediaType.APPLICATION_JSON).content(Utils.asJsonString(user)).
                contentType(MediaType.APPLICATION_JSON).
                characterEncoding("utf-8");
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());

    }


    /**
     * <p>  Following method validate  address. Address can not be null.
     *
     * </p>
     * */
    @Test
    public void createUserTest()  throws  Exception  {
        User user=new User(90,"saurabh","ssmoghe07@gmail.com","6904 knight street");
        when(userService.createUser(user)).thenReturn(user);
        RequestBuilder request= MockMvcRequestBuilders.post("/user").
                accept(MediaType.APPLICATION_JSON).content(Utils.asJsonString(user)).
                contentType(MediaType.APPLICATION_JSON).
                characterEncoding("utf-8");
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

    }




}
