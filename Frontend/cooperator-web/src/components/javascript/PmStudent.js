import axios from "axios";
import { log } from "util";
var config = require("../../../config");

<<<<<<< HEAD
var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl =
  "http://" + config.dev.backendHost + ":" + config.dev.backendPort;
=======
var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'https://cooperator-backend-260.herokuapp.com/'
>>>>>>> b78db288154732bc57245794a2e7ab7474ce3f5a

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
  data() {
    return {
      students: [],

      fields: {
        firstName: {
          label: "FirstName",
          sortable: true
        },
        lastName: {
          label: "LastName",
          sortable: true
        },
        studentId: {
          label: "ID",
          sortable: true
        },
        problematic: {
          label: "Problematic Status",
          sortable: true
        },
        coopStatus: {
          label: "Coop Status",
          sortable:true
        }
      }
    };
  },

  created: function() {
    
    // Initializing people from backend
    AXIOS.get(`/allStudents`)
      .then(response => {
        this.students = response.data;
      })
      .then(() => {
        for (var i = 0; i < this.students.length; i++) {
          var student = this.students[i];
          if (student.coopPositions.length > 0) {
            student.coopStatus = student.coopPositions[0].status;
          } else student.coopStatus = null;
        }    
            
        this.$refs.table.refresh();
      })
      .catch(error => {
        alert(error);
      });

      
  }
};
