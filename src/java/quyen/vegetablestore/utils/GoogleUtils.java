/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quyen.vegetablestore.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import quyen.vegetablestore.loginwithgoogle.Contants;
import quyen.vegetablestore.loginwithgoogle.GooglePojo;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author To Quyen Phan
 */
public class GoogleUtils {
    //dùng để gửi truy vấn tới google (đổi code sang access-token, lấy thông tin trong tài khoản google
    //Lấy các thông tin đã tạo trên google API
    public static String getToken(final String code) throws IOException{
        String response = Request.Post(Contants.GOOGLE_LINK_GET_TOKEN)//Post với Fluent API
                .bodyForm(Form.form().add("client_id", Contants.GOOGLE_CLIENT_ID)
                .add("client_secret", Contants.GOOGLE_CLIENT_SECRET)
                .add("redirect_uri", Contants.GOOGLE_REDIRECT_URI).add("code",code)
                .add("grant_type", Contants.GOOGLE_GRANT_TYPE).build()).execute().returnContent().asString();
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }
    
    public static GooglePojo getUserInfo(final String accessToken) throws IOException{
        String link = Contants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        GooglePojo googlePojo = new Gson().fromJson(response, GooglePojo.class);
        System.out.println(googlePojo);
        return googlePojo;
    }
}
