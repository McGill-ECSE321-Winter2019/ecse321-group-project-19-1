import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'https://cooperator-backend-260.herokuapp.com/'

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    data() {
        return {
          students: [],

          fields: {
            firstName: {
              label: 'FirstName',
              sortable: true
            },
            lastName: {
              label: 'LastName',
              sortable: true
            },
            studentId:{
              label: 'ID',
              sortable: true
            },
            problematic:{
              label: 'Problematic Status',
              sortable: true
            },
            coopStatus:{
              label: 'Coop Status',
              sortable: true
            },
          }
          
        }
      },

      created: function() {
        // Initializing people from backend
        AXIOS.get(`/allStudents`)
          .then(response => {
            this.students = response.data
              })
            }
    
  }