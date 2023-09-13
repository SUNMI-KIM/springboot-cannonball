package cannonball.cannonball.Domain;

import org.springframework.security.crypto.password.PasswordEncoder;

public class Profile {
    private String classNum;
    private String name;
    private String gender;
    private String passWord;
    private String phoneNum;
    private String className;

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Profile hashPassWord(PasswordEncoder passwordEncoder) {
        this.passWord = passwordEncoder.encode(this.passWord);
        return this;
    }

    public boolean checkPassWord(String plainPassword, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(plainPassword, this.passWord);
    }
}
