import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function CoopPositionDto(id, desc, start, end, location, term,
 studentId, termInst, status) {
this.coopId = id;
this.description = desc;
this.startDate = start;
this.endDate = end;
this.term = term;
this.location = location;
this.studentId = studentId;
this.termInstructor = termInst;
this.status=status;
}

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
        AXIOS.get(`/coops`)
          .then(response => {
            // JSON responses are automatically parsed.
            this.coops = response.data;
          })  
    },
    methods: {
        assignCoop: function (email, coopId) {
          AXIOS.post(`/assignCoop` + "?email=" + email + "&coopId=" + coopId, {}, {})
            .then(response => {
              // JSON responses are automatically parsed.
              this.instructor = ''
              this.coopId = ''
              this.errorCoop = ''
              created()
            })
            .catch(e => {
              var errorMsg = e.message
              console.log(errorMsg)
              this.errorCoop = errorMsg
            });
        }
    }
}