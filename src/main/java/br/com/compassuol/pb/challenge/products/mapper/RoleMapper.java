package br.com.compassuol.pb.challenge.products.mapper;

import org.springframework.stereotype.Component;

import br.com.compassuol.pb.challenge.products.model.RoleModel;
import br.com.compassuol.pb.challenge.products.model.dto.RoleDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleMapper {

    public RoleModel toEntity(RoleDTO dto) {
        RoleModel model = new RoleModel();
        model.setName(dto.getName());
        return model;
    }

    public RoleDTO toDto(RoleModel model) {
        RoleDTO dto = new RoleDTO();
        dto.setId(model.getUuid());
        dto.setName(model.getName());
        return dto;
    }


    public List<RoleDTO> toDto(List<RoleModel> listModel) {
        return listModel.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }
}