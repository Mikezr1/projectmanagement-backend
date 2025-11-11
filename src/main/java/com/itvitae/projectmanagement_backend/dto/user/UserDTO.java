//package com.itvitae.projectmanagement_backend.dto.user;
//
//import com.itvitae.projectmanagement_backend.dto.comment.CommentSummaryDTO;
//import com.itvitae.projectmanagement_backend.dto.project.ProjectSummaryDTO;
//import com.itvitae.projectmanagement_backend.dto.task.TaskSummaryDTO;
//import com.itvitae.projectmanagement_backend.enums.Role;
//import com.itvitae.projectmanagement_backend.models.User;
//
//import java.util.List;
//
//public record UserDTO(
//        Long id,
//        Role role,
//        String firstName,
//        String lastName,
//        String email,
//        String companyName,
//        List<CommentSummaryDTO> commentDTOs,
//        List<ProjectSummaryDTO> projectDTOs,
//        List<TaskSummaryDTO> taskDTOs
//
//) {
//    public static UserDTO fromEntity(User user) {
//        //DIT WERKT NIET!
//        List<CommentSummaryDTO> commentDTOs = user.getComments()
//                .stream()
//                //DIT HOORD COMMENTMAPPER tezijn
//                .map(CommentSummaryDTO::fromEntity)
//                .toList();
//        List<ProjectSummaryDTO> projectDTOs = user.getProjects()
//                .stream()
//                .map(ProjectSummaryDTO::fromEntity)
//                .toList();
//        List<TaskSummaryDTO> taskDTOs = user.getTasks()
//                .stream()
//                .map(TaskSummaryDTO::fromEntity)
//                .toList();
//
//        return new UserDTO(
//                user.getId(),
//                user.getRole(),
//                user.getFirstName(),
//                user.getLastName(),
//                user.getEmail(),
//                user.getCompanyName(),
//                commentDTOs,
//                projectDTOs,
//                taskDTOs
//        );
//    }
//}
