package ru.ssau.citizen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ssau.citizen.entities.Photo;
import ru.ssau.citizen.repository.PhotoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    PhotoRepository photoRepository;


    @Override
    public Photo getPhoto(Integer id) {
        return photoRepository.getById(id);
    }

    @Override
    public String[] getPhotosByIncident(Long id) {
        Photo[] photos = photoRepository.findAll().stream().filter(item -> Objects.equals(item.getEvent().getId(), id)).toArray(Photo[]::new);
        List<String> urls = new ArrayList<>();
        for(Photo photo: photos) {
            urls.add("http://localhost:8086/event/photos/" + photo.getId());
        }
        return urls.toArray(String[]::new);
    }
}
