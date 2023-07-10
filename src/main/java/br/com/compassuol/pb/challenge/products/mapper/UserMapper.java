package br.com.compassuol.pb.challenge.users.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import br.com.compassuol.pb.challenge.users.model.RoleModel;
import br.com.compassuol.pb.challenge.users.model.UserModel;
import br.com.compassuol.pb.challenge.users.model.dto.CreateUserDTO;
import br.com.compassuol.pb.challenge.users.model.dto.UpdateUserDTO;
import br.com.compassuol.pb.challenge.users.model.dto.UserDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserModel toEntity(CreateUserDTO dto, List<RoleModel> list) {
        UserModel model = new UserModel();
        model.setFirstName(dto.getFirstName());
        model.setLastName(dto.getLastName());
        model.setEmail(dto.getEmail());
        model.setPassword(dto.getPassword());

        model.setRoles(list);
        return model;
    }

    public UserModel toEntity(UpdateUserDTO dto, UserDTO foundDto) {
        UserModel model = new UserModel();
        String firstName = dto.getFirstName() == null ? foundDto.getFirstName() : dto.getFirstName();
        String lastName = dto.getLastName() == null ? foundDto.getLastName() : dto.getLastName();
        String email = dto.getEmail() == null ? foundDto.getEmail() : dto.getEmail();
        String password = dto.getPassword() == null ? foundDto.getPassword() : dto.getPassword();

        model.setFirstName(firstName);
        model.setLastName(lastName);
        model.setEmail(email);
        model.setPassword(password);
        model.setRoles(foundDto.getRoles());;

        return model;
    }

    public UserDTO toDto(UserModel model) {
        UserDTO dto = new UserDTO();
        dto.setId(model.getUuid());
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setEmail(model.getEmail());
        dto.setPassword(model.getPassword());
        dto.setRoles(model.getRoles()); // Observar que o getCategories está trazendo todos os produtos
                                                  // listados nas categories, embora não esteja passando no response por
                                                  // causa do @JsonIgnore
        return dto;
    }

    public List<UserDTO> toDto(List<UserModel> listModels) {
        return listModels.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Page<UserDTO> toDto(Page<UserModel> listModels) {
        List<UserDTO> listDtos = listModels.getContent().stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(listDtos, listModels.getPageable(), listModels.getTotalElements());
    }
}