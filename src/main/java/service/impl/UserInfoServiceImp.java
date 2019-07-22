package service.impl;

import bean.UserInfoBean;
import com.sun.mail.util.MailSSLSocketFactory;
import dao.UserInfoDAO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import service.UserInfoService;

import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class UserInfoServiceImp implements UserInfoService {
    @Resource
    private UserInfoDAO userInfoDao;
    /**
     * smtp服务器
     */
    private String host = "";
    /**
     * 发件人地址
     */
    private String from = "";
    /**
     * 收件人地址
     */
    private String to = "";
    /**
     * 用户名
     */
    private String user = "";
    /**
     * 密码
     */
    private String pwd = "";
    /**
     * 邮件标题
     */
    private String subject = "";

    @Override
    public UserInfoBean getUserByUsernameAndPassword(UserInfoBean user) {
        return userInfoDao.selectById(user);
    }

    @Override
    @CacheEvict(value = "user", key = "'user_'+#userInfoBean.getId()")
    public Integer updateUserInfo(UserInfoBean userInfoBean) {
        Integer result = userInfoDao.updateUserInfo(userInfoBean);
        return result;
    }

    @Override
    public Integer updateStatusByUsername(String username) {
        return userInfoDao.updateStatusByUsername(username);
    }

    @Override
    public void setSend(String host, String user, String pwd, String activeUsername, String activeEmail, String url)
            throws GeneralSecurityException {
        this.host = host;
        this.user = user;
        this.pwd = pwd;
        Properties props = new Properties();
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);
        // 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
        props.put("mail.smtp.host", host);
        // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
        props.put("mail.smtp.auth", "true");

        // 用刚刚设置好的props对象构建一个session
        Session session = Session.getDefaultInstance(props);

        // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
        // 用（你可以在控制台（console)上看到发送邮件的过程）
        session.setDebug(true);
        // 用session为参数定义消息对象
        MimeMessage message = new MimeMessage(session);
        try {
            // 加载发件人地址
            message.setFrom(new InternetAddress(from));
            // 加载收件人地址
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(activeEmail));
            // 加载标题
            message.setSubject(subject);

            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();

            // 设置邮件的文本内容
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setText("点击地址进入激活" + url + activeUsername);
            multipart.addBodyPart(contentPart);

            // 将multipart对象放到message中
            message.setContent(multipart);
            // 保存邮件
            message.saveChanges();
            // 发送邮件
            Transport transport = session.getTransport("smtp");
            // 连接服务器的邮箱
            transport.connect(host, user, pwd);
            // 把邮件发送出去
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
            //邮件发送失败删除注册用户
//        UpdateUserStatusServiceImp updateStatus=new UpdateUserStatusServiceImp();
//        updateStatus.deleteUser(tempUser);
//        jsonObject.put("result", 0);
//        jsonObject.put("msg","激活邮件发送失败，邮箱不可用或账户错误，请重新注册");
        }

    }

    public void setAddress(String from, String to, String subject) {
        this.from = from;
        this.to = to;
        this.subject = subject;
    }

    @Override
    public boolean checkUserName(String username) {
        UserInfoBean userInfoBean = userInfoDao.getUserByUsername(username);
        if (userInfoBean == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean saveUser(UserInfoBean userInfoBean) {
        Integer result = userInfoDao.saveUser(userInfoBean);
        if (result == 1) {
            return true;
        }
        return false;
    }

    @Override
    public Integer deleteUser(String username) {
        return null;
    }

    @Cacheable(value = "user", key = "'user_'+#userId.toString()")
    @Override
    public List<UserInfoBean> getUserUserInfoBeanById(Long userId) {
        UserInfoBean userInfoBean =userInfoDao.getUserById(userId);
        List<UserInfoBean> userInfoBeans = new ArrayList<>();
        userInfoBeans.add(userInfoBean);
        return userInfoBeans;
    }
}
