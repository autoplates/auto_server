package org.auto.plate.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-04-09 23:32:11
 */

public class User implements Serializable, UserDetails {
    private static final long serialVersionUID = -81188813556233696L;
    
    private Integer id;
    private String username;
    private String password;
    private String company;
    private String address;
    private Date createdate;

    /**
     * 获取用户名
     * @return
     */
    @Override
    public String getUsername() {
        return this.username;
    }
    /**
     * 账户是否未过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    /**
     * 账户是否未锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    /**
     * 凭证是否未过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     *账户是否可用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
    /**
     * 获取用户密码
     * @return
     */
    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @JsonFormat(timezone = "yyyy-MM-dd")
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

}