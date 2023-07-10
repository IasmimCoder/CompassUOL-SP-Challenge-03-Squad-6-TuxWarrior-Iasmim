package br.com.compassuol.pb.challenge.users.service;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.compassuol.pb.challenge.users.exceptions.BusinnesException;
import br.com.compassuol.pb.challenge.users.exceptions.EntityAlreadyExistsException;
import br.com.compassuol.pb.challenge.users.exceptions.EntityNotFoundException;
import br.com.compassuol.pb.challenge.users.mapper.RoleMapper;
import br.com.compassuol.pb.challenge.users.model.RoleModel;
import br.com.compassuol.pb.challenge.users.model.dto.RoleDTO;
import br.com.compassuol.pb.challenge.users.model.dto.UserDTO;
import br.com.compassuol.pb.challenge.users.repository.RoleRepository;
import br.com.compassuol.pb.challenge.users.utils.MessageUtils;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleService {
    
    @Autowired
    private RoleRepository repository;

    @Autowired
    private RoleMapper mapper;

    @Transactional
    public RoleDTO save(RoleDTO dto) {
        Optional<RoleModel> optional = repository.findByName(dto.getName());
        if (optional.isPresent()) {
            throw new EntityAlreadyExistsException("Role" + MessageUtils.ALREADY_EXIST);
        }
        RoleModel model = mapper.toEntity(dto);
        repository.save(model);
        return mapper.toDto(model);
    }

    @Transactional(readOnly = true)
    public List<RoleDTO> findAll() {
        return mapper.toDto(repository.findAll());
    }

    @Transactional(readOnly = true)
    public RoleDTO findById(String id) {
        return repository.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Role" + MessageUtils.NO_RECORDS_FOUND));
    }

    @Transactional(readOnly = true)
    public RoleDTO findByName(String name) {
        return repository.findByName(name).map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Role" + MessageUtils.NO_RECORDS_FOUND));
    }



}
