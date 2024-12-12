package com.example.school.Service;

import com.example.school.DTO.AddressDTO;
import com.example.school.Model.Address;
import com.example.school.Model.Teacher;
import com.example.school.Repository.AddressRepository;
import com.example.school.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final TeacherRepository teacherRepository;

    public void addAddress(AddressDTO addressDTO) {
        Teacher teacher = teacherRepository.findTeacherById(addressDTO.getTeacherId());
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher not found");
        }

        Address address = new Address();
        address.setArea(addressDTO.getArea());
        address.setStreet(addressDTO.getStreet());
        address.setBuildingNumber(addressDTO.getBuildingNumber());
        address.setTeacher(teacher);

        addressRepository.save(address);
    }


    public void updateAddress(Integer addressId, AddressDTO addressDTO) {
        Address address = addressRepository.findAddressById(addressId);
        if (address == null) {
            throw new IllegalArgumentException("Address not found");
        }

        address.setArea(addressDTO.getArea());
        address.setStreet(addressDTO.getStreet());
        address.setBuildingNumber(addressDTO.getBuildingNumber());
        addressRepository.save(address);
    }

    public void deleteAddress(Integer addressId) {
        Address address = addressRepository.findAddressById(addressId);
        if (address == null) {
            throw new IllegalArgumentException("Address not found");
        }
        addressRepository.delete(address);
    }

    public Address getAddressByTeacherId(Integer teacherId) {
        return addressRepository.findAddressByTeacher_Id(teacherId);
    }
    public List<Address>getAll(){
        return addressRepository.findAll();
    }
}
