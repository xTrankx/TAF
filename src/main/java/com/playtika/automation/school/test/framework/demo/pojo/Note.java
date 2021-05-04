package com.playtika.automation.school.test.framework.demo.pojo;

import lombok.Data;
import net.andreinc.mockneat.MockNeat;

@Data
public class Note {

    Integer id;
    String content;
    String createdAt;
    String modifiedAt;
    Integer version;

    public Note() {
        this.content = MockNeat.threadLocal().celebrities().get();
    }
}
