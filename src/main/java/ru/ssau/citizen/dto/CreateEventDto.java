package ru.ssau.citizen.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateEventDto {

        private String messageSubject;

        private String messageText;

        private List<PhotoDto> photo;

        private AddressDto addressDto;

        private Long rubricId;

        public CreateEventDto(String messageSubject, String messageText, List<PhotoDto> photos, AddressDto addressDto, Long rubricId) {
                this.messageSubject = messageSubject;
                this.messageText = messageText;
                this.photo = photos;
                this.addressDto = addressDto;
                this.rubricId = rubricId;
        }
}
