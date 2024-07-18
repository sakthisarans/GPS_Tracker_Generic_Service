package com.sakthi.genericServices.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter@Setter@Builder
public class ResourceSavedResponse {
    boolean isSaved;
    String URI;
}
