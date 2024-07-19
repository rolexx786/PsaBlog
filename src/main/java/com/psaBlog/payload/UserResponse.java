package com.psaBlog.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private List<UserDto> userDtoList;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElement;
    private boolean lastPage;
    private Integer totalPage;

}
