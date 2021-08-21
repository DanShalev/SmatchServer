package com.tie.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tie.model.dao.User;
import com.tie.repository.GroupRepository;
import com.tie.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GroupComponentTest extends BaseComponentTest {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserRepository userRepository;

    ObjectMapper mapper = new ObjectMapper();


    @Test
    void createUser_success() throws Exception {
        User mockUser = new User("1", "Mark", "Male", 25, "pushNotification", "img1", "img2", "img3", null);
        String groupJson = mapper.writeValueAsString(mockUser);

        mockMvc.perform(post("/user/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(groupJson))
                .andExpect(status().isOk());

        List<User> users = userRepository.findAll();
        assertThat(users.size()).isEqualTo(1);
        User user = users.get(0);
        assertThat(user.getId()).isEqualTo("1");
        assertThat(user.getName()).isEqualTo("Mark");
        assertThat(user.getSex()).isEqualTo("Male");
        assertThat(user.getAge()).isEqualTo(25);
        assertThat(user.getPushNotificationToken()).isEqualTo("pushNotification");
        assertThat(user.getImage1()).isEqualTo("img1");
        assertThat(user.getImage2()).isEqualTo("img2");
        assertThat(user.getImage3()).isEqualTo("img3");
    }

}
