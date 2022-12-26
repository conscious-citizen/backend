package ru.ssau.citizen.service;

import ru.ssau.citizen.entities.Photo;

public interface PhotoService {
    Photo getPhoto(Integer id);

    String[] getPhotosByIncident(Long id);
}
