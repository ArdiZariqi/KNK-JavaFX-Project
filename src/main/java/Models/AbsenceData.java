package Models;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class AbsenceData {

        private Integer a_id;
        private Integer student_id;
        private String class_;
        private String course_name;
        private Integer time;
        private String firstName;
        private String lastName;
        private String gender;
        private Date date_;
        private String status;
        private String reasonability;


        // MAKE SURE THAT SAME DATATYPE THAT YOU WILL PUT THERE
        public AbsenceData(Integer a_id,Integer student_id, String class_, String course_name,Integer time, String firstName, String lastName, String gender, Date date_, String status, String reasonability) {
            this.a_id= a_id;
            this.student_id= student_id;
            this.class_ = class_;
            this.course_name = course_name;
            this.time=time;
            this.firstName = firstName;
            this.lastName = lastName;
            this.gender = gender;
            this.date_ = date_;
            this.status = status;
            this.reasonability = reasonability;
        }


        public Integer getA_id() {
        return a_id;
    }
        public Integer getStudent_id() {
            return student_id;
        }

        public String getClass_() {
            return class_;
        }

        public String getCourse_name() {
            return course_name;
        }

        public Integer getTime() {
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

