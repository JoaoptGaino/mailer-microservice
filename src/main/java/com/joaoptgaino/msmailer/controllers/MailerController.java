package com.joaoptgaino.msmailer.controllers;

import com.joaoptgaino.msmailer.dtos.MailerDTO;
import com.joaoptgaino.msmailer.models.MailerModel;
import com.joaoptgaino.msmailer.services.MailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import static org.springframework.beans.BeanUtils.copyProperties;

@RestController
public class MailerController {

    @Autowired
    MailerService mailerService;

    @PostMapping("/send-email")
    public ResponseEntity<MailerModel> sendEmail(@RequestBody @Valid MailerDTO mailerDTO){
        MailerModel mailerModel = new MailerModel();
        copyProperties(mailerDTO,mailerModel);
        mailerService.sendEmail(mailerModel);
        return new ResponseEntity<>(mailerModel, HttpStatus.CREATED);
    }
}
