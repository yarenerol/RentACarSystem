package Models.Users;

import Models.Enums.UserRoles;

public class User {

    private long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private UserRoles userRole;
    private Integer age;
    private String companyName;



    //DAO katmanında kullanmak üzere boş constructor oluşturalım.


    //Bir de yeni user nesnesi oluşturmak için constructor oluşturalım.


    public User() {
    }

    public User(String name, String surname, String email, String password, UserRoles userRole, Integer age, String companyName) {

        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.userRole = userRole;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRoles getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoles userRole) {
        this.userRole = userRole;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
