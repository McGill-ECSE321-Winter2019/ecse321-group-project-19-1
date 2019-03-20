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
          coops: [],
          instructor: '',
          coopId: '',
          errorCoop: '',
        }
      },

      created: function() {
        // Initializing people from backend
        AXIOS.get(`/allCoops`)
          .then(response => {
            // JSON responses are automatically parsed.
            this.coops = response.data;
          })  
    },
    methods: {
        assignCoop: function (instructor, coopId) {
            console.log(`/assignCoop` + "?email=" + instructor + "&coopId=" + coopId)
          AXIOS.post(`/assignCoop` + "?email=" + instructor + "&coopId=" + coopId, {}, {})
            .then(response => {
              // JSON responses are automatically parsed.
              this.instructor = ''
              this.coopId = ''
              this.errorCoop = ''
              this.coops.push(response.data)
            })
            .catch(e => {
              var errorMsg = e.message
              console.log(errorMsg)
              this.errorCoop = errorMsg
            });
        }
    }
}