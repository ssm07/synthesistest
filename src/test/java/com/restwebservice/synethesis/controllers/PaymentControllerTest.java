package com.restwebservice.synethesis.controllers;

import com.restwebservice.synethesis.beans.Payment;
import com.restwebservice.synethesis.beans.User;
import com.restwebservice.synethesis.services.PaymentService;
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

import java.util.Calendar;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    PaymentService paymentService;



    @Test
    public void getPaymentTest() throws Exception{
        String cvv="123";
        String creditCard="1234567890123456";
        String address="6904 knight street";
        Integer paymentId=90;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Payment  payment=  new Payment(paymentId,address,creditCard ,cvv,calendar.getTime());
        when(paymentService.getPayment(paymentId)).thenReturn( Optional.of(payment));
        RequestBuilder request= MockMvcRequestBuilders.get("/payment/{id}",paymentId).accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult= mockMvc.perform(request).
                andExpect(content().json("{paymentId:90,cvv:123}")).
                andReturn();
        mvcResult.getResponse();
    }

     private void validateField(Payment payment) throws  Exception{

         when(paymentService.createPayment(payment)).thenReturn(payment);
         RequestBuilder request= MockMvcRequestBuilders.post("/payment").
                 accept(MediaType.APPLICATION_JSON).content(Utils.asJsonString(payment)).
                 contentType(MediaType.APPLICATION_JSON).
                 characterEncoding("utf-8");
         MvcResult mvcResult = mockMvc.perform(request).andReturn();
         MockHttpServletResponse response = mvcResult.getResponse();
         assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
     }

     //address can not be null
    @Test
    public void addressValidationTest() throws  Exception{

        String cvv="123";
        String creditCard="1234567890123456";
        String address=null;
        Integer paymentId=90;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Payment  payment=  new Payment(paymentId,address,creditCard ,cvv,calendar.getTime());
        validateField(payment);

    }

    //credit card length should be 13 -16
    @Test
    public void creditValidationTest() throws  Exception{

        String cvv="123";
        String creditCard="1234567890";
        String address="6904 knight street";
        Integer paymentId=90;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Payment  payment=  new Payment(paymentId,address,creditCard ,cvv,calendar.getTime());
        validateField(payment);

    }

      // cvv length should be 3-4 character long
    @Test
    public void cvvValidationTest() throws  Exception{

        String cvv="12345";
        String creditCard="1234567890123456";
        String address="6904 knight street";
        Integer paymentId=90;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Payment  payment=  new Payment(paymentId,address,creditCard ,cvv,calendar.getTime());
        validateField(payment);

    }

    //expiry date can not be past dated
    @Test
    public void expiryDateValidationTest() throws  Exception{

        String cvv="123";
        String creditCard="1234567890123456";
        String address="6904 knight street";
        Integer paymentId=90;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -10);
        Payment  payment=  new Payment(paymentId,address,creditCard ,cvv,calendar.getTime());
        validateField(payment);

    }
}
