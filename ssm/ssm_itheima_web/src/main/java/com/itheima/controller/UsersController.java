package com.itheima.controller;

import com.itheima.domain.PageBean;
import com.itheima.domain.Users;
import com.itheima.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping("/login")
    public String login(Users users,HttpServletRequest request, Model model, HttpSession session, String ck , HttpServletResponse response, MultipartFile headimg) throws IOException {
        //验证用户是否存在
        Users u = usersService.login(users);
        if ( u == null ){
            model.addAttribute("msg","用户名或密码错误");
            return "login";
        }
        //获取上传路径
        String uploadPath = session.getServletContext().getRealPath("/uploads/");
        //判断当前文件夹是否存在不存在则创建
        File file = new File(uploadPath);
        if (!file.exists()){
            file.mkdirs();
        }
        //判断headimg是否为空
        if (!headimg.isEmpty()){
            //获取上传头像的name
            String filename = headimg.getOriginalFilename();
            //设置唯一id
            String uuid = UUID.randomUUID().toString().replace("-", "");
            filename = uuid+"-"+filename;
            //把图片的路径存储到user对象中
           String uri = request.getContextPath()+"/uploads/"+filename;
           ///ssm/uploads/3eff1a7225764880b2c23ff6f92b505b-QQ图片20181222144706.jpg

           u.setFileurl(uri);
           usersService.updateUserMsg(u);
            //上传文件
            headimg.transferTo(new File(uploadPath,filename));
        }

        session.setAttribute("user",u);
        Cookie cookieUsername = new Cookie("username",u.getUsername());
        Cookie cookiePassword = new Cookie("password",u.getPassword());
        Cookie  cookieHeader = new Cookie("header",u.getFileurl());
       if ("1".equals(ck)){
          cookieUsername.setMaxAge(60*60*24*7);
          cookiePassword.setMaxAge(60*60*24*7);
       }else{
            cookiePassword.setMaxAge(0);
            cookieUsername.setMaxAge(0);
       }
        cookieHeader.setMaxAge(60*60*24*7);
        cookiePassword.setPath("/");
        cookieUsername.setPath("/");
        cookieHeader.setPath("/");
        response.addCookie(cookieUsername);
        response.addCookie(cookiePassword);
        response.addCookie(cookieHeader);
       return "index1";
    }

    /**
     * 查询所有用户信息
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll(Model model){
       List<Users> u =  usersService.findAll();
       model.addAttribute("users",u);
       return "list";
    }

    /**
     * 根据id查询用户信息
     */
    @RequestMapping("/findById/{id}")
    public String findById(@PathVariable("id")Integer id,Model model){
       Users u =  usersService.findById(id);
       model.addAttribute("users",u);
       return "update";
    }

    /**
     * 修改用户信息
     * @param users
     * @return
     */
    @RequestMapping("/updateMsg")
    public String updateUserMsg(Users users){
         usersService.updateUserMsg(users);
        return "redirect:/users/findByPage";
    }

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    @RequestMapping("/deleteUser/{id}")
    public String delete(@PathVariable("id") Integer id){
       usersService.delete(id);
        return "redirect:/users/findByPage";
    }

    /**
     * 添加用户信息
     * @param users
     * @return
     */
    @RequestMapping("/addUser")
    public String addUser(Users users){
        usersService.addUser(users);
        return "redirect:/users/findByPage";
    }

    /**
     * 分页查询
     */
    @RequestMapping("/findByPage")
    public String findByPage(String currentPage,String pageRows,Model model,PageBean pageBean){

       PageBean pb =  usersService.findByPage(currentPage,pageRows,pageBean);

       model.addAttribute("pb",pb);
        return "list";
    }

    /**
     * 删除多个
     * @param request
     */
    @RequestMapping("deleteMore")
    public @ResponseBody void  deleteMore(HttpServletRequest request){
        String[] uids = request.getParameterValues("uid");
        System.out.println(uids.length);
        usersService.deleteMore(uids);
    }
    @RequestMapping("/exit")
    public String exit(HttpSession session){
        session.removeAttribute("user");
        return "login";
    }
}
