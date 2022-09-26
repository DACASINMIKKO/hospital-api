package com.mikko.hospitalapi.repositories;

import com.mikko.hospitalapi.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
