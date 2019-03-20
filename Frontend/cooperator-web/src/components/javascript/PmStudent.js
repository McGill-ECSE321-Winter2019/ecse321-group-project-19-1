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
            coopID: {
              label: 'CoopID',
              sortable: true
            },
            student: {
              label: 'StudentID',
              sortable: true
            },
            startDate:{
              label: "Start Date",
              sortable: true
            },
            endDate:{
              label: "End Date",
              sortable: true
            }
          },

          coops: []
        }
      },

      created: function() {
        // Initializing people from backend
        AXIOS.get(`/coops`)
          .then(response => {
            // JSON responses are automatically parsed.
            this.coops = response.data;
          })  
    },

    // methods:{
    //   getStudent: function(studentID){
    //     AXIOS.get(/)
    //   }
  //  }
    
  }