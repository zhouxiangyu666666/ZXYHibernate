package src;

import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jeremy on 2017/7/27.
 */
@Controller
public class loginController extends HttpServlet{
   @RequestMapping(value = "login.do")
    public void login(HttpServletRequest req,HttpServletResponse resp){

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        loginService service = new loginService();
        Result result=service.login(username,password);

        Map<String,Object> map = new HashMap<>();

        map.put("result",result.getCode());
        if (result.getCode()==-1){
            Map<String,Object> errorMap = new HashMap<>();
            errorMap.put("error","username is not already exist");
            map.put("des",errorMap);
        }
        if (result.getCode()==-2){
            Map<String,Object> errorMap = new HashMap<>();
            errorMap.put("error","password is error");
            map.put("des",errorMap);
        }

       JSONObject MyJson = JSONObject.fromObject(map);

       try {
           PrintWriter printwriter = resp.getWriter();
           String LoginResult = MyJson.toString();
           printwriter.write(LoginResult);
       } catch (IOException e) {
           e.printStackTrace();
       }

   }
   @RequestMapping(value = "register")
    public void register(HttpServletRequest req,HttpServletResponse resp){
        String username= req.getParameter("username");
        String password= req.getParameter("password");

        RegisterService service = new RegisterService();
        Result result= service.save(username,password);
        Map<String,Object> map = new HashMap<>();
        map.put("result",result.getCode());
        if (result.getCode()==-1){
            Map<String,Object> errorMap = new HashMap<>();
            errorMap.put("error","username is already exist");
            map.put("des",errorMap);
        }
       JSONObject MyJson  =  JSONObject.fromObject(map);
       try {
           PrintWriter printWriter = resp.getWriter();
           String RegisterResult = MyJson.toString();
           printWriter.write(RegisterResult);
       }
       catch (IOException e){
           e.printStackTrace();
       }
   }

}
