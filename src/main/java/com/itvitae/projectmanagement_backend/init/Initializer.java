package com.itvitae.projectmanagement_backend.init;

import com.itvitae.projectmanagement_backend.enums.Role;
import com.itvitae.projectmanagement_backend.enums.Status;
import com.itvitae.projectmanagement_backend.models.Comment;
import com.itvitae.projectmanagement_backend.models.Project;
import com.itvitae.projectmanagement_backend.models.Task;
import com.itvitae.projectmanagement_backend.models.User;
import com.itvitae.projectmanagement_backend.repositories.CommentRepository;
import com.itvitae.projectmanagement_backend.repositories.ProjectRepository;
import com.itvitae.projectmanagement_backend.repositories.TaskRepository;
import com.itvitae.projectmanagement_backend.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Initializer {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;
    private final PasswordEncoder passwordEncoder;

    public Initializer(UserRepository userRepository, ProjectRepository projectRepository, TaskRepository taskRepository, CommentRepository commentRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.commentRepository = commentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        if (userRepository.count() > 0) return;

        User leader = new User("alice", "one", "alice@example.com", passwordEncoder.encode("password123"), Role.PROJECT_LEADER);
        User dev = new User("Bob", "builder", "charlie@example.com", passwordEncoder.encode("pass"), Role.DEVELOPER);
        User customer = new User("charly", "eeee", "notaspamemail@spam.com", passwordEncoder.encode("aaa"), Role.CUSTOMER);
        userRepository.saveAll(List.of(leader, dev, customer));


        Project project = new Project();
        project.setTitle("Project Test System");
        project.setUsers(List.of(leader, dev, customer));
        projectRepository.save(project);

        Task task1 = new Task("make it work", "make the backend work i will sacrifice my soul ;)", leader);
        task1.setStatus(Status.IN_PROGRESS);
        task1.setProject(project);

        Task task2 = new Task("do something", "hahaha descriting", dev);
        task2.setStatus(Status.TODO);
        task2.setProject(project);

        taskRepository.saveAll(List.of(task1, task2));

        Comment comment1 = new Comment("it looks like shit", leader, task1);
        Comment comment2 = new Comment("boooo its not working anymore :(", dev, task2);
        commentRepository.saveAll(List.of(comment1, comment2));

        System.out.println("system intialized!");
    }
}
