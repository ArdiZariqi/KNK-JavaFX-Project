package Models;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class AbsenceData {

        private Integer student_id;
        private String class_;
        private Integer course_id;
        private Timestamp time;
        private String firstName;
        private String lastName;
        private String gender;
        private Date date_;
        private String status;
        private String reasonability;


        // MAKE SURE THAT SAME DATATYPE THAT YOU WILL PUT THERE
        public AbsenceData(Integer student_id, String class_, Integer course_id,Timestamp time, String firstName, String lastName, String gender, Date date, String status, String reasonability) {
            this.student_id= student_id;
            this.class_ = class_;
            this.course_id = course_id;
            this.time=time;
            this.firstName = firstName;
            this.lastName = lastName;
            this.gender = gender;
            this.date_ = date_;
            this.status = status;
            this.reasonability = reasonability;
        }


        public Integer getStudent_id() {
            return student_id;
        }

        public String getClass_() {
            return class_;
        }

        public Integer getCourse_id() {
            return course_id;
        }

        public Timestamp getTime() {
        return time;
    }
        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getGender() {
            return gender;
        }

        public Date getDate_() {
            return date_;
        }

        public String getStatus() {
            return status;
        }

        public String getReasonability() {
            return reasonability;
        }

    }

