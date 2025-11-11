package com.itvitae.projectmanagement_backend.dto.project;

import java.util.List;

public record ProjectUpdateDTO(
   Long id,
   String title,
   List<Long> userIds
) {}
