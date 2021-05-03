package cn.edu.zzu.oj;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public class UserApi {
    String SERVICE_NAME = "zzuoj-user";

//    /**
//     * 根据username查询userid
//     * @param username
//     */
//    @GetMapping("/usernameToUserId")
//    Long usernameToUserId(@RequestParam("username") String username);
//
//    /**
//     * 根据userId查询username
//     * @param userId
//     */
//    @GetMapping("/userIdToUsername")
//    @Cacheable(key = "#userId", value = RedisConstants.USER_ID_TO_USERNAME)
//    String userIdToUsername(@RequestParam("userId") Long userId);
//
//    /**
//     * 根据userId查询nickname
//     * @param userId
//     */
//    @GetMapping("/userIdToNickname")
//    @Cacheable(key = "#userId", value = RedisConstants.USER_ID_TO_NICKNAME)
//    String userIdToNickname(@RequestParam("userId") Long userId);

    /**
     * @Description 查询具体用户权限
     **/
//    @GetMapping("/userIdToRoles")
//    List<Integer> queryRolesById(@RequestParam("userId") Long userId);

//    /**
//     * 根据用户名和密码查询用户
//     * @param map {"username": "", "password": ""}
//     */
//    @PostMapping(value = "/verify", consumes = "application/json")
//    UserDTO verify(@RequestBody Map<String, String> map) throws InternalApiException;
//
//    /**
//     * 根据用户id查询用户
//     * @param userId
//     */
//    @GetMapping("/queryById")
//    UserDTO query(@RequestParam("userId") Long userId) throws InternalApiException;
//
//    /**
//     * 查询 userId->username 的全量 map
//     */
//    @GetMapping("/queryIdToUsernameMap")
//    Map<Long, String> queryIdToNameMap() throws InternalApiException;
//
//    /**
//     * @Description 新增用户参加比赛
//     * @param userId
//     * @param contestId
//     **/
//    @GetMapping("/addUserParticipateContest")
//    void addUserParticipateContest(@RequestParam("userId") long userId,
//                                   @RequestParam("contestId") long contestId);

}
