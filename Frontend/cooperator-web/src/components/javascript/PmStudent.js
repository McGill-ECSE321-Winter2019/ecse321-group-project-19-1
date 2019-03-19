import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    data() {
        return {
          fields: {
            firstName: {
              label: 'First Name',
              sortable: false
            },
            lastName: {
              label: 'Last Name',
              sortable: true
            },
            studentId:{
              label: "ID",
              sortable: true
            },
            problematic:{
              label: "Problematic",
              sortable: false
            }
          },

          students: []
        }
      },

      created: function() {
        // Initializing people from backend
        AXIOS.get(`/problematicStudents`)
          .then(response => {
            // JSON responses are automatically parsed.
            this.students = response.data;
          })  
    }
}