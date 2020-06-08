package com.hoaxify.ws.error;
/*
 * Created by Oray Kurt
 * Date: 08-Jun-20
 * Time: 4:04 PM
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

}
