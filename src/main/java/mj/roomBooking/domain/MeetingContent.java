package mj.roomBooking.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Embeddable
public class MeetingContent {

    private String content;

    private String conclusion;
}
