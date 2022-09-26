package com.mikko.hospitalapi.services;

import com.mikko.hospitalapi.exceptions.ResourceNotFoundException;
import com.mikko.hospitalapi.models.Patient;
import com.mikko.hospitalapi.models.Room;
import com.mikko.hospitalapi.repositories.PatientRepository;
import com.mikko.hospitalapi.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final PatientRepository patientRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomByID(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room not found"));
    }

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, Room room) {
        Room existingRoom = getRoomByID(id);
        existingRoom.setRoomNumber(room.getRoomNumber());
        existingRoom.setIsAirconditioned(room.getIsAirconditioned());
        existingRoom.setPatient(room.getPatient());
        return roomRepository.save(existingRoom);
    }

    public void deleteRoom(Long id) {
        Room existingRoom = getRoomByID(id);
        roomRepository.delete(existingRoom);
    }
}
