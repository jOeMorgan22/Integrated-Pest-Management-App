package com.ipm_manager_ws.ipm_manager_app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


    @Entity
    @Table(name = "user_groups")
    public class UserGroup {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "group_names", nullable = false, unique = true)
        private String groupName;
    
        @Column(name = "group_codes", unique = true)
        private String groupCode;

        public UserGroup(String groupName) {
            this.groupName = groupName;
        }

        public UserGroup() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getGroupCode() {
            return groupCode;
        }

        public void setGroupCode(String groupCode) {
            this.groupCode = groupCode;
        }



        
}
