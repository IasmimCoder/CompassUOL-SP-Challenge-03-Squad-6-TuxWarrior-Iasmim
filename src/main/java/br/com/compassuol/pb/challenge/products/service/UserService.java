package br.com.compassuol.pb.challenge.users.service;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.compassuol.pb.challenge.users.exceptions.EntityAlreadyExistsException;
import br.com.compassuol.pb.challenge.users.exceptions.EntityNotFoundException;
import br.com.compassuol.pb.challenge.users.mapper.UserMapper;
import br.com.compassuol.pb.challenge.users.model.RoleModel;
import br.com.compassuol.pb.challenge.users.model.UserModel;
import br.com.compassuol.pb.challenge.users.model.dto.CreateUserDTO;
import br.com.compassuol.pb.challenge.users.model.dto.UpdateUserDTO;
import br.com.compassuol.pb.challenge.users.model.dto.UserDTO;
import br.com.compassuol.pb.challenge.users.model.enums.DirectionSort;
import br.com.compassuol.pb.challenge.users.repository.RoleRepository;
import br.com.compassuol.pb.challenge.users.repository.UserRepository;
import br.com.compassuol.pb.challenge.users.utils.MessageUtils;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private UserMapper mapper;

    @Transactional
    public UserDTO save(CreateUserDTO dto) {
        Optional<UserModel> optional = repository.findByEmail(dto.getEmail());

        if (optional.isPresent()) {
            throw new EntityAlreadyExistsException("Email" + MessageUtils.ALREADY_EXIST);
        }

        List<RoleModel> list = new ArrayList<>();
        if (dto.getRoles() != null) {
            for (String id : dto.getRoles()) {
                Optional<RoleModel> optionalModel = roleRepository.findById(id);
                if (!optionalModel.isPresent()) {
                    throw new EntityAlreadyExistsException("Role" + MessageUtils.NO_RECORDS_FOUND);
                }
                list.add(optionalModel.get());
            }
        }

        UserModel UserModel = mapper.toEntity(dto, list);
        repository.save(UserModel);
        return mapper.toDto(UserModel);
    }

    @Transactional
    public UserDTO update(String id, UpdateUserDTO dto) {
        // Este put não adicionará e nem editará as categorias do produto

        UserDTO foundDto = this.findById(id);
        UserModel model = mapper.toEntity(dto, foundDto);
        repository.save(model);
        return mapper.toDto(model);
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        return mapper.toDto(repository.findAll());
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> findAll(
            Integer page,
            Integer linesPerPage,
            String orderBy,
            String direction
            ) {
        PageRequest pageRequest = PageRequest.of(
            page,
            linesPerPage,
            Direction.valueOf(DirectionSort.fromString(direction).getDescription()),
            orderBy
        );
        
        Page<UserModel> response = repository.findAll(pageRequest);

        return mapper.toDto(response);
    }

    @Transactional(readOnly = true)
    public UserDTO findById(String id) {
        return repository.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("User" + MessageUtils.NO_RECORDS_FOUND));
    }

    @Transactional
    public String delete(String id) {
        this.findById(id);
        repository.deleteById(id);
        return "User" + MessageUtils.DELETED_WITH_SUCESS;
    }
}
