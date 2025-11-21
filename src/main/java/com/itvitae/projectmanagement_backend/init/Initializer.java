package com.itvitae.projectmanagement_backend.init;

import com.itvitae.projectmanagement_backend.dto.comment.CommentCreateDTO;
import com.itvitae.projectmanagement_backend.dto.project.ProjectCreateDTO;
import com.itvitae.projectmanagement_backend.dto.task.TaskCreateDTO;
import com.itvitae.projectmanagement_backend.dto.user.UserCreateDTO;
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
import com.itvitae.projectmanagement_backend.services.CommentService;
import com.itvitae.projectmanagement_backend.services.ProjectService;
import com.itvitae.projectmanagement_backend.services.TaskService;
import com.itvitae.projectmanagement_backend.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Initializer {

    private final UserRepository userRepository;
    private final UserService userService;
    private final ProjectRepository projectRepository;
    private final ProjectService projectService;
    private final TaskRepository taskRepository;
    private final TaskService taskService;
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    public Initializer(UserRepository userRepository, UserService userService, ProjectRepository projectRepository, ProjectService projectService, TaskRepository taskRepository, TaskService taskService, CommentRepository commentRepository, CommentService commentService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.projectRepository = projectRepository;
        this.projectService = projectService;
        this.taskRepository = taskRepository;
        this.taskService = taskService;
        this.commentRepository = commentRepository;
        this.commentService = commentService;
    }

    @PostConstruct
    public void init() {
        if (userRepository.count() > 0) return;

        userService.createUser(new UserCreateDTO("alice", "one", "alice@example.com", "password123", Role.PROJECT_LEADER, "MacroHard"));
        userService.createUser(new UserCreateDTO("Bob", "theBuilder", "btb@example.com", "pass", Role.DEVELOPER, "MacroHard"));
        userService.createUser(new UserCreateDTO("charly", "eee", "notaspamemail@spam.com", "aaa", Role.DEVELOPER, "MacroHard"));
        userService.createUser(new UserCreateDTO("samohT","kninneW","samohtkninnew@liamtoh.com","lolerpants",Role.PROJECT_LEADER,"Wennink 'n' Co"));
        userService.createUser(new UserCreateDTO("Daniel","Haazen","daniel.haazen@code-cafe.nl","daniel",Role.CUSTOMER,"Code-Cafe"));
        userService.createUser(new UserCreateDTO("Joris","van Breugel","joris.vanbreugel@code-cafe.nl","joris",Role.CUSTOMER,"Code-Cafe"));

        projectService.createProject(new ProjectCreateDTO("Project Test System", 1L));
        projectService.createProject(new ProjectCreateDTO("How to become a better WebDesigner", 1L));
        projectService.createProject(new ProjectCreateDTO("Testing", 4L));

        projectService.addUsersToProject(1L, List.of(2L, 3L));
        projectService.addUsersToProject(2L, List.of(2L, 3L));
        projectService.addUsersToProject(3L, List.of(2L));

        taskService.createTask(new TaskCreateDTO("make it work","make the backend work i will sacrifice my soul ;)",Status.IN_PROGRESS,1L,1L));
        taskService.createTask(new TaskCreateDTO("do something","Customize Toolbar…",Status.TODO,2L,1L));
        taskService.createTask(new TaskCreateDTO("HELP PLS","We need help. How do lol?", Status.IN_PROGRESS,3L,2L));
        taskService.createTask(new TaskCreateDTO("Test 1","Testing lol.",Status.IN_PROGRESS,4L,3L));

        commentService.createComment(new CommentCreateDTO("it looks like shit", 1L,1L));
        commentService.createComment(new CommentCreateDTO("boooo its not working anymore :(", 2L,1L));

        System.out.println("system initialized!");
    }
}
