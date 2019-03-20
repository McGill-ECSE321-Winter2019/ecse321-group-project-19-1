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
          
          coops: [],

          fields: {

            coopID: {
              label: 'CoopID',
              sortable: true
            },
            student: {
              label: 'StudentID',
              sortable: true
            },
            status:{
              label: 'Coop Status',
              sortable: true
            },
          }
          
        }
      },

      created: function() {
        // Initializing people from backend
        AXIOS.get(`/allCurrentCoops`)
          .then(response => {
            this.coops = response.data
              })
            }
    
  }