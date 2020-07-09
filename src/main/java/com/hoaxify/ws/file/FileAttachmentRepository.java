package com.hoaxify.ws.file;
/*
 * Created by Oray Kurt
 * Date: 17-Jun-20
 * Time: 9:44 PM
 */

import com.hoaxify.ws.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface FileAttachmentRepository extends JpaRepository<FileAttachment, Long> {

	List<FileAttachment> findByDateBeforeAndHoaxIsNull(Date date);

	List<FileAttachment> findByHoaxUser(User user);
}
