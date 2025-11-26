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

        userService.createUser(new UserCreateDTO("Alice", "de Boer", "alice@example.com", "password123", Role.PROJECT_LEADER, "MacroHard"));
        userService.createUser(new UserCreateDTO("Bob", "de Bouwer", "bob@example.com", "pass", Role.DEVELOPER, "MacroHard"));
        userService.createUser(new UserCreateDTO("Charly", "van Huizen", "notaspamemail@spam.com", "aaa", Role.DEVELOPER, "MacroHard"));
        userService.createUser(new UserCreateDTO("Sam","Kniepert","samkniepert@liamtoh.com","lolerpants",Role.PROJECT_LEADER,"DevOpps"));
        userService.createUser(new UserCreateDTO("Daniel","Haazen","daniel.haazen@code-cafe.nl","daniel",Role.CUSTOMER,"Code-Cafe"));
        userService.createUser(new UserCreateDTO("Joris","van Breugel","joris.vanbreugel@code-cafe.nl","joris",Role.CUSTOMER,"Code-Cafe"));
        userService.createUser(new UserCreateDTO("Thomas", "Demo","thomas_demo@projectpro.com","thomas",Role.DEVELOPER,"Project Pro"));
        userService.createUser(new UserCreateDTO("Stefan", "Demo","stefan_demo@projectpro.com","stefan",Role.DEVELOPER,"Project Pro"));
        userService.createUser(new UserCreateDTO("Chun", "Demo","chun_demo@projectpro.com","chun",Role.DEVELOPER,"Project Pro"));
        userService.createUser(new UserCreateDTO("Mike", "Demo","mike_demo@projectpro.com","mike",Role.DEVELOPER,"Project Pro"));

        projectService.createProject(new ProjectCreateDTO("Coolblue website redesign", 1L));
        projectService.createProject(new ProjectCreateDTO("Campina app upgrade to v2.1", 1L));
        projectService.createProject(new ProjectCreateDTO("Philips app bug fixes", 1L));
        projectService.createProject(new ProjectCreateDTO("Bol.com website upgrade", 1L));


        projectService.addUsersToProject(1L, List.of(2L, 3L, 9L));
        projectService.addUsersToProject(2L, List.of(2L, 3L));
        projectService.addUsersToProject(3L, List.of(2L));

        taskService.createTask(new TaskCreateDTO("Backend","The backend work I will sacrifice my soul ;)",Status.IN_PROGRESS,3L,1L));
        taskService.createTask(new TaskCreateDTO("Toolbar","We want a customized Toolbar.",Status.TODO,2L,1L));
        taskService.createTask(new TaskCreateDTO("Sidebar","We want a customized Sidebar.",Status.TODO,3L,1L));
        taskService.createTask(new TaskCreateDTO("Navbar","We want a customized Navbar.",Status.TODO,9L,1L));
        taskService.createTask(new TaskCreateDTO("Logo","Our logo needs more flair.", Status.IN_PROGRESS,3L,2L));
        taskService.createTask(new TaskCreateDTO("Bug fixes","Testing our backend ofr bug fixes.",Status.IN_PROGRESS,4L,3L));

        commentService.createComment(new CommentCreateDTO("De eerste versie van de feature ziet er goed uit, goed werk!", 1L,1L));
        commentService.createComment(new CommentCreateDTO("Ik heb de implementatie bekeken; alles lijkt correct te functioneren.", 1L,2L));
        commentService.createComment(new CommentCreateDTO("Code review afgerond, geen kritische issues gevonden.", 1L,2L));

        commentService.createComment(new CommentCreateDTO("Er lijkt een fout op te treden bij het opslaan van gegevens, dit vereist nader onderzoek.", 2L,1L));
        commentService.createComment(new CommentCreateDTO("Backend respons is momenteel niet consistent, mogelijk een race condition.", 2L,2L));
        commentService.createComment(new CommentCreateDTO("Foutmelding gecontroleerd, ik zal een fix voorstellen.", 2L,3L));

        commentService.createComment(new CommentCreateDTO("De workflow faalt bij edge cases, moeten we unit tests toevoegen?", 3L,1L));
        commentService.createComment(new CommentCreateDTO("Testcases lopen niet door, kan iemand dit nakijken?", 3L,2L));
        commentService.createComment(new CommentCreateDTO("Probleem gereproduceerd, issue aangemaakt in Jira.", 3L,3L));

        commentService.createComment(new CommentCreateDTO("De laatste commit lijkt de bug te verhelpen, graag testen.", 4L,1L));
        commentService.createComment(new CommentCreateDTO("Test uitgevoerd, functionaliteit werkt nu zoals verwacht.", 4L,2L));
        commentService.createComment(new CommentCreateDTO("Alles gecontroleerd, code kan gemerged worden naar main.", 4L,3L));

        System.out.println("system initialized!");
    }
}
