package com.example.mapper;

import com.example.dto.RoleDTO;
import com.example.entity.Role;
import org.modelmapper.ModelMapper;

public class RoleMapper {

    private ModelMapper modelMapper;

    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Role convertToEntity(Role dto){
        return modelMapper.map(dto,Role.class);

    }

    public RoleDTO convertToDto(Role entity){
        return modelMapper.map(entity,RoleDTO.class);

    }

}
