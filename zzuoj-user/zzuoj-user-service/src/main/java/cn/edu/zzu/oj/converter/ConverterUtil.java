package cn.edu.zzu.oj.converter;

import cn.edu.zzu.oj.entity.Privilege;
import cn.edu.zzu.oj.entity.User;
import cn.edu.zzu.oj.entity.frontToWeb.ProblemFront;
import cn.edu.zzu.oj.entity.webToFront.UserWeb;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.parsing.Problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConverterUtil {

    public static List<UserWeb> UserToUserWeb(List<User> users, List<Privilege> privileges){
        List<UserWeb> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(int i=0;i<privileges.size();i++){
            map.put(privileges.get(i).getUserId(), privileges.get(i).getRightstr());
        }

        for(int i = 0;i < users.size(); i++){
            User user = users.get(i);
            UserWeb userWeb = new UserWeb().setUserId(user.getUserId())
                                            .setAccesstime(user.getAccesstime())
                    .setDefunct(user.getDefunct())
                    .setEmail(user.getEmail())
                    .setIp(user.getIp())
                    .setNick(user.getNick())
                    .setRegTime(user.getRegTime())
                    .setSchool(user.getSchool())
                    .setSolved(user.getSolved())
                    .setSubmit(user.getSubmit());

            Integer temp = map.get(user.getUserId());
            userWeb.setRole(temp == null? 0:temp);

            res.add(userWeb);
        }
        return res;
    }
}
