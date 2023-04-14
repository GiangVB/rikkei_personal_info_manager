package vn.rikkei.personalinfomanager.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import vn.rikkei.personalinfomanager.validation.ValidEmail;
import vn.rikkei.personalinfomanager.validation.ValidIdNumber;
import vn.rikkei.personalinfomanager.validation.ValidPasscode;
import vn.rikkei.personalinfomanager.validation.ValidPhoneNumber;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    public User(String email, String passcode) {
        this.email = email;
        this.passcode = passcode;
    }

    public User(String fullName, boolean sex, Date birthday, String phoneNumber, String address, String idNumber) {
        this.fullName = fullName;
        this.sex = sex;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.idNumber = idNumber;
    }

    private Integer userId;
    private String email;
    private String passcode;
    private String fullName;
    private boolean sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String phoneNumber;
    private String address;
    private String idNumber;
    private String avatar;
}
