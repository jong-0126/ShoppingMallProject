package com.project.project.service;

import com.project.project.domain.entity.Address;
import com.project.project.domain.entity.User;
import com.project.project.repository.AddressRepository;
import com.project.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public void addAddress(User user, Address address){
        address.setUser(user);
        address.setAddress_key(UUID.randomUUID());
        address.setPostcode(address.getPostcode());
        address.setRoadAddress(address.getRoadAddress());
        address.setJibunAddress(address.getJibunAddress());
        address.setDetailAddress(address.getDetailAddress());
        address.setExtraAddress(address.getExtraAddress());
        addressRepository.save(address);
    }


}