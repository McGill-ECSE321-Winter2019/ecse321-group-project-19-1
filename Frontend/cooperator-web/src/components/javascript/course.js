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
          courses: []
        }
      },

      created: function() {
        // Initializing people from backend
        AXIOS.get(`/ranking`)
          .then(response => {
            // JSON responses are automatically parsed.
            this.courses = response.data;
          })  
    },
    methods: {
        coopLength: function(course){
            return course.coopPositions.length
        }
    }
}