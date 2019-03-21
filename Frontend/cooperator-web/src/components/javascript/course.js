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
          if ((localStorage.getItem('loggedIn') != null)) {
            //if cookies expired, refresh
            if (this.$cookie.get("username") == null || this.$cookie.get("password") == null) {
                localStorage.removeItem('loggedIn')
                this.$cookie.delete('username');
                this.$cookie.delete('password');
                window.location.href = "/";
            }
            else {
                //reverify login information
                AXIOS.post(`/login/` + this.$cookie.get("username") + '/' + this.$cookie.get("password"), {}, {})
                    .then(response => {
                        if (response.data == 'TermInstructor') {
                            if(localStorage.getItem('loggedIn') != "TermInstructor"){
                                localStorage.setItem('loggedIn', "TermInstructor");
                                console.log("Not term instructor");
                                window.location.href = "/";
                            }
                            
                        }
                        else if (response.data == "ProgramManager") {
                            if(localStorage.getItem('loggedIn') != "ProgramManager"){
                                localStorage.setItem('loggedIn', "ProgramManager");
                                console.log("Not program manager");
                                window.location.href = "/";
                            }                           
                        }
                        else {
                            localStorage.removeItem('loggedIn')
                            this.$cookie.delete('username');
                            this.$cookie.delete('password');
                            console.log("bad log in information");
                            window.location.href = "/";
                        }
                    })
                    .catch(e => {
                        localStorage.removeItem('loggedIn')
                        this.$cookie.delete('username');
                        this.$cookie.delete('password');
                        console.log("error in post request: " + e);
                        window.location.href = "/";
                    });
            }
        }
    },
    methods: {
        coopLength: function(course){
            return course.coopPositions.length
        }
    }
}