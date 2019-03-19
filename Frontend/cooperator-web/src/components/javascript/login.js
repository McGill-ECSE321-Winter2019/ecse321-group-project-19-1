import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
  name: 'login',
  data() {
    return {
      username: '',
      password: '',
      errorPerson: '',
      response: '',
    }
  },
  created: function () {
    // Initializing people from backend
    AXIOS.get(`/persons`)
      .then(response => {
        // JSON responses are automatically parsed.
        this.people = response.data
      })
      .catch(e => {
        this.errorPerson = e;
      });
  },
  methods: {
    login(username, password) {
        if(username == ''){
        var errorMsg = "Unvalid username"
          console.log(errorMsg)
          this.errorPerson = errorMsg
          return
        }
        if(password == ''){
            var errorMsg = "Unvalid password"
              console.log(errorMsg)
              this.errorPerson = errorMsg
              return
            }
      AXIOS.post(`/login/` + username + '/' + password, {}, {})
        .then(response => {
          // JSON responses are automatically parsed.
          this.response = response.data
          this.username = ''
          this.password = ''
          this.errorPerson = ''
          console.log(this.response)
        })
        .catch(e => {
          var errorMsg = e.message
          console.log(errorMsg)
          this.errorPerson = errorMsg
        });
    }
  }
}