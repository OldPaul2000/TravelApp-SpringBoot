package com.travelapp.travelapp.repository.testing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseTestUserRelated {

    private AppDAO appDAO;

    @Autowired
    public DatabaseTestUserRelated(AppDAO appDAO) {
        this.appDAO = appDAO;
    }

//    public void registerNewUser(){
//        User user = new User();
//        user.setUsername("Paul");
//        user.setPassword("paul1234");
//        user.setEnabled((byte)1);
//
//        Role role = new Role(Roles.USER.name());
//        role.setUser(user);
//        user.addRole(role);
//
//        UserInfo userInfo = new UserInfo();
//        userInfo.setFirstName("Paul");
//        userInfo.setLastName("Batrin");
//        userInfo.setEmail("paulbatrin@yahoo.com");
//        userInfo.setBirthDate(LocalDate.of(2003, 02, 07));
//        userInfo.setRegistrationDate(LocalDateTime.now());
//        user.setUserInfo(userInfo);
//
//        ProfilePicture profilePicture = new ProfilePicture();
//        profilePicture.setFileName("bk424k1-c124-12ccc-cc213.jpg");
//
//        userInfo.setProfilePicture(profilePicture);
//
//        appDAO.addNewEntry(user);
//    }
//
//    public void addNewRoleToUser(){
//        User user = appDAO.findUserAndRolesById(1);
//        Role role = new Role(Roles.ADMIN.name());
//        user.addRole(role);
//        role.setUser(user);
//
//        appDAO.updateEntry(user);
//    }
//
//    @Transactional
//    public void removeRoleFromUser() {
//        User user = appDAO.findUserAndRolesById(1);
//        Role role = user.getRoles().get(0);
//        user.getRoles().remove(0);
//
//        appDAO.deleteEntry(role);
//    }
//
//    @Transactional
//    public void deleteUser(){
//        User user = appDAO.findUserById(2);
//
//        appDAO.deleteEntry(user);
//    }
//
//    public void findUserByFullName(String firstName, String lastName){
//        User user = appDAO.findUserByFullName(firstName, lastName);
//        System.out.println(user);
//        System.out.println(user.getUserInfo());
//    }
//
//    @Transactional
//    public void updateUserInfo(){
//        User user = (User) appDAO.findById(User.class, 1);
//        UserInfo userInfo = user.getUserInfo();
//        userInfo.setLastName("Molnar");
//
//        appDAO.updateEntry(user);
//    }
//
//    @Transactional
//    public void updateUserProfilePic(){
//        User user = appDAO.findUserById(2);
//        ProfilePicture profilePicture = user.getUserInfo().getProfilePicture();
//        profilePicture.setFileName("this-is-the-new-file-name-v2.jpg");
//
//        appDAO.updateEntry(profilePicture);
//    }
//
//    public void getUser(int id){
//        User user = (User)appDAO.findById(User.class, id);
//        System.out.println(user);
//        System.out.println(user.getUserInfo());
//        System.out.println(user.getRoles());
//    }



}
